<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发货</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="JS/util.js"></script>
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
  
  	<form action="logicsticsSend.action" method="POST" onsubmit="return valid()">
	  	请选择物流公司:
	  	<select name="company_code" id="logisticscompany"></select><br/>
	  	请输入运单号:
	  	<input name="out_id" id="out_id" type="text"/><br/>
	  	
	  	<input type="hidden" id="tid" name="tid" value="${tid}"/>
  		<input type="hidden" id="top_session" name="top_session" value="${top_session}"/>
  		
	  	<input type="submit" value="提交" />
	</form>
  	
  	
  </body>
</html>
