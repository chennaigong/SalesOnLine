<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户欢迎界面</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
  </head>
  
  <body>
  	欢迎您:${username}
  	<form action="admin/tradeIndex.action" method="POST"/>
	  	<input type="hidden" name="username" value="${username}"/>
	  	<input type="submit" value="查看订单"/>
  	</form>
  	<form action="admin/rateIndex.action" method="POST"/>
  		<input type="hidden" name="username" value="${username}"/>
  		<input type="submit" value="查看评价"/>
  	</form>
  </body>
</html>
