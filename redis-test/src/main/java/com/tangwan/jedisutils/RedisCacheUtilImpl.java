package com.tangwan.jedisutils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

//import org.springframework.stereotype.Service;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;
import com.alibaba.fastjson.JSON;


//@Service
public class RedisCacheUtilImpl implements RedisCacheUtil {
	
	/** JedisCluster 对象 */
	private static JedisCluster jedis;
	
	/** 读取redis配置文件 */
	private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("rediscache");
			jedis = getJedisCluster();
		} catch (Exception e) {
			jedis = null;
		}
	}
	
	/**
	 * 获取JedisCluster对象
	 */
	private static JedisCluster getJedisCluster(){
		JedisPoolConfig config = new JedisPoolConfig();
		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		config.setBlockWhenExhausted(true);
		//最大空闲连接数, 默认8个
		config.setMaxIdle(Integer.parseInt(rb.getString("redis.server.maxidle")));
		//最大连接数, 默认8个
		config.setMaxTotal(Integer.parseInt(rb.getString("redis.server.maxtotal")));
		//获取连接时的最大等待毫秒数
		config.setMaxWaitMillis(Long.parseLong(rb.getString("redis.server.maxwaitmillis")));
		//在获取连接的时候检查有效性
        config.setTestOnBorrow("true".equalsIgnoreCase(rb.getString("redis.server.testonborrow")));
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		String[] ips = rb.getString("redis.server.addrs").split(" ");
		String[] ports = rb.getString("redis.server.ports").split(" ");
		for (int i = 0; i < ips.length; i++) {
			nodes.add(new HostAndPort(ips[i], Integer.parseInt(ports[i])));
		}
		//返回JedisCluster对象
		return new JedisCluster(nodes, config);
	}

	@Override
	public String get(String key) {
		return jedis.get(key);
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		return JSON.parseObject(get(key), clazz);
	}
	
	@Override
	public <T> List<T> getList(String key, Class<T> clazz) {
		return JSON.parseArray(get(key), clazz);
	}

	@Override
	public Long del(String key) {
		return jedis.del(key);
	}

	@Override
	public Boolean exists(String key) {
		return jedis.exists(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedis.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return jedis.ttl(key);
	}

	@Override
	public String set(String key, String value) {
		return jedis.set(key, value);
	}

	@Override
	public String setex(String key, int seconds, String value) {
		return jedis.setex(key, seconds, value);
	}

	@Override
	public <T> String set(String key, T value) {
		return jedis.set(key, JSON.toJSONString(value));
	}

	@Override
	public <T> String setex(String key, int seconds, T value) {
		return jedis.setex(key, seconds, JSON.toJSONString(value));
	}
	
	@Override
	public Long strlen(String key) {
		return jedis.strlen(key);
	}

	@Override
	public Long append(String key, String value) {
		return jedis.append(key, value);
	}

	@Override
	public String hget(String key, String field) {
		return jedis.hget(key, field);
	}

	@Override
	public <T> T hget(String key, String field, Class<T> clazz) {
		return JSON.parseObject(jedis.hget(key, field), clazz);
	}
	
	@Override
	public <T> List<T> hgetList(String key, String field, Class<T> clazz) {
		return JSON.parseArray(jedis.hget(key, field), clazz);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		return jedis.hgetAll(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedis.hset(key, field, value);
	}

	@Override
	public <T> Long hset(String key, String field, T value) {
		return jedis.hset(key, field, JSON.toJSONString(value));
	}
	
	@Override
	public Long hincrBy(String key, String field) {
		return hincrBy(key, field, 1);
	}
	
	@Override
	public Long hincrBy(String key, String field, long value) {
		return jedis.hincrBy(key, field, value);
	}
	
	@Override
	public Double hincrByFloat(String key, String field, double value) {
		return jedis.hincrByFloat(key, field, value);
	}
	
	@Override
	public Boolean hexists(String key, String field) {
		return jedis.hexists(key, field);
	}
	
	@Override
	public List<String> hvals(String key) {
		return jedis.hvals(key);
	}
	
	@Override
	public Set<String> hkeys(String key) {
		return jedis.hkeys(key);
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		return jedis.hmget(key, fields);
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		return jedis.hmset(key, hash);
	}

	@Override
	public Long hlen(String key) {
		return jedis.hlen(key);
	}

	@Override
	public Long hdel(String key, String... fields) {
		return jedis.hdel(key, fields);
	}

	@Override
	public Long sadd(String key, String... members) {
		return jedis.sadd(key, members);
	}

	@Override
	public Set<String> smembers(String key) {
		return jedis.smembers(key);
	}

	@Override
	public Long srem(String key, String... members) {
		return jedis.srem(key, members);
	}

	@Override
	public Long scard(String key) {
		return jedis.scard(key);
	}

	@Override
	public Boolean sismember(String key, String member) {
		return jedis.sismember(key, member);
	}

	@Override
	public Long incr(String key) {
		return jedis.incr(key);
	}

	@Override
	public Long incrBy(String key, long integer) {
		return jedis.incrBy(key, integer);
	}

	@Override
	public Long decr(String key) {
		return jedis.decr(key);
	}

	@Override
	public Long decrBy(String key, long integer) {
		return jedis.decrBy(key, integer);
	}

	@Override
	public Long lpush(String key, String... strings) {
		return jedis.lpush(key, strings);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Long lpush(String key, T... values) {
		String str[] = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			str[i] = JSON.toJSONString(values[i]);
		}
		return jedis.lpush(key, str);
	}

	@Override
	public String lpop(String key) {
		return jedis.lpop(key);
	}

	@Override
	public Long rpush(String key, String... strings) {
		return jedis.rpush(key, strings);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Long rpush(String key, T... values) {
		String str[] = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			str[i] = JSON.toJSONString(values[i]);
		}
		return jedis.rpush(key, str);
	}

	@Override
	public Long llen(String key) {
		return jedis.llen(key);
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		return jedis.lrange(key, start, end);
	}
	
	@Override
	public <T> List<T> lrange(String key, long start, long end, Class<T> classz) {
		List<String> list = jedis.lrange(key, start, end);
		if(list == null){
			return null;
		}
		List<T> newList = new ArrayList<T>(list.size());
		for(String str : list){
			newList.add(JSON.parseObject(str, classz));
		}
		return newList;
	}

	@Override
	public Long lrem(String key, String value) {
		return jedis.lrem(key, 0, value);
	}

	@Override
	public Long lrem(String key, long count, String value) {
		return jedis.lrem(key, count, value);
	}

	@Override
	public String lset(String key, long index, String value) {
		return jedis.lset(key, index, value);
	}

	@Override
	public String ltrim(String key, long start, long end) {
		return jedis.ltrim(key, start, end);
	}

	@Override
	public String lindex(String key, long index) {
		return jedis.lindex(key, index);
	}

	@Override
	public String type(String key) {
		return jedis.type(key);
	}

	@Override
	public List<String> sort(String key) {
		return jedis.sort(key);
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		return jedis.sort(key, sortingParameters);
	}
}