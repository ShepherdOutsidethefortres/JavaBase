package zhw.dynamicproxy;

import javassist.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class JavaAssistDemo {

    @Test
    public void runProxy() throws CannotCompileException, IOException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("zhw.dynamicproxy.Programmer");

        CtMethod ctMethod = CtNewMethod.make("public void code(){}", ctClass);

        ctMethod.insertBefore("System.out.println(\"I'm a Programmer,just coding...\");");

        ctClass.addMethod(ctMethod);

        ctClass.writeFile("./output");
    }
}
