package com.likuncheng.lock.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements ZookeeperLock {
	
	//连接地址
	private static final String CONNECTPATH = "127.0.0.1:2181";
	
	//超时时间为5000毫秒
	private static final int TIMEOUT = 5000;
	
	//创建zkclient对象
	protected ZkClient zkClient = new ZkClient(CONNECTPATH,TIMEOUT);
	
	//创建临时节点的path
	protected static final String PATH = "/path";
	
	//信号量
	protected static CountDownLatch countDownLatch = null;

	//上锁
	public void lock() {
		if(! tryLock()) {
			//如果没有获得锁 就进行等待 等待之后从新调用lock()方法
//			waitLock();
			lock();
		}
	}

	//释放锁
	public void unLock() {
		if(zkClient != null) {
			//关闭连接 因为是创建的临时节点已关闭就没得了 就相当于释放了锁
			zkClient.close();
			System.out.println("################释放锁成功################");
		}
		
	}
	
	//判断是否获取锁 获得返回true
	abstract boolean tryLock();
	
	//等待
	abstract void waitLock();

}
