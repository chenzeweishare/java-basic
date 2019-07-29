package com.czw.basic.proxy;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * 主要使用到 InvocationHandler 接口和 Proxy.newProxyInstance() 方法。 
 * JDK动态代理要求被代理实现一个接口，只有接口中的方法才能够被代理 。
 * 其方法是将被代理对象注入到一个中间对象，而中间对象实现InvocationHandler接口，在实现该接口时，可以在 被代理对象调用它的方法时，在调用的前后插入一些代码。
 * 而 Proxy.newProxyInstance() 能够利用中间对象来生产代理对象。插入的代码就是切面代码。所以使用JDK动态代理可以实现AOP
 * @auth czw
 * @date 2019-03-05
 * @time 11:55
 */
public class ProxyTest {

    public static void main(String[] args) {
        Object proxyObject = new UserServiceImpl();
        ProxyUtil proxyUtil = new ProxyUtil(proxyObject);
        UserService userService = (UserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), UserServiceImpl.class.getInterfaces(), proxyUtil);
        userService.addUser();
        userService.findUserById();
    }
}
