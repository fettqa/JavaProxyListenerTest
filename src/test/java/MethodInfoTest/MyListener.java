package MethodInfoTest;

import org.testng.*;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MyListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        Class<? extends ITestClass> testClass = iInvokedMethod.getTestMethod().getTestClass().getClass();
        Annotation[] annotations = iInvokedMethod.getTestMethod().getTestClass().getClass().getAnnotations();
        boolean flag = testClass.isAnnotationPresent(Test.class);
        boolean flag1 = testClass.isAnnotationPresent(TestMethodInfo.class);
        for (Annotation annotation : annotations) {
            System.out.println(annotation.getClass().getAnnotation(TestMethodInfo.class).author());
            System.out.println(annotation.getClass().getAnnotation(TestMethodInfo.class).lastModified());
            System.out.println(annotation.getClass().getAnnotation(TestMethodInfo.class).priority());
            System.out.println(annotations);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}
