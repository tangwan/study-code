package com.tangwan.sortAlgorithm;

import org.junit.Test;

/**
 * 直接插入排序
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName StraightInsertSortTest.java
 * @Date 2016/10/29 21:25
 * @since JDK 1.8
 */
public class StraightInsertSortTest {
    @Test
    public void test(){
        //排序前数组
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
        StraightInsertSort.sort(a);
    }
}
