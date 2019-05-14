package com.likuncheng.lock.zookeeper;

public class ZookeeperApplication implements Runnable {

	private CreateOrderNumber createOrderNumber = new CreateOrderNumber();

	private ZookeeperAbstractLockImpl abstractLockImpl = new ZookeeperAbstractLockImpl();

	public static void main(String[] args) {
		System.out.println();
		System.err.println("====================模拟生成订单开始====================");
		for (int i = 0 ; i < 100 ; i++) {
			new Thread(new ZookeeperApplication()).start();
		}
	}

	public void run() {
		try {
			abstractLockImpl.lock();
			System.out.println();
			System.out.println("==================获得锁==================");
			createOrder();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			abstractLockImpl.unLock();
			System.out.println("==================释放锁==================");
			System.out.println();
		}
	}

	public void createOrder() {
		String number = createOrderNumber.createOrderNumber();
		System.out.println("ThreadName:" + Thread.currentThread().getName() + ",得到订单:" + number);
	}


}
