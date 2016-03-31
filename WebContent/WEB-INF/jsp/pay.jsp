<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信支付</title>
</head>
<body>
	<img src="${code}">
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		/* 查询是否支付成功  */
		function payResult() {
			$.get("payResultServlet", function(data) {
				if (data == 1) {
					window.location.href = "paySuccess.jsp";
				}
			});
		}
		$(function() {
			/* 每隔2秒查一次 */
			window.setInterval("payResult()", 3000);
		});
	</script>
</body>
</html>