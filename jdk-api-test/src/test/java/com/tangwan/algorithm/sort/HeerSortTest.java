package com.tangwan.algorithm.sort;

import org.junit.Test;

/**
 * 希尔排序
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName HeerSortTest.java
 * @Date 2016/10/29 21:16
 * @since JDK 1.8
 */
public class HeerSortTest {
    @Test
    public void test(){
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
        HeerSort.sort(a);
    }
}
