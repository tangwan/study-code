package com.tangwan.jedisutils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.SortingParams;

public interface RedisCacheUtil{

	/**
	 * 获取值
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key 			key键
	 * @return value 		缓存value
	 */
	public String get(String key);
	
	/**
	 * 获取值（泛型对象）
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param classz		泛型对象class
	 * @return 泛型对象
	 */
	public <T> T get(String key, Class<T> clazz);
	
	/**
	 * 获取集合值（泛型对象）
	 * @date 2016年05月26日
	 * @author liliangliang
	 * @param key			key键
	 * @param classz		泛型对象class
	 * @return 泛型对象 集合
	 */
	public <T> List<T> getList(String key, Class<T> clazz);
	
	/**
	 * 删除key
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @return				删除key的数量
	 */
	public Long del(String key);
	
	/**
	 * 是否存在key
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @return				true or false
	 */
	public Boolean exists(String key);
	
	/**
	 * 设置key 过期时间
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param seconds		时间（单位秒）
	 * @return				设置成功的数量
	 */
	public Long expire(String key, int seconds);
	
	/**
	 * 剩余过期时间
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @return				剩余过期时间  -1则永不过期（单位 秒）
	 */
	public Long ttl(String key);
	
	/**
	 * 赋值
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param value			value内容
	 * @return				标识 OK:成功
	 */
	public String set(String key, String value);
	
	/**
	 * 赋值 并设置过期时间
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param seconds		时间（单位秒）
	 * @param value			value内容
	 * @return				标识 OK:成功
	 */
	public String setex(String key, int seconds, String value);
	
	/**
	 * 赋值 泛型
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param value			泛型
	 * @return				标识 OK:成功
	 */
	public <T> String set(String key, T value);
	
	/**
	 * 赋值 泛型 并设置过期时间 
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param seconds		时间（单位秒）
	 * @param value			泛型value内容
	 * @return				标识 OK:成功
	 */
	public <T> String setex(String key, int seconds, T value);

	/**
	 * 通过key获取值的长度（仅限字符串值）
	 * @date 2016年04月20日
	 * @author liliangliang
	 * @param key			key键
	 * @return key的值的字符长度
	 */
	public Long strlen(String key);
	
	/**
	 * 追加
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param value			追加value内容
	 * @return				总value长度
	 */
	public Long append(String key, String value);
	
	/**
	 * 获取key 的散列值
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @return				散列值
	 */
	public String hget(String key, String field);
	
	/**
	 * 获取key 的散列值（泛型）
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @param clazz			泛型对象
	 * @return				泛型对象
	 */
	public <T> T hget(String key, String field, Class<T> clazz);
	
	/**
	 * 获取key 的散列值集合（泛型）
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @param clazz			泛型对象
	 * @return				泛型对象集合
	 */
	public <T> List<T> hgetList(String key, String field, Class<T> clazz);
	
	/**
	 * @desc 返回哈希表 key 中，所有的域和值。
	 * @param key
	 * @return map,key关联的元素。若 key 不存在，返回null
	 * @date 2016-03-21
	 * @author zhangxiangzhou
	 */
	Map<String, String> hgetAll(String key);
	
	/**
	 * 赋值 key的散列及值
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @param value			内容
	 * @return				0：已存在并被更新 1：赋值成功 -1 标识出错
	 */
	public Long hset(String key, String field, String value);
	
	/**
	 * 赋值 key的散列及值（泛型）
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @param value			泛型对象
	 * @return				0：已存在并被更新 1：赋值成功 -1 标识出错
	 */
	public <T> Long hset(String key, String field, T value);
	
	/**
	 * 散列递增(按步长)
	 * @date 2016年04月20日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @return 递增后的值（默认步长1递增）
	 */
	public Long hincrBy(String key, String field);
	
	/**
	 * 散列递增(按步长)
	 * @date 2016年04月20日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @param value			步长（long类型）
	 * @return 递增后的值（按步长递增）
	 */
	public Long hincrBy(String key, String field, long value);
	
	/**
	 * 散列递增(按步长)
	 * @date 2016年04月20日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @param value			步长（double类型）
	 * @return 递增后的值（按步长递增）
	 */
	public Double hincrByFloat(String key, String field, double value);
	
	/**
	 * 散列是否存在key中
	 * @date 2016年04月20日
	 * @author liliangliang
	 * @param key			key键
	 * @param field			散列
	 * @return true 存在， false 不存在
	 */
	public Boolean hexists(String key, String field);
	
	/**
	 * 获取key中所有的值集合
	 * @date 2016年04月20日
	 * @author liliangliang
	 * @param key			key键
	 * @return 值集合
	 */
	public List<String> hvals(String key);
	
	/**
	 * 获取key中散列的集合
	 * @date 2016年04月20日
	 * @author liliangliang
	 * @param key			key键
	 * @return 散列集合
	 */
	public Set<String> hkeys(String key);
	
	/**
	 * 获取 HashMap 散列一个或多个值
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param fields		一个或多个散列
	 * @return				散列的值
	 */
	public List<String> hmget(String key, String... fields);

	/**
	 * 存储 HashMap
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param hash			hash对象
	 * @return				标识 OK:成功
	 */
	public String hmset(String key, Map<String, String> hash);
	
	/**
	 * 获取 hash 散列数量
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @return				key 散列数量
	 */
	public Long hlen(String key);
	
	/**
	 * 删除 hash 散列
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			key键
	 * @param fields		散列
	 * @return				成功删除数量
	 */
	public Long hdel(String key, String... fields);
	
