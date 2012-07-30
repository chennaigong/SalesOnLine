<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加店铺</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script>
		$(document).ready
		(
			function()
			{
				$("#addshop").click(function()
				{
					window.location.href="http://container.api.taobao.com/container?appkey=21062760";
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
	              <td class="font1">&nbsp;<a href="#">店铺管理</a> &gt; <a href="#">添加店铺</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	<input type="button" id="addshop" value="点击授权店铺"/>
  	</div>
  	
  </body>
</html>
