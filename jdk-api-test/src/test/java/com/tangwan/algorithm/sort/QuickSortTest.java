package com.tangwan.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

import static com.tangwan.algorithm.sort.QuickSort.quick;

/**
 * 快速排序测试
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName QuickSortTest.java
 * @Date 2016/10/29 21:19
 * @since JDK 1.8
 */
public class QuickSortTest {
    @Test
    public void test(){
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };
        System.out.println("排序之前："+ Arrays.toString(a));

        // QuickSort
        quick(a);

        System.out.println("排序之后："+Arrays.toString(a));
    }
}
