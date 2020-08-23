package ProxyTest;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class MethodInterception {

    @Test
    public void annotationValue() {
        MainPage mainPage = createPage(MainPage.class);
        assertNotNull(mainPage);
        assertEquals(mainPage.buttonSearch(), ".//*[@test-attr='button_search']");
        assertEquals(mainPage.textInputSearch(), ".//*[@test-attr='input_search']");
    }

    private MainPage createPage(Class clazz) {
        Page mainPage = new Page();
        ClassLoader classLoader = mainPage.getClass().getClassLoader();
        Class[] interfaces = mainPage.getClass().getInterfaces();
        InvocationHandler handler = new PageInvocationHandler(mainPage,clazz);
        MainPage proxy = (MainPage) Proxy.newProxyInstance(classLoader,interfaces,handler);
        return  proxy;
    }

    private static class Page implements MainPage {
        @Override
        public String textInputSearch() {
            return searchForXPath("textInputSearch");
        }
        @Override
        public String buttonSearch() {
            return searchForXPath("buttonSearch");
        }

        public String searchForXPath(String name){
            Class<?>[] interfaces = this.getClass().getInterfaces();
            for (Class classes : interfaces){
                for (Method classMethod : classes.getDeclaredMethods()){
                    if(classMethod.isAnnotationPresent(Selector.class) && classMethod.getName().equals(name)){
                            return classMethod.getAnnotation(Selector.class).xpath();
                    }
                }
            }
            return null;
        }
    }

    static class PageInvocationHandler implements InvocationHandler {
        private Class clazz;
        private MainPage page;

        public PageInvocationHandler(MainPage page, Class clazz) {
            this.page = page;
            this.clazz = clazz;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method m : clazz.getDeclaredMethods()){
                if (m.getName().equals(method.getName())){
                    return m.invoke(page,args);
                }
            }
            return method.invoke(page,args);
        }
    }
}