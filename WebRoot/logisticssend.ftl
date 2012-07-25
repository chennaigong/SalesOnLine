<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发货</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script>
		
		$(document).ready
		(
			function()
			{
				var total=3;
				var inter=setInterval
				(
					function()
					{
						total--;
						$("#result").html(total);
						if(total<=0)
						{
							clearInterval(inter);
							window.location.href="userIndex.action"
						}
					},1000
				);
			}
		)
	</script>
  </head>
  
  <body>
  	发货成功，系统将在<span id="result">3</span>秒后自动跳转，<a href="userIndex.action">直接跳转</a>
  </body>
</html>
