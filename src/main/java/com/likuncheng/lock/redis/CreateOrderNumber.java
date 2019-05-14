package com.likuncheng.lock.redis;

import java.text.SimpleDateFormat;
import java.util.Date;

//创建订单号
public class CreateOrderNumber {
	
	private static Integer count = 0;

	//得到订单号
	public String createOrderNumber() {
		try {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String result = sd.format(new Date())+"+"+count++;
			return  result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
