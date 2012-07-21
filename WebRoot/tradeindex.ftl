<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("tradeList.action",{top_session:$("#top_session").val()}, function(data) {
					
					var jsondata=strCut(data)
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td align=center height=50>";
						var trtr="<tr><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var btn;
						var statue;
						if(obj.status=="WAIT_BUYER_CONFIRM_GOODS")
						{
							btn="卖家已发货";
							statue="卖家已发货";
						}
						else if(obj.status=="WAIT_SELLER_SEND_GOODS")
						{
							btn='<input type="button" value="发货" onclick="tranGoods(this)"/>';
							statue="等待卖家发货";
						}
						else if(obj.status=="TRADE_FINISHED")
						{
							btn="交易完成";
							statue="交易完成";
						}
						else if(obj.status=="TRADE_CLOSED_BY_TAOBAO")
						{
							btn="交易关闭";
							statue="交易关闭";
						}
						
						var str=trtr+obj.tid+_tdtd+statue+_tdtd+obj.buyer_nick
						+_tdtd+obj.created+_tdtd+obj.total_fee+_tdtd+obj.pay_time+_tdtd+obj.payment+_tdtd+btn+_td_tr;
						insertTr("tradetable",str);
						
						
					});
				});
				
			}
		)
		function tranGoods(obj)
		{
			var tid=$(obj).parent().parent().children("td").html();
			window.location.href="logisticsIndex.action?tid="+tid+"&top_session="+$("#top_session").val();
		}
	</script>
	
  </head>
  
  <body>
 
  	<input type="hidden" value="${top_session}" id="top_session"/>
	授权成功。您的sesionKey为:${top_session}。<br/><br/>
	
	<a href="rateIndex.action?top_session=${top_session}">评价列表</a>
	<a href="buyerIndex.action?top_session=${top_session}">用户</a>
	<table id="tradetable">
		<tr>
			<th width=200>订单号</th>
			<th width=200>状态</th>
			<th width=200>买家昵称</th>
			<th width=200>订单创建时间</th>
			<th width=200>商品价格</th>
			<th width=200>付款时间</th>
			<th width=100>实际付款</th>
			<th width=100>操作</th>
		</tr>
	</table>
  </body>
</html>
