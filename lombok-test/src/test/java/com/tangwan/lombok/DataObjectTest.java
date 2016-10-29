package com.tangwan.lombok;

import org.junit.Test;

/**
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName DataObjectTest.java
 * @Date 2016/10/29 21:30
 * @since JDK 1.8
 */
public class DataObjectTest {
    @Test
    public void test(){
        DataObject d1 = new DataObject("tangwan","tangwan");
        System.out.println(d1);
        DataObject d2 = new DataObject("tangwan","tangwan");
        System.out.println(d2);

        System.out.println(d1.equals(d2));

        String abc = "abc";
        System.out.println(abc.hashCode());
    }
}
