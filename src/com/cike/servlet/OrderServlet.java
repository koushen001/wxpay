package com.cike.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cike.entity.OrderReturn;
import com.cike.service.OrderService;
import com.cike.service.impl.OrderServiceImpl;

@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * 下单生成支付二维码
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			OrderService orderService = new OrderServiceImpl();
			// 商品描述
			String body = request.getParameter("body");
			// 商户订单号
			String out_trade_no = request.getParameter("out_trade_no");
			// 订单总金额，单位为分
			String total_fee = request.getParameter("total_fee");
			// 统一下单
			OrderReturn orderReturn = orderService.placeOrder(body, out_trade_no, total_fee);
			// 创建支付二维码
			String path = request.getSession().getServletContext().getRealPath("/");
			String code = orderService.createCode(path, orderReturn);
			request.setAttribute("code", code);
			request.getRequestDispatcher("/WEB-INF/jsp/pay.jsp").forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
