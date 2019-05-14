package com.likuncheng.lock.zookeeper;

public class ZookeeperApplication implements Runnable {

	private CreateOrderNumber createOrderNumber = new CreateOrderNumber();

	private ZookeeperAbstractLockImpl abstractLockImpl = new ZookeeperAbstractLockImpl();

	public static void main(String[] args) {
		System.out.println();
		System.err.println("====================ģ�����ɶ�����ʼ====================");
		for (int i = 0 ; i < 100 ; i++) {
			new Thread(new ZookeeperApplication()).start();
		}
	}

	public void run() {
		try {
			abstractLockImpl.lock();
			System.out.println();
			System.out.println("==================�����==================");
			createOrder();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			abstractLockImpl.unLock();
			System.out.println("==================�ͷ���==================");
			System.out.println();
		}
	}

	public void createOrder() {
		String number = createOrderNumber.createOrderNumber();
		System.out.println("ThreadName:" + Thread.currentThread().getName() + ",�õ�����:" + number);
	}


}
