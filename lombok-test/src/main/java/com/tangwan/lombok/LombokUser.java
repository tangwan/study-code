package com.tangwan.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

//添加set get方法,无参构造,全参构造
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j
public @Data class LombokUser {
	private String id = null;
	private String name = null;
	private String email = null;

	
	public static void main(String[] args) {
		log.info("test the lombok bean");

		LombokUser u0 = new LombokUser();
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
