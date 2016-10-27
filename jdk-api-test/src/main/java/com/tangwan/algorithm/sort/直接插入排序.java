package com.tangwan.algorithm.sort;

import java.util.Arrays;

public class 直接插入排序 {
	public static void main(String[] args) {
		//排序前数组
		int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
		System.out.println("排序之前："+Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
		
		// 直接插入排序
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
