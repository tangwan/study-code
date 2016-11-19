package com.tangwan.sortAlgorithm;

import java.util.Arrays;

/**
 * 简单的选择排序
 * @FileName SimpleSelectSort.java
 * @Date 2016/10/29 20:42
 * @author Name:tangwan  Mail:devintowne.tang@boldseas.com
 * @since JDK 1.8
 * @version V1.0
 */
public class SimpleSelectSort {
	public static void sort(int[] a) {
		System.out.println("排序之前："+Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
		
		// SimpleSelectSort
		for (int i = 0; i < a.length; i++) {
			int min = a[i];
			int n = i; // 最小数的索引
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < min) { // 找出最小的数
					min = a[j];
					n = j;
				}
			}
			a[n] = a[i];
			a[i] = min;

		}
		
		System.out.println("排序之后："+Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
	}
}
