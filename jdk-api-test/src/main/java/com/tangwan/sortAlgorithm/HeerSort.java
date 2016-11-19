package com.tangwan.sortAlgorithm;

import java.util.Arrays;

/**
 * 希尔排序
 * @FileName HeerSort.java
 * @Date 2016/10/29 20:39
 * @author Name:tangwan  Mail:devintowne.tang@boldseas.com
 * @since JDK 1.8
 * @version V1.0
 */
public class HeerSort {
	public static void sort(int[] a) {
		System.out.println("排序之前："+Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
		
		// HeerSort
		int d = a.length;
		while (true) {
			d = d / 2;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < a.length; i = i + d) {
					int temp = a[i];
					int j;
					for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
						a[j + d] = a[j];
					}
					a[j + d] = temp;
				}
			}
			if (d == 1) {
				break;
			}
		}
		System.out.println("排序之后："+Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
	}
}
