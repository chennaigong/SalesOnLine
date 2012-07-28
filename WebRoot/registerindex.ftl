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
			var userUsername = $("#userUsername");
			var userPassword = $("#userPassword");
			var userRepassword=$("#userRepassword");
			if(userUsername.val()==""){
				alert("用户名不能为空");
				userUsername.focus();
			}else if(userUsername.val().length>20){
				alert("用户名长度不能超过20");
				userUsername.focus();
			}else if(userPassword.val()==""){
				alert("密码不能为空");
				userPassword.focus();
			}else if(userPassword.val().length>20){
				alert("密码长度不能超过20");
				userPassword.focus();
			}else if(userPassword.val()!=userRepassword.val()){
				alert("两次密码不同,请重新输入")
				userRepassword.focus();
			}else
			{
				$.post("doRegister.action", {
					username : userUsername.val(),
					password : userPassword.val()
				}, function(data) {
					if(data==0){
						alert("该用户已被注册,请重新输入");
					}else{
						alert("恭喜您,注册成功");
						window.location.href="loginIndex.action";
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