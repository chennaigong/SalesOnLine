<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("admin/tradeList.action",{username:$("#username").val()}, function(data) {
					var jsondata=strCut(data)
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td height='30'>";
						var trtr="<tr align='center'><td>";
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
						+_tdtd+obj.created+_tdtd+obj.total_fee+_tdtd+obj.pay_time+_tdtd+obj.payment+_tdtd+obj.modified+_tdtd+btn+_td_tr;
						insertTr("tradetable",str);
						
						senfe("tradetable","#F6F6F6","#FFFFFF");
						
					});
				});
				
			}
		)
		function tranGoods(obj)
		{
			var tid=$(obj).parent().parent().children("td").html();
			window.location.href="admin/logisticsIndex.action?tid="+tid+"&username="+$("#username").val();
		}
	</script>
	
  </head>
  
  <body>
  <input type="hidden" value="${username}" id="username"/>
  <div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">我的订单</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	 <table id="tradetable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table" id="changecolor">
			<tr height="34" align="center" class="table_title">
				<td width=15%>订单号</td>
				<td width=10%>状态</td>
				<td width=10%>买家昵称</td>
				<td width=10%>订单创建时间</td>
				<td width=10%>商品价格</td>
				<td width=10%>付款时间</td>
				<td width=10%>实际付款</td>
				<td width=10%>最后修改时间</td>
				<td width=10%>操作</td>
			</tr>
		</table>
	</div>
  </body>
</html>
