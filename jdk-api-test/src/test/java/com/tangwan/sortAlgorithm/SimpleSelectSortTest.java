package com.tangwan.sortAlgorithm;

import org.junit.Test;

/**
 * 简单选择排序测试
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName SimpleSelectSortTest.java
 * @Date 2016/10/29 21:22
 * @since JDK 1.8
 */
public class SimpleSelectSortTest {
    @Test
    public void test(){
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };
        SimpleSelectSort.sort(a);
    }
}
