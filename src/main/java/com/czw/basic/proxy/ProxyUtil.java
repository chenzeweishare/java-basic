package com.czw.basic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @auth czw
 * @date 2019-03-05
 * @time 11:53
 */
public class ProxyUtil implements InvocationHandler {

    private Object target;//被代理的对象

    public ProxyUtil(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do something before");
        // 调用被代理对象的方法并得到返回值       
        Object result = method.invoke(target, args);
        System.out.println("do something after");
        return result;
    }

    public Object getTarget() {

        return target;

    }

    public void setTarget(Object target) {

        this.target = target;

    }
}
