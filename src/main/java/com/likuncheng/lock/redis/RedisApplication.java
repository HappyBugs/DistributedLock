package com.likuncheng.lock.redis;

public class RedisApplication implements Runnable {

	private RedisLockImpl redisLockImpl = new RedisLockImpl();

	private CreateOrderNumber createOrderNumber = new CreateOrderNumber();


	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new RedisApplication()).start();
		}
	}

	public void run() {
		try {
			Thread.sleep(500);
			// 获得锁
			if (redisLockImpl.lock()) {
				createOrder();
				redisLockImpl.unLock();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void run() {
//		try {
//			Thread.sleep(500);
//			createOrder();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	public void createOrder() {
		String createOrderNumber2 = createOrderNumber.createOrderNumber();
		System.out.println("ThreadName:" + Thread.currentThread().getName() + ",获得订单:" + createOrderNumber2);
	}

}
