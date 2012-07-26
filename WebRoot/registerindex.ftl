<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
		function()
			{
			$("#register").click(function() {
			var userUsername = $("#userUsername").val();
			var userPassword = $("#userPassword").val();
			var userRepassword=$("#userRepassword").val();
			if(userUsername==""){
				alert("用户名不能为空");
			}else if(userUsername.length>20){
				alert("用户名长度不能超过20");
			}else if(userPassword==""){
				alert("密码不能为空");
			}else if(userPassword.length>20){
				alert("密码长度不能超过20");
			}else if(userPassword!=userRepassword){
				alert("两次密码不同,请重新输入")
			}else{
			$.post("register.action", {
				username : userUsername,
				password : userPassword
			}, function(data) {
				if(data==0){
					alert("该用户已被注册,请重新输入");
				}else{
					alert("恭喜您,注册成功");
				}
			});
			}
		});
		}
		)
</script>
</head>
<body>
	用户名:
	<input id="userUsername" type="text" />
	<br /> 密码:
	<input id="userPassword" type="password" />
	<br /> 重复密码:
	<input id="userRepassword" type="password" />
	<br />
	<input id="register" type="button" value="提交" />
</body>
</html>