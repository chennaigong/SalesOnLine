<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加角色</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script>
		$(document).ready
		(
			function()
			{
				$.post("functionList.action",function(data)
				{
					var jsondata=strCut(data);
					if(jsondata=="")
					{
						alert("无功能列表，请先添加用户功能");
					}
					else
					{
						$.each(jsondata,function(index)
						{
							var obj=jsondata[index];
							var checkbox="<input type='checkbox' name='functions' value=\""+obj.id+"\"/>"+obj.name+"<br/><br/>"
							$("#functionlist").append(checkbox);
						});
					}

				});
				
				$("#add").click(function()
				{
					var name=$("#name").val();
					if(name=="")
					{
						alert("请输入角色名");
						return;
					}
					var functionids="";
					var checked=$("#functionlist input:checked");
					$(checked).each(function()
					{
						functionids+=$(this).val();
						functionids+=",";
					});
					if(functionids=="")
					{
						alert("至少选择一个功能");
					}
					else
					{
						$.post("doAddRole.action",{functionids:functionids,name:name},function(data)
						{
							if(data==1)
							{
								alert("添加成功")
							}
						});
					}
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
	  	角色名称:<input type="text" id="name"/></br><br/>
	  	<div id="functionlist"></div></br><br/>
	  	<input type="button" value="添加" id="add"/>
  	</div>
  	
  </body>
</html>
