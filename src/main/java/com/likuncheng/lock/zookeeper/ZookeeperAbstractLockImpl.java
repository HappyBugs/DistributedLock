package com.likuncheng.lock.zookeeper;


import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;


public class ZookeeperAbstractLockImpl extends ZookeeperAbstractLock {

	@Override
	boolean tryLock() {
		try {
			// 创建一个临时节点
			zkClient.createEphemeral(PATH);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	void waitLock() {
		IZkDataListener iZkDataListener = new IZkDataListener() {
			//被修改的时间通知
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println("我进来了");
			}

			//被删除的事件通知
			public void handleDataDeleted(String dataPath) throws Exception {
				if(countDownLatch != null) {
					//放行
					countDownLatch.countDown();
				}
			}
		};
		//注册节点信息到事件监听中？
		zkClient.subscribeDataChanges(PATH, iZkDataListener);
		//判断节点时候是否存在？
		if (zkClient.exists(PATH)) {
			countDownLatch = new CountDownLatch(1);
			try {
				countDownLatch.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 删除监听
		zkClient.unsubscribeDataChanges(PATH, iZkDataListener);
	}

}
