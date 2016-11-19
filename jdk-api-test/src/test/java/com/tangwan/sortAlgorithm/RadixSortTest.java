package com.tangwan.sortAlgorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 基数排序测试
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName RadixSortTest.java
 * @Date 2016/10/29 21:20
 * @since JDK 1.8
 */
public class RadixSortTest {
    @Test
    public void test(){
        int[] a = { 49, 38, 65, 97, 176, 213, 227, 49, 78, 34, 12, 164, 11, 18, 1 };
        System.out.println("排序之前："+ Arrays.toString(a));

        // RadixSort
        RadixSort.sort(a);

        System.out.println("排序之后："+Arrays.toString(a));
    }
}
