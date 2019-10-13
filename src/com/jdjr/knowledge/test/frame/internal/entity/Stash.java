package com.jdjr.knowledge.test.frame.internal.entity;

import com.jdjr.knowledge.test.frame.internal.response.ResultContent;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Stash implements Serializable {
    private static final long serialVersionUID = 1L;
    private String task_id;
    private String description;
    private String info;
    private String log;
    private String day;

    public Stash() {
    }

    public static long getSerialVersionUID() {
        return 1L;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTask_id() {
        return this.task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public static class Info {
        private String name;
        private int result;
        private int type;
        private String module_name;
        private String tester_erp;
        private String tester_name;
        private String tester_group;
        private String tester_org;
        private Map<String, Set<String>> functionId;

        public Info() {
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getResult() {
            return this.result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getModule_name() {
            return this.module_name;
        }

        public void setModule_name(String module_name) {
            this.module_name = module_name;
        }

        public String getTester_group() {
            return this.tester_group;
        }

        public void setTester_group(String tester_group) {
            this.tester_group = tester_group;
        }

        public String getTester_erp() {
            return this.tester_erp;
        }

        public void setTester_erp(String tester_erp) {
            this.tester_erp = tester_erp;
        }

        public String getTester_name() {
            return this.tester_name;
        }

        public void setTester_name(String tester_name) {
            this.tester_name = tester_name;
        }

        public String getTester_org() {
            return this.tester_org;
        }

        public void setTester_org(String tester_org) {
            this.tester_org = tester_org;
        }

        public Map<String, Set<String>> getFunctionId() {
            return this.functionId;
        }

        public void setFunctionId(Map<String, Set<String>> functionId) {
            this.functionId = functionId;
        }
    }

    public static class Log {
        private Stash.Log.Annotation annotation;
        private List<ResultContent> single;
        private List<ResultContent> scene;

        public Log() {
        }

        public Stash.Log.Annotation getAnnotation() {
            return this.annotation;
        }

        public void setAnnotation(Stash.Log.Annotation annotation) {
            this.annotation = annotation;
        }

        public List<ResultContent> getSingle() {
            return this.single;
        }

        public void setSingle(List<ResultContent> single) {
            this.single = single;
        }

        public List<ResultContent> getScene() {
            return this.scene;
        }

        public void setScene(List<ResultContent> scene) {
            this.scene = scene;
        }

        public static class Annotation {
            private int priority;
            private boolean enabled;
            private String dataProvider;
            private String dataProviderClass;
            private String[] dependsOnGroups;
            private String[] dependsOnMethods;
            private String[] groups;

            public Annotation() {
            }

            public int getPriority() {
                return this.priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getDataProvider() {
                return this.dataProvider;
            }

            public void setDataProvider(String dataProvider) {
                this.dataProvider = dataProvider;
            }

            public String getDataProviderClass() {
                return this.dataProviderClass;
            }

            public void setDataProviderClass(String dataProviderClass) {
                this.dataProviderClass = dataProviderClass;
            }

            public String[] getDependsOnGroups() {
                return this.dependsOnGroups;
            }

            public void setDependsOnGroups(String[] dependsOnGroups) {
                this.dependsOnGroups = dependsOnGroups;
            }

            public String[] getDependsOnMethods() {
                return this.dependsOnMethods;
            }

            public void setDependsOnMethods(String[] dependsOnMethods) {
                this.dependsOnMethods = dependsOnMethods;
            }

            public String[] getGroups() {
                return this.groups;
            }

            public void setGroups(String[] groups) {
                this.groups = groups;
            }
        }
    }
}
