package com.jdjr.knowledge.test.frame.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.jsf.gd.GenericService;
import com.jdjr.knowledge.test.frame.annotation.Task;
import com.jdjr.knowledge.test.frame.internal.entity.Stash;
import com.jdjr.knowledge.test.frame.internal.entity.Stash.Info;
import com.jdjr.knowledge.test.frame.internal.entity.Stash.Log;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent;
import com.jdjr.knowledge.test.frame.internal.util.*;
import com.jdjr.knowledge.test.frame.internal.util.RequestUtil.KnowledgeTaskInfo;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.testng.*;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestListener implements ITestListener, IClassListener, IInvokedMethodListener2, IReporter, IMethodInterceptor, IDataProviderListener {
    public static ThreadLocal<Boolean> threadSaveFlag;
    public static ThreadLocal<Map<String, Object>> threadVariableData;
    public static ThreadLocal<Map<String, Set<String>>> threadFunctionIdMap;
    public static ThreadLocal<List<ResultContent>> threadSignalResultContentList;
    public static ThreadLocal<List<ResultContent>> threadScenelResultContentList;
    public static ThreadLocal<ConcurrentHashMap<String, GenericService>> threadGenericService;
    public static ThreadLocal<List<String>> threadHost;
    private static List<Map<String, Object>> report = new ArrayList();
    private int totalExecuted = 0;
    private String currentName = "";
    private String taskName = "";
    private boolean logstashEnable = true;
    private static ObjectMapper objectMapper;
    private KnowledgeTaskInfo knowledgeTaskInfo;

    public TestListener() {
    }

    private boolean validVersion(String versionConfig) {
        String requiredVersion = versionConfig.split("-")[0];
        String[] requiredVersionS = requiredVersion.split("\\.");

        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            List<Dependency> dependencies = model.getDependencies();
            Iterator var7 = dependencies.iterator();

            String groupId;
            String artifactId;
            String version;
            do {
                if (!var7.hasNext()) {
                    return true;
                }

                Dependency dependency = (Dependency) var7.next();
                groupId = dependency.getGroupId();
                artifactId = dependency.getArtifactId();
                version = dependency.getVersion();
            } while (!groupId.equals("com.jd.dhf") || !artifactId.equals("api-test"));

            version = version.split("-")[0];
            if (version.equals(requiredVersion)) {
                return true;
            } else {
                String[] versionS = version.split("\\.");

                for (int i = 0; i < versionS.length && i < requiredVersionS.length; ++i) {
                    if (Integer.parseInt(versionS[i]) > Integer.parseInt(requiredVersionS[i])) {
                        return true;
                    }

                    if (Integer.parseInt(versionS[i]) < Integer.parseInt(requiredVersionS[i])) {
                        return false;
                    }
                }

                if (versionS.length > requiredVersionS.length) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception var14) {
            return true;
        }
    }

    public void onBeforeClass(ITestClass testClass) {
        if (!testClass.getRealClass().isAnnotationPresent(Task.class)) {
            LogUtil.taskNotPresent();
            this.logstashEnable = false;
        } else if (!this.validVersion(DpCcUtil.getData("/dhfcc/configdata/dhf2/minVersion"))) {
            LogUtil.versionNotValid();
            System.exit(0);
        } else {
            if (!this.validVersion(DpCcUtil.getData("/dhfcc/configdata/dhf2/currentVersion"))) {
                LogUtil.versionNotValid();
            }

            this.taskName = ((Task) testClass.getRealClass().getAnnotation(Task.class)).value();
            this.knowledgeTaskInfo = RequestUtil.validTaskId(this.taskName);
            if (!this.knowledgeTaskInfo.isSuccess()) {
                LogUtil.taskNotValid();
                this.logstashEnable = false;
            } else {
                LogUtil.taskValid(this.knowledgeTaskInfo);
            }
        }

        threadVariableData = ThreadLocal.withInitial(HashMap::new);
        threadGenericService = ThreadLocal.withInitial(ConcurrentHashMap::new);
        threadHost = ThreadLocal.withInitial(ArrayList::new);
    }

    public void onAfterClass(ITestClass testClass) {
        HostUtil.clearAllVirtualDns();
        threadVariableData.remove();
        threadGenericService.remove();
        threadHost.remove();
    }

    private void deleteHost() {
        Iterator var1 = ((List) threadHost.get()).iterator();

        while (var1.hasNext()) {
            String host = (String) var1.next();
            HostUtil.deleteHost(host);
        }

    }

    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        methods.forEach((method) -> {
            if (((Test) this.getTestMethodAnnotation(method.getMethod(), Test.class)).description().isEmpty()) {
                LogUtil.descNotPresent(method.getMethod().getMethodName());
                System.exit(0);
            }

        });
        return methods;
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (method.isTestMethod() && this.isAnnotationPresent(method, Test.class)) {
            threadSignalResultContentList = ThreadLocal.withInitial(ArrayList::new);
            threadScenelResultContentList = ThreadLocal.withInitial(ArrayList::new);
            threadFunctionIdMap = ThreadLocal.withInitial(HashMap::new);
            threadSaveFlag = ThreadLocal.withInitial(() -> {
                return this.isAnnotationPresent(method, Test.class);
            });
            if (!method.getTestMethod().getMethodName().equals(this.currentName)) {
                ++this.totalExecuted;
                this.currentName = method.getTestMethod().getMethodName();
            }
        }

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (method.isTestMethod() && this.isAnnotationPresent(method, Test.class)) {
            if (this.logstashEnable) {
                LogStashUtil.stashInfo(this.creatResult(method, testResult));
            }

            if (!method.getTestMethod().getMethodName().equals(this.currentName)) {
                ++this.totalExecuted;
                this.currentName = method.getTestMethod().getMethodName();
            }

            threadSignalResultContentList.remove();
            threadScenelResultContentList.remove();
            threadFunctionIdMap.remove();
            threadSaveFlag.remove();
            int status = testResult.getStatus();
            LogUtil.info("====================================================================");
            LogUtil.info(" " + method.getTestMethod().getTestClass().getRealClass().getSimpleName() + " - " + method.getTestMethod().getMethodName());
            LogUtil.info(" 结果 ： " + (status == 1 ? "成功" : (status == 2 ? "失败" : (status == 3 ? "跳过" : "未知"))));
            LogUtil.info(" 进度 ： " + this.formPercentageStr(this.totalExecuted, (long) context.getAllTestMethods().length) + "  ( " + this.totalExecuted + " / " + context.getSuite().getAllMethods().size() + " )");
            LogUtil.info("====================================================================");
            LogUtil.info("");
        }

    }

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
        this.resetRetryCount(result);
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        this.resetRetryCount(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }

    private void resetRetryCount(ITestResult result) {
        TestRetryAnalyzer testRetryAnalyzer = (TestRetryAnalyzer) result.getMethod().getRetryAnalyzer();
        if (testRetryAnalyzer != null) {
            testRetryAnalyzer.setCount(0);
        }
    }

    public void onTestFailure(ITestResult result) {
        this.resetRetryCount(result);
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        if (this.logstashEnable) {
            File file = new File("");
            String prjPath = file.getAbsolutePath();
            String resultFile = prjPath + "\\target\\result.html";
            String os = System.getProperty("os.name").toLowerCase();
            if (!os.contains("windows")) {
                resultFile = resultFile.replace("\\", "/");
            }

            LogUtil.report(resultFile);
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            resolver.setPrefix("templates/");
            resolver.setSuffix(".html");
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(resolver);
            Context context = new Context();
            context.setVariable("report", report);
            context.setVariable("task", this.knowledgeTaskInfo.getName());

            try {
                FileWriter write = new FileWriter(resultFile);
                templateEngine.process("index", context, write);
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }
    }

    private boolean isAnnotationPresent(IInvokedMethod m, Class<? extends Annotation> annotationClass) {
        return m.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(annotationClass);
    }

    private <T extends Annotation> T getAnnotation(IInvokedMethod m, Class<T> annotationClass) {
        return m.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(annotationClass);
    }

    private <T extends Annotation> T getAnnotationOnClass(IInvokedMethod m, Class<T> annotationClass) {
        return (T) m.getTestMethod().getRealClass().getAnnotation(annotationClass);
    }

    private <T extends Annotation> T getTestMethodAnnotation(ITestNGMethod m, Class<T> annotationClass) {
        return m.getConstructorOrMethod().getMethod().getAnnotation(annotationClass);
    }

    private String formPercentageStr(int executedTestCount, long totalTestCount) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((double) ((float) executedTestCount / (float) totalTestCount * 100.0F)) + "%";
    }

    private String creatResult(IInvokedMethod method, ITestResult testResult) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        Stash stash = new Stash();
        HashMap caseMap = new HashMap();

        try {
            Info info = this.generateStashInfo(method, testResult);
            Log log = this.genertaeStashLog(method);
            stash.setTask_id(((Task) this.getAnnotationOnClass(method, Task.class)).value());
            stash.setDescription(((Test) this.getAnnotation(method, Test.class)).description());
            stash.setInfo(objectMapper.writeValueAsString(info));
            stash.setLog(ZipUtil.compress(objectMapper.writeValueAsString(log)));
            stash.setDay(DateUtil.dateToStr(new Date()));
            caseMap.put("description", stash.getDescription());
            caseMap.put("method", info.getName().split("\\.")[info.getName().split("\\.").length - 1]);
            caseMap.put("result", info.getResult());
            caseMap.put("functionId", this.getReportDetail(log));
            report.add(caseMap);
            return objectMapper.writeValueAsString(stash);
        } catch (IOException var7) {
            LogUtil.error(var7.getMessage());
            return "";
        }
    }

    private List<Map<String, Object>> getReportDetail(Log l) {
        List<ResultContent> signal = l.getSingle();
        List<ResultContent> scene = l.getScene();
        signal.addAll(scene);
        List<Map<String, Object>> r = new ArrayList();
        signal.forEach((resultContent) -> {
            Map<String, Object> typeMap = new HashMap();
            typeMap.put("url", resultContent.getRequest().getUrl());
            typeMap.put("method", resultContent.getRequest().getRequestMethod().name().toUpperCase().equals("INVOKE") ? "JSF" : resultContent.getRequest().getRequestMethod().name().toUpperCase());
            typeMap.put("execute", DateUtil.timeToStr(resultContent.getResponse().getExecuteTime()));
            typeMap.put("responseTime", resultContent.getResponse().getResponseTime());
            r.add(typeMap);
        });
        return r;
    }

    private Log genertaeStashLog(IInvokedMethod method) {
        Log log = new Log();
        log.setSingle((List) threadSignalResultContentList.get());
        log.setScene((List) threadScenelResultContentList.get());
        Test ann = (Test) this.getAnnotation(method, Test.class);
        com.jdjr.knowledge.test.frame.internal.entity.Stash.Log.Annotation annotation = new com.jdjr.knowledge.test.frame.internal.entity.Stash.Log.Annotation();
        annotation.setDataProvider(ann.dataProvider());
        annotation.setDataProviderClass(ann.dataProviderClass().getName());
        annotation.setDependsOnGroups(ann.dependsOnGroups());
        annotation.setDependsOnMethods(ann.dependsOnMethods());
        annotation.setEnabled(ann.enabled());
        annotation.setGroups(ann.groups());
        annotation.setPriority(ann.priority());
        log.setAnnotation(annotation);
        return log;
    }

    private Info generateStashInfo(IInvokedMethod method, ITestResult testResult) {
        Info info = new Info();
        info.setName(this.getName(method));
        info.setResult(testResult.getStatus());
        info.setModule_name(this.knowledgeTaskInfo.getModule_name());
        info.setTester_erp(this.knowledgeTaskInfo.getTester_erp());
        info.setTester_name(this.knowledgeTaskInfo.getTester_name());
        info.setTester_group(this.knowledgeTaskInfo.getTester_group());
        info.setTester_org(this.knowledgeTaskInfo.getTester_org());
        info.setType(this.knowledgeTaskInfo.getType());
        info.setFunctionId((Map) threadFunctionIdMap.get());
        return info;
    }

    private String getName(IInvokedMethod method) {
        return method.getTestMethod().getRealClass().getName() + "." + method.getTestMethod().getMethodName();
    }

    public void beforeDataProviderExecution(IDataProviderMethod dataProviderMethod, ITestNGMethod method, ITestContext iTestContext) {
    }

    public void afterDataProviderExecution(IDataProviderMethod dataProviderMethod, ITestNGMethod method, ITestContext iTestContext) {
    }
}
