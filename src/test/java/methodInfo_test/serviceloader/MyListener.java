package methodInfo_test.serviceloader;

import methodInfo_test.TestMethodInfo;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class MyListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        Class<?> testClass = iInvokedMethod.getTestMethod().getTestClass().getRealClass();
        if(testClass.isAnnotationPresent(TestMethodInfo.class)){
            System.out.println(testClass.getAnnotation(TestMethodInfo.class).author());
            System.out.println(testClass.getAnnotation(TestMethodInfo.class).lastModified());
            System.out.println(testClass.getAnnotation(TestMethodInfo.class).priority());
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}
