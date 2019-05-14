package com.likuncheng.lock.zookeeper;

public interface ZookeeperLock {
	
	//»ñµÃËø
	public void lock();
	
	//ÊÍ·ÅËø
	public void unLock();

}
