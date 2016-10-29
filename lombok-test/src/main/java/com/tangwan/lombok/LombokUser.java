package com.tangwan.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

//添加set get方法,无参构造,全参构造
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j
public @Data class LombokUser {
	private String id = null;
	private String name = null;
	private String email = null;
}
