package com.tangwan.csv;

/**
 * @author Name:tangwan  Mail:lovej2ee@126.com
 * @version V1.0
 * @FileName User.java
 * @Date 2016/11/19 23:12
 * @since JDK 1.8
 */
public class User {
    private String id;
    private String name;
    private int age;
    private double money;

    public User() {
    }

    public User(String id, String name, int age, double money) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
