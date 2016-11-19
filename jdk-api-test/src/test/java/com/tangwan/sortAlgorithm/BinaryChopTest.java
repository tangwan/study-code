package com.tangwan.sortAlgorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 二分查找测试
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName BinaryChopTest.java
 * @Date 2016/10/29 20:58
 * @since JDK 1.8
 */
public class BinaryChopTest {
    @Test
    public void test(){
        int[] a = { 49, 38, 65, 97, 176, 213, 227, 49, 78, 34, 12, 164, 11, 18, 1 };
        System.out.println("排序之前："+ Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/

        // BinaryChop
        BinaryChop.sort(a);

        System.out.println("排序之后："+Arrays.toString(a));
		/*for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
    }
}
