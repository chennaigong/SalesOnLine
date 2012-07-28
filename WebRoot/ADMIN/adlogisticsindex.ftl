<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发货</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script>
		
		$(document).ready
		(
			function()
			{
				$.post("logisticsList.action", function(data) {
				
					var jsondata=strCut(data);
					var str;
					$.each(jsondata,function(index)
					{
						var obj=jsondata[index];
						str+='<option value="'+obj.code+'@'+obj.reg_mail_no+'">'+obj.name+'</option>';
						
					});
					$("#logisticscompany").html(str);
				})
			}
		)
		function valid()
		{
			try
			{
				var code_reg=$("#logisticscompany").val();
				var reg=code_reg.split("@")[1];
				var regexp=new RegExp(reg);
				var isvalid=regexp.test($("#out_id").val());
				if(!isvalid)
				{
					alert("运单号不符合规则");
					return false;
				}
			}
			catch(e)
			{
				return false;
			}
		}
	</script>
  </head>
  <body>
  	<div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">发货</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	<form action="logicsticsSend.action" method="POST" onsubmit="return valid()">
	  		<input type="hidden" value="${shopid}" name="shopid"/>
		  	请选择物流公司:
		  	<select name="company_code" id="logisticscompany"></select><br/><br/>
		  	请输入运单号:
		  	<input name="out_id" id="out_id" type="text"/><br/><br/>
		  	
		  	<input type="hidden" id="tid" name="tid" value="${tid}"/>
	  		
		  	<input type="submit" value="提交" />
		</form>
  	</div>
  	
  </body>
</html>
