<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>评价列表(只能获取卖加收到的评论)</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("rateList.action",{username:$("#username").val()}, function(data) {
				
					var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td align=center height=50>";
						var trtr="<tr><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var result;
						if(obj.result=="good")
						{
							result="好评";
						}
						else if(obj.result=="neutral")
						{
							result="中评";
						}
						else if(obj.result=="bad")
						{
							result="差评";
						}
						var str=trtr+obj.tid+_tdtd+obj.nick+_tdtd+result+_tdtd+obj.created
						+_tdtd+obj.item_title+_tdtd+obj.item_price+_tdtd+obj.content+_tdtd+obj.reply+_td_tr;
						
						insertTr("ratetable",str);
					});
					
				});       
			}
		)
	</script>
  </head>
  
  <body>
  	<input type="hidden" value="${username}" id="username"/>
  	<table id="ratetable">
		<tr>
			<th width=100>订单号</th>
			<th width=100>买家昵称</th>
			<th width=100>评价结果</th>
			<th width=100>评价时间</th>
			<th width=300>评价的商品</th>
			<th width=100>评价的商品的价格</th>
			<th width=100>评价的内容</th>
			<th width=100>评价的解释</th>
		</tr>
	</table>
  </body>
</html>
