package com.tangwan.sortAlgorithm;

import java.util.Arrays;
/**
 * 直接插入排序
 * @FileName StraightInsertSort.java
 * @Date 2016/10/29 20:41
 * @author Name:tangwan  Mail:devintowne.tang@boldseas.com
 * @since JDK 1.8
 * @version V1.0
 */
public class StraightInsertSort {
	public static void sort(int[] a) {
		System.out.println("排序之前："+Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
		
		// StraightInsertSort
		for (int i = 1; i < a.length; i++) {
			// 待插入元素temp,要和temp之前所有数据比较大小,把比temp大的数据的索引+1
			int temp = a[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				// 将大于temp的往后移动一位
				if (a[j] > temp) {
					a[j + 1] = a[j];
				} else {
					break;
				}
			}
			a[j + 1] = temp;
		}
		
		System.out.println("排序之后："+Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
	}
}
