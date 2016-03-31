package com.cike.entity;

/**
 * @ClassName: OrderConstant
 * @Description: 订单接口地址
 * @author: CIKE
 * @date: 2016年3月31日 下午1:53:30
 */
public class OrderConstant {
	// 统一下单
	public static final String PLACEANORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 查询订单
	public static final String QUERYORDER = "https://api.mch.weixin.qq.com/pay/orderquery";

	public static Integer RESULT = 0;
}
