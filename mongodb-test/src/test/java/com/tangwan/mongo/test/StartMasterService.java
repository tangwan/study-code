package com.tangwan.mongo.test;

import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tangwan.mongoutil.MongoManager;

public class StartMasterService {
	/**
	 * 保存
	 * @Method：() 
	 * @throws Exception
	 */
	@Test
	public void master() throws Exception {
		MongoManager manager=new MongoManager();
		DBObject log = new BasicDBObject();
		log.put("id", "00002");
		log.put("logInfo", "日志2");
	    manager.save( log); 
		manager.close();
		System.out.println("发送成功啦");
	}
	
	/**
	 * 读取
	 * @Method：()
	 */
	@Test
	public void test2(){
		MongoManager manager=new MongoManager();
		DBObject query=new  BasicDBObject();
		query.put("id", "00002");
		DBObject fields = new BasicDBObject();
	    List<DBObject> result1 = manager.find(query, fields, 0);
		System.out.println(result1);
	    System.out.println("取出成功啦");
		manager.close();
	}
}
