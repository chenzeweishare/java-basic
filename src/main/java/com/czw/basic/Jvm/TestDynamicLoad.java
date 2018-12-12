package com.czw.basic.Jvm;

/**
 * -verbose:class 类加载不是jar包中所有的类都加载
 */
public class TestDynamicLoad {

    static {
        System.out.println("*************static code************");
    }

    public static void main(String[] args){
        new A();
        System.out.println("*************load test************");
        new B();
    }
}

class A{
    public A(){
        System.out.println("*************initial A************");
    }
}

class B{
    public B(){
        System.out.println("*************initial B************");
    }
}
