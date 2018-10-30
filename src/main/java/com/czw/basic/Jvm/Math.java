package com.czw.basic.Jvm;

import java.util.HashMap;
import java.util.Map;


class Math{
	public static final Integer CONSTANT_1 = 666;

	public int math() {
		int a = 1; 
		int b = 2;
		int c = (a + b)*10;
		return c;
	}

	public static void main(String[] args) {
		Math math = new Math();
		math.math();
		Map<String, String> map = new HashMap<>();
		map.put("test", "test"); //map引用了HashMap(),
		new Thread().start(); //本地方法栈 native
		System.out.println("end");
	}
}
