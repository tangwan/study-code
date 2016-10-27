package com.tangwan.algorithm.sort;

import java.util.Arrays;

public class 冒泡排序 {
	public static void main(String[] args) {
		int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };
		System.out.println("排序之前："+Arrays.toString(a));
		
		// 冒泡排序
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				// 这里-i主要是每遍历一次都把最大的i个数沉到最底下去了，没有必要再替换了
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		
		System.out.println("排序之后："+Arrays.toString(a));
	}
}
