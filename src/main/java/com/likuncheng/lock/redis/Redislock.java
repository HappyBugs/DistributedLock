package com.likuncheng.lock.redis;


public interface Redislock {
	
	public boolean lock();
	
	public void unLock();
	

}
