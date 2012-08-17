<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加角色</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.blockUI.js"></script>
	<script>
		$(document).ready
		(
			function()
			{
				$.post("roleListMark.action",function(data)
				{
					var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
						var obj=jsondata[index];
						$("#role").append("<option value='"+obj.id+"'>"+obj.name+"</option>");
					});
					
					$("#add").click(
					function()
					{
						var username=$("#username");
						var password=$("#password");
						if(username.val()==""||password.val()=="")
						{
							alert("用户名或密码不能为空");
							return;
						}
						
						$.blockUI({ message: "正在处理..." });
						
						$.post("doAddUser.action",{username:username.val(),password:password.val(),id:$("#role").val()},
						function(data)
						{
							$.unblockUI();
							if(data==1)
							{
								alert("添加成功");
							}
							else if(data==0)
							{
								alert("此用户已经存在");
							}
							else if(data==2)
							{
								alert("此角色已被禁用");
							}
							else
							{
								alert("添加失败");
							}
						});
					});
				});
			}
		)
	</script>
  </head>
  <body>
  	<div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">角色管理</a> &gt; <a href="#">添加角色</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	用户名:<input type="text" id="username"/></br><br/>
	  	密码:<input type="text" id="password"/></br><br/>
	  	角色
	  	<select id="role">
	  	
	  	</select>
	  	<input type="button" value="添加" id="add"/>
  	</div>
  	
  </body>
</html>
