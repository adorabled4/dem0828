package com.dhx.dem0828.cglibdemo;

    import net.sf.cglib.proxy.MethodInterceptor;
    import net.sf.cglib.proxy.MethodProxy;

    import java.lang.reflect.Method;

/**
 * @author adorabled4
 * @className ServiceInterceptor
 * @date : 2023/08/29/ 13:26
 **/
public class ServiceInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before(method.getName());
        // 注意这里是调用invokeSuper而不是invoke，否则死循环;
        // methodProxy.invokeSuper执行的是原始类的方法;
        // method.invoke执行的是子类的方法;
        Object result = methodProxy.invokeSuper(obj, objects);
        after(method.getName());
        return result;
    }

    /**
     * 调用invoke方法之前执行
     */
    private void before(String methodName) {
        System.out.println("调用方法" + methodName +"之【前】的日志处理");
    }

    /**
     * 调用invoke方法之后执行
     */
    private void after(String methodName) {
        System.out.println("调用方法" + methodName +"之【后】的日志处理");
    }
}
