package com.tangwan.csv;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName CSVUtilTest.java
 * @Date 2016/11/19 23:12
 * @since JDK 1.8
 */
public class CSVUtilTest {
    @Test
    public void test1() throws Exception{
        List<User> data = new ArrayList<>();
        User u1 = new User("1","tangwan",22,121212.0);
        User u2 = new User("2","唐婉",0,121212.0);
        User u3 = new User("3","黄甜",22,121212.0);
        User u4 = new User("4","赵看",22,121212.0);
        User u5 = new User("5","子怡",22,121212.0);
        data.add(u1);
        data.add(u2);
        data.add(u3);
        data.add(u4);
        data.add(u5);
        boolean flag = CSVUtil.export("D:/test/tangwan.csv",data);
        System.out.println(flag);
        //assert flag;
    }
}
