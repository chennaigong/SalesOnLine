<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加功能</title>
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
				$("#add").click(
				function()
				{
					var name=$("#name").val()
					var code=$("#code").val();
					if(name=="")
					{
						alert("请输入功能名称");
						return;
					}
					else if(code=="")
					{
						alert("请输入功能代码");
						return;
					}
					
					$.blockUI({ message: "正在处理..." });
					
					$.post("doAddFunction.action",{name:name,code:code},
					function(data)
					{
						$.unblockUI();
						if(data==1)
						{
							alert("添加成功");
						}
					});
				})
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
	              <td class="font1">&nbsp;<a href="#">功能管理</a> &gt; <a href="#">添加功能</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	功能名称<input type="text" id="name"/><br/><br/>
	  	功能代码<textarea id="code" cols=20 rows=5></textarea><br/><br/>
	  	<input type="button" id="add" value="添加"/>
  	</div>
  	
  </body>
</html>
