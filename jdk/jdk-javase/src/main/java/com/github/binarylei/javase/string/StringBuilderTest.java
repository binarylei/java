package com.github.binarylei.javase.string;

import org.junit.Test;

/**
 * 测试可变字符序列。StringBuilder(线程不安全，效率高),StringBuffer(线程安全，效率低)
 * String：不可变字符序列
 */
public class StringBuilderTest {

	@Test
	public void test() {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder(32);
		StringBuilder sb2 = new StringBuilder("abcd");
		sb2.append("efg");
		sb2.append(true).append(321).append("哈哈");
		System.out.println(sb2);
		
		System.out.println("---------------");
		
		StringBuilder gh = new StringBuilder("a");
		for (int i = 0; i < 1000; i++) {
			gh.append(i);
		}
		System.out.println(gh);
	}
}
