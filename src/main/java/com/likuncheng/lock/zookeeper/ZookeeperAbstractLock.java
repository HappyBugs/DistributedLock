package com.likuncheng.lock.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements ZookeeperLock {
	
	//���ӵ�ַ
	private static final String CONNECTPATH = "127.0.0.1:2181";
	
	//��ʱʱ��Ϊ5000����
	private static final int TIMEOUT = 5000;
	
	//����zkclient����
	protected ZkClient zkClient = new ZkClient(CONNECTPATH,TIMEOUT);
	
	//������ʱ�ڵ��path
	protected static final String PATH = "/path";
	
	//�ź���
	protected static CountDownLatch countDownLatch = null;

	//����
	public void lock() {
		if(! tryLock()) {
			//���û�л���� �ͽ��еȴ� �ȴ�֮����µ���lock()����
//			waitLock();
			lock();
		}
	}

	//�ͷ���
	public void unLock() {
		if(zkClient != null) {
			//�ر����� ��Ϊ�Ǵ�������ʱ�ڵ��ѹرվ�û���� ���൱���ͷ�����
			zkClient.close();
			System.out.println("################�ͷ����ɹ�################");
		}
		
	}
	
	//�ж��Ƿ��ȡ�� ��÷���true
	abstract boolean tryLock();
	
	//�ȴ�
	abstract void waitLock();

}
