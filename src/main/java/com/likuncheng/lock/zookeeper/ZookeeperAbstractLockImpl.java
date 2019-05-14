package com.likuncheng.lock.zookeeper;


import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;


public class ZookeeperAbstractLockImpl extends ZookeeperAbstractLock {

	@Override
	boolean tryLock() {
		try {
			// ����һ����ʱ�ڵ�
			zkClient.createEphemeral(PATH);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	void waitLock() {
		IZkDataListener iZkDataListener = new IZkDataListener() {
			//���޸ĵ�ʱ��֪ͨ
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println("�ҽ�����");
			}

			//��ɾ�����¼�֪ͨ
			public void handleDataDeleted(String dataPath) throws Exception {
				if(countDownLatch != null) {
					//����
					countDownLatch.countDown();
				}
			}
		};
		//ע��ڵ���Ϣ���¼������У�
		zkClient.subscribeDataChanges(PATH, iZkDataListener);
		//�жϽڵ�ʱ���Ƿ���ڣ�
		if (zkClient.exists(PATH)) {
			countDownLatch = new CountDownLatch(1);
			try {
				countDownLatch.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ɾ������
		zkClient.unsubscribeDataChanges(PATH, iZkDataListener);
	}

}
