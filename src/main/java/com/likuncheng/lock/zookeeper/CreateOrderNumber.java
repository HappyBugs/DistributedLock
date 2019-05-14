package com.likuncheng.lock.zookeeper;

import java.text.SimpleDateFormat;
import java.util.Date;

//����������
public class CreateOrderNumber {
	
	private static Integer count = 0;

	//�õ�������
	public String createOrderNumber() {
		try {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			return  sd.format(new Date())+"+"+count++;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
