package com.cike.service;

import com.cike.entity.OrderReturn;


/**
 * 
 * @ClassName: OrderService
 * @Description: 订单业务接口
 * @author: CIKE
 * @date: 2016年3月31日 下午2:20:43
 */
public interface OrderService {
	/**
	 * 
	 * @Title: placeOrder
	 * @Description: 统一下单
	 * @param body 商品描述
	 * @param out_trade_no 商户订单号
	 * @param total_fee 订单总金额，单位为分
	 * @return
	 * @throws Exception
	 * @return: OrderReturn
	 */
	OrderReturn placeOrder(String body, String out_trade_no,String total_fee) throws Exception;
	/**
	 * 
	 * @Title: createCode
	 * @Description: 生成支付二维码
	 * @param path 项目绝对路径
	 * @param orderReturn 
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	String createCode(String path, OrderReturn orderReturn) throws Exception;
}
