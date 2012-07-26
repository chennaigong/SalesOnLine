<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发货</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
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
							window.location.href=$("#url").val();
						}
					},1000
				);
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
	              <td class="font1">&nbsp;<a href="#">发货成功</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	<input type="hidden" id="url" value="${url}"/>
  		发货成功，系统将在<span id="result">3</span>秒后自动跳转，<a href="${url}">直接跳转</a>
  	</div>
  
  </body>
</html>
