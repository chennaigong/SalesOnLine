<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="CSS/login.css" rel="stylesheet" type="text/css"/>
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
					 		window.location.href=data;
					 	}
					 	else
					 	{
					 		alert("用户名密码错误");
					 	}
					 });
				});
				$("#register").click(function()
				{
					window.location.href="registerIndex.action";
				});
			}
		)
	</script>
  </head>
  
  <body>
  	<table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td align="center">
	      <table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td>&nbsp;</td>
	          <td width="950px" style="padding-left:45px;">
	            <div class="main">
	              <table width="35%"  height="44%" border="0" cellspacing="0" cellpadding="0" style="margin:210px 0 180px 285px;">
	                <tr>
	                  <td colspan="3" align="center"><span class="title_span"><img src="IMAGES/title.png"/></span></td>
	                <tr>
	                <tr>
	                  <td width="52px;"><label>用户名:</label></td>
					  <td colspan="2"><input id="username" type="text" /></td>
	                <tr>
	                  <td><label>密码: &nbsp;&nbsp;</label></td>
					  <td colspan="2"><input type="password" id="password"/></td>
	                </tr>
	                <tr>
	                  <td><label>验证码:</label></td>
					  <td class="yanzhengma"><input type="text" /></td>
	                  <td><span class="img_span"><img src="IMAGES/yanzhengma.jpg"/></span></td>
	                </tr>
	                <tr height="18px;">
	                  <td colspan="3" align="center">
	                    <input type="radio" name="role" checked="checked" value="普通用户">普通用户</input>
	  					<input type="radio" name="role" value="管理员">管理员</input><br/>
	                  </td>
	                </tr>
	                <tr>
	                  <td colspan="3" align="center">
	                  	<span class="zhuce" ><input type="button" value="" id="register"></input></span>
	                  	<span class="denglu"><input type="button" value="" id="submit"></input></span>
	                  </td>
	                </tr>
	              </table>
	            </div>
	          </td>
	          <td>&nbsp;</td>
	        </tr>
	      </table>
	    </td>
	  </tr>
	</table>
  </body>
</html>
