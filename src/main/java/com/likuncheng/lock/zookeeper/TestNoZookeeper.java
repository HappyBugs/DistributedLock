package com.likuncheng.lock.zookeeper;


//没有加入分布式锁的情况下
public class TestNoZookeeper implements Runnable{
	
	private CreateOrderNumber createOrderNumber = new CreateOrderNumber();
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new TestNoZookeeper()).start();
		}
	}

	public void run() {
		try {
			Thread.sleep(500);
			createOrder();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void createOrder() {
		String createOrderNumber2 = createOrderNumber.createOrderNumber();
		System.out.println("ThreadName:"+Thread.currentThread().getName()+"，得到订单:"+createOrderNumber2);
	}

}
