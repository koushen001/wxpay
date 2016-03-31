<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信支付</title>
</head>
<body>
<form action="orderServlet" method="post">
<table>
	<tr>
		<td>订单号：</td>
		<td><input type="text" name="out_trade_no"/></td>
	</tr>
	<tr>
		<td>订单总金额（分）：</td>
		<td><input type="text" name="total_fee"/></td>
	</tr>
	<tr>
		<td>商品描述：</td>
		<td><input type="text" name="body"/></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="提交"/></td>
	</tr>
</table>
</form>
</body>
</html>