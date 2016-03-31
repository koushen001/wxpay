package com.cike.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cike.entity.OrderConstant;

@WebServlet("/payResultServlet")
public class PayResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 判断是否支付成功，若支付成功，跳转至成功页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 实际项目中为查询订单状态,这儿做个简单的模拟,实际项目中请勿采用此方式
		PrintWriter pw = response.getWriter();
		pw.println(OrderConstant.RESULT);
		pw.flush();
		pw.close();

	}

	/**
	 * 扫码支付成功处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 实际项目中为更改订单状态,这儿做个简单的模拟,实际项目中请勿采用此方式
		OrderConstant.RESULT = 1;
		// 支付完成后，微信会把相关支付结果和用户信息发送给商户，商户需要接收处理，并返回应答。
		String rs = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
		PrintWriter pw = response.getWriter();
		pw.write(rs);
		pw.flush();
		pw.close();
		System.out.println("订单付款成功了");
	}

}
