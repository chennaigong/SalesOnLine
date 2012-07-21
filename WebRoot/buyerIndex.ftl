<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>买家列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("buyerList.action",{top_session:$("#top_session").val()}, function(data) {
				 	var jsondata=strCut(data);
				 	var str="";
					$.each(jsondata,function(index)
					{
						str+=jsondata[index].buyer_nick+"</br>"
					});
					$("#buyerlist").html(str);
				 });
			}
		)
	</script>
  </head>
  
  <body>
  	<input type="hidden" value="${top_session}" id="top_session"/>
  	<div id="buyerlist"></div>
  	<table id="ratetable">
		<tr>
		</tr>
	</table>
  </body>
</html>
