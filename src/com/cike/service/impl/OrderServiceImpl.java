package com.cike.service.impl;

import java.io.File;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import com.cike.entity.Order;
import com.cike.entity.OrderConstant;
import com.cike.entity.OrderReturn;
import com.cike.service.OrderService;
import com.cike.util.Config;
import com.cike.util.HttpUtil;
import com.cike.util.QRCode;
import com.cike.util.Sign;
import com.cike.util.XmlUtil;

/**
 * 
 * @ClassName: OrderServiceImpl
 * @Description: 订单业务处理类
 * @author: CIKE
 * @date: 2016年3月31日 下午2:21:20
 */
public class OrderServiceImpl implements OrderService {
	/**
	 * 
	 * @Title: placeOrder
	 * @Description: 统一下单
	 * @param body
	 *            商品描述
	 * @param out_trade_no
	 *            商户订单号
	 * @param total_fee
	 *            订单总金额，单位为分
	 * @return
	 * @throws Exception
	 * @return: OrderReturn
	 */
	@Override
	public OrderReturn placeOrder(String body, String out_trade_no, String total_fee) throws Exception {
		// 生成订单对象
		Order o = new Order();
		// 随机字符串
		String nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
		o.setAppid(Config.APPID);
		o.setBody(body);
		o.setMch_id(Config.MCH_ID);
		o.setNotify_url(Config.NOTIFY_URL);
		o.setOut_trade_no(out_trade_no);
		// 判断有没有输入订单总金额，没有输入默认1分钱
		if (total_fee != null && !total_fee.equals("")) {
			o.setTotal_fee(Integer.parseInt(total_fee));
		} else {
			o.setTotal_fee(1);
		}
		o.setNonce_str(nonce_str);
		o.setTrade_type(Config.TRADE_TYPE);
		o.setSpbill_create_ip(Config.SPBILL_CREATE_IP);
		SortedMap<Object, Object> p = new TreeMap<Object, Object>();
		p.put("appid", Config.APPID);
		p.put("mch_id", Config.MCH_ID);
		p.put("body", body);
		p.put("nonce_str", nonce_str);
		p.put("out_trade_no", out_trade_no);
		p.put("total_fee", total_fee);
		p.put("spbill_create_ip", Config.SPBILL_CREATE_IP);
		p.put("notify_url", Config.NOTIFY_URL);
		p.put("trade_type", Config.TRADE_TYPE);
		// 得到签名
		String sign = Sign.createSign("utf-8", p, Config.KEY);
		o.setSign(sign);
		// Object转换为XML
		String xml = XmlUtil.object2Xml(o, Order.class);
		// 统一下单地址
		String url = OrderConstant.PLACEANORDER;
		// 调用微信统一下单地址
		String returnXml = HttpUtil.sendPost(url, xml);
		// XML转换为Object
		OrderReturn or = (OrderReturn) XmlUtil.xml2Object(returnXml, OrderReturn.class);
		return or;
	}

	/**
	 * 
	 * @Title: createCode
	 * @Description: 生成支付二维码
	 * @param path
	 *            项目绝对路径
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@Override
	public String createCode(String path, OrderReturn orderReturn) throws Exception {
		File file = new File(path + "common/QRCode");
		if (!file.exists()) {
			file.mkdirs();
		}
		QRCode q = new QRCode();
		String fileName = UUID.randomUUID().toString();
		String filePath = "common/QRCode/" + fileName + ".png";
		String imgPath = path + filePath;
		q.encoderQRCode(orderReturn.getCode_url(), imgPath);
		return filePath;
	}

}