	/**
	 * 添加到Set
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key		Set key键
	 * @param members	元素
	 * @return 			成功添加元素数量
	 */
	public Long sadd(String key, String... members);
	
	/**
	 * 遍历Set集合
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key		Set key键
	 * @return 			元素集合
	 */
	public Set<String> smembers(String key);
	
	/**
	 * 移除Set元素
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key		Set key键
	 * @param members	元素
	 * @return	 		移除数量
	 */
	public Long srem(String key, String... members);
	
	/***
	 * 返回Set长度
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key		Set key键
	 * @return 			Set长度
	 */
	public Long scard(String key);

	/**
	 * 元素是否包含在Set
	 * @date 2015年10月25日
	 * @author liliangliang
	 * @param key			Set key键
	 * @param member		元素
	 * @return 				true or false
	 */
	public Boolean sismember(String key, String member);
	
	/**
	 * 递增
	 * @date 2015年10月28日
	 * @author liliangliang
	 * @param key		key键
	 * @return 递增后的值（key 不存在则从0开始递增， 存在则在原值上递增）
	 */
	public Long incr(String key);

	/**
	 * 递增(按步长)
	 * @date 2015年10月28日
	 * @author liliangliang
	 * @param key			key键
	 * @param integer		步长
	 * @return 递增后的值（按步长递增）
	 */
	public Long incrBy(String key, long integer);
	
	/**
	 * 递减
	 * @date 2015年10月28日
	 * @author liliangliang
	 * @param key			key键
	 * @return 递减后的值（key 不存在则从0开始递减， 存在则在原值上递减）
	 */
	public Long decr(String key);
	
	/**
	 * 递减(按步长)
	 * @date 2015年10月28日
	 * @author liliangliang
	 * @param key			key键
	 * @param integer		步长
	 * @return 递减后的值（按步长递增）
	 */
	public Long decrBy(String key, long integer);
	
	/**
	 * 列表 表头插入元素
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param strings		元素
	 * @return 列表长度
	 */
	public Long lpush(String key, String... strings);
	
	/**
	 * 列表 表头插入元素（泛型）
	 * @date 2016年05月26日
	 * @author liliangliang
	 * @param key			key键
	 * @param values		泛型对象集合
	 * @return 列表长度
	 */
	@SuppressWarnings("unchecked")
	public <T> Long lpush(String key, T... values);
	
	/**
	 * @desc 移除并返回列表 key 的头元素。
	 * @param key
	 * @param dbId	数据库ID
	 * @return 头元素
	 * @date 2016-03-21
	 * @author zhangxiangzhou
	 */
	public String lpop(String key);
	
	/**
	 * 列表 表未插入元素
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param strings		列表元素
	 * @return 列表长度
	 */
	public Long rpush(String key, String... strings);
	
	/**
	 * 列表 表未插入元素（泛型）
	 * @date 2016年05月26日
	 * @author liliangliang
	 * @param key			key键
	 * @param values		泛型对象集合
	 * @return 列表长度
	 */
	@SuppressWarnings("unchecked")
	public <T> Long rpush(String key, T... values);
	
	/**
	 * 获取表长度
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @return 列表长度
	 */
	public Long llen(String key);
	
	/**
	 * 获取列表元素集合(指定区间)
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param start			起始下标
	 * @param end			结束下标
	 * @return 指定区间元素列表
	 */
	public List<String> lrange(String key, long start, long end);
	
	/**
	 * 获取列表元素集合（泛型）(指定区间)
	 * @date 2016年05月26日
	 * @author liliangliang
	 * @param key			key键
	 * @param start			起始下标
	 * @param end			结束下标
	 * @param classz		对象.class
	 * @return 指定区间元素列表
	 */
	public <T> List<T> lrange(String key, long start, long end, Class<T> classz);
	
	/**
	 * 移出元素
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param value			移除的元素
	 * @return 被移除的数量
	 */
	public Long lrem(String key, String value);
	
	/**
	 * 移除元素（指定数量移除）
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param count			count > 0：从表头移除与value相等的count个元素，count < 0：从表尾移除与value相等的count个元素，count = 0：移除所有与value相等的元素
	 * @param value			元素
	 * @return 被移除的数量
	 */
	public Long lrem(String key, long count, String value);
	
	/**
	 * 替换列表元素(指定下标)
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param index			需要替换的下标
	 * @param value			元素
	 * @return 成功：OK，否则 错误信息
	 */
	public String lset(String key, long index, String value);
	
	/**
	 * 修剪列表(保留指定区间元素)
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param start			保留起始下标
	 * @param end			保留结束下标
	 * @return 成功：OK，否则错误信息
	 */
	public String ltrim(String key, long start, long end);
	
	/**
	 * 获取列表下标元素
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @param index			下标
	 * @return 存在：列表中下标的元素 不存在：nil
	 */
	public String lindex(String key, long index);
	
	/**
	 * 所存储的值的类型
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			kye键
	 * @return none(key不存在) string(字符串) list(列表) set(集合) zset(有序集) hash(哈希表)
	 */
	public String type(String key);
	
	/**
	 * 排序
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key			key键
	 * @return 排序后的集合（默认从小到大）
	 */
	public List<String> sort(String key);
	
	/**
	 * 有参数的排序
	 * @date 2015年11月19日
	 * @author liliangliang
	 * @param key				key键
	 * @param sortingParameters	参数集合
	 * @return 排序后的集合
	 */
	public List<String> sort(String key, SortingParams sortingParameters);
}