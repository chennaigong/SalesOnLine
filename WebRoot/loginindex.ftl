<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				$("#submit").click(function()
				{
					var username=$("#username").val();
					var password=$("#password").val();
					var role=$("input:radio[name='role']:checked").val();
					$.post("doLogin.action",{username:username,password:password,role:role},
					 function(data){
					 	if(data!=0)
					 	{
					 		window.location.href=data+"?username="+username;
					 	}
					 	else
					 	{
					 		alert("用户名密码错误");
					 	}
					 });
				});
			}
		)
	</script>
  </head>
  
  <body>
	  	用户名:<input type="text" id="username"/><br/>
	  	密码:<input type="text" id="password"/><br/>
	  	<input type="radio" name="role" checked="checked" value="普通用户">普通用户</input>
	  	<input type="radio" name="role" value="管理员">管理员</input><br/>
	  	<input type="button" value="提交" id="submit"/>
  </body>
</html>
