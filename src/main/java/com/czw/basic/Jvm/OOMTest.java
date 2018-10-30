package com.czw.basic.Jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 堆初始10M, 最大10M, 打印Heap信息, 把当时快照保存
 */
public class OOMTest {
	// JVM设置 当时情况   
	// -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=H:\jvm.dump
	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		int i = 0;
		while (true) {
			list.add(new User(i++, UUID.randomUUID().toString()));
		}
	}
}
