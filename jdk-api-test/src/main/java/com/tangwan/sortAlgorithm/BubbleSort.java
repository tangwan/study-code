package com.tangwan.sortAlgorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 * @FileName BubbleSort.java
 * @Date 2016/10/29 20:36
 * @author Name:tangwan  Mail:devintowne.tang@boldseas.com
 * @since JDK 1.8
 * @version V1.0
 */
public class BubbleSort {
	public static void sort(int[] a) {
		System.out.println("排序之前："+Arrays.toString(a));
		
		// BubbleSort
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
