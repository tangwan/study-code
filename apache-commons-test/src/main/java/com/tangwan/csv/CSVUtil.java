package com.tangwan.csv;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName CSVUtil.java
 * @Date 2016/11/19 22:50
 * @since JDK 1.8
 */
public class CSVUtil {
    public static <T> boolean export(String filePath, List<T> data) throws Exception{
        File file = new File(filePath);
        for (T t: data ) {
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                String name = f.getName(); // 获取属性的名字
                System.out.println(name);
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String type = f.getGenericType().toString();
                System.out.println(type);// 获取类型
                // 如果type是类类型，则前面包含"class "，后面跟类名
                if (type.equals("class java.lang.String")) {
                    Method m = t.getClass().getMethod("get" + name);
                    // 调用getter方法获取属性值
                    String value = (String) m.invoke(t);
                    System.out.println(value);
                    if (value == null) {
                        m = t.getClass().getMethod("set"+name,String.class);
                        m.invoke(t, "");
                    }
                }
                if (type.equals("int")) {
                    Method m = t.getClass().getMethod("get" + name);
                    // 调用getter方法获取属性值
                    int value = (int) m.invoke(t);
                    System.out.println(value);
                    /*if (value == null) {
                        m = t.getClass().getMethod("set"+name,String.class);
                        m.invoke(t, "");
                    }*/
                    m = t.getClass().getMethod("set"+name,String.class);
                    m.invoke(t, "");
                }
                if (type.equals("double")) {
                    Method m = t.getClass().getMethod("get" + name);
                    // 调用getter方法获取属性值
                    Double value = (Double) m.invoke(t);
                    System.out.println(value);
                    if (value == null) {
                        m = t.getClass().getMethod("set"+name,String.class);
                        m.invoke(t, "");
                    }
                }
            }
        }
        return false;
    }
}
