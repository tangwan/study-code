package com.tangwan.jedis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

public class JedisTest {
private Jedis jedis; 
    
    @Before
    public void setup() {
        jedis = new Jedis("172.29.1.102", 6379);
        //权限认证(不设置密码不需要)
        //jedis.auth("123456");  
    }
    
    /**
     * 字符串操作
     * @Method：()
     */
    @Test
    public void testString() {
        //-----添加数据----------  
        jedis.set("name1","zhangsan");//向key-->name中放入了value-->zhangsan  
        System.out.println(jedis.get("name"));//执行结果：zhangsan  
        
        jedis.append("name1", " is my lover"); //拼接
        System.out.println(jedis.get("name")); 
        
        jedis.del("name1");  //删除某个键
        System.out.println(jedis.get("name"));//取出null
        
        //设置多个键值对
        jedis.mset("name1","liuling","age","23","qq","476777389");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
    }
}
