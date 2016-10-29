package com.tangwan.lombok;

import org.junit.Test;

/**
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName LombokUserTest.java
 * @Date 2016/10/29 21:30
 * @since JDK 1.8
 */
public class LombokUserTest {
    @Test
    public void test(){
        LombokUser u0 = new LombokUser();
        //log.info("test the lombok bean");
        u0.setId("123");
        u0.setEmail("lovej2ee@126.com");
        u0.setName("tangwan");
        System.out.println(u0.toString());

        System.out.println("##############################");
        LombokUser u1 = new LombokUser("001", "tangwan", "lovej2ee@126.com");
        System.out.println(u1.toString());
        LombokUser u2 = new LombokUser("001", "tangwan", "lovej2ee@126.com");
        System.out.println(u1.equals(u2));
    }
}
