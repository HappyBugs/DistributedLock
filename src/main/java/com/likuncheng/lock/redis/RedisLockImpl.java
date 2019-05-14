package com.likuncheng.lock.redis;

import java.util.Collections;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisLockImpl implements Redislock {

//	private static final String CONNECTPATH = "127.0.0.1";
//
//	private static final Integer PORT = 6379;
	
	private JedisShardInfo jedisShardInfo = new JedisShardInfo("redis://localhost:6379/15");

	private Jedis jedis = null;

	private static final String KEY = "redisLock";

	private static final String VALUE = "redisLock";

	private static final long TIMEOUT = 5000;

	private static final String EXPX = "PX";

	private static final String NXXX = "NX";

	public boolean lock() {
		try {
			jedisShardInfo.setPassword("123456");
			jedis = new Jedis(jedisShardInfo);
			String set = jedis.set(KEY, VALUE, NXXX, EXPX, TIMEOUT);
			if(set != null && set.length() >= 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void unLock() {
		try {
			String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
			Object eval = jedis.eval(script, Collections.singletonList(KEY), Collections.singletonList(VALUE));
		} catch (Exception e) {
		}finally {
			jedis.close();
		}
	}

}
