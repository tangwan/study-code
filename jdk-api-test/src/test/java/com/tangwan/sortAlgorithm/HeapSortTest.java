package com.tangwan.sortAlgorithm;

import org.junit.Test;

import java.util.Arrays;

import static com.tangwan.sortAlgorithm.HeapSort.buildMaxHeap;

/**
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName HeapSortTest.java
 * @Date 2016/10/29 21:03
 * @since JDK 1.8
 */
public class HeapSortTest {
    @Test
    public void test(){
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64 };
        System.out.println("排序之前："+ Arrays.toString(a));
        int arrayLength = a.length;
        // 循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            // 建堆
            buildMaxHeap(a, arrayLength - 1 - i);
            // 交换堆顶和最后一个元素
            HeapSort.swap(a, 0, arrayLength - 1 - i);
        }
        System.out.println("排序之后："+Arrays.toString(a));
    }
}
