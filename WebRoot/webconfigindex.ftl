<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网站配置</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("webconfigList.action", function(data) {
				 alert(data)
				 	
				 });
			}
		)
	</script>
  </head>
  <body>
  	更新开始时间(没数据)：<input type="text" name="defaultTime" /><br/>
  	同步的间隔时间：<input type="text"/><br/>
  	<input type="button" value="保存"/>
  </body>
</html>
