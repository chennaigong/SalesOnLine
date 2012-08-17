<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.paginate.js"></script>
	<script type="text/javascript" src="../JS/jquery.blockUI.js"></script>
	
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				$.blockUI({ message: "正在加载..." });
				$.post("allTradeList.action", function(data) {
					var jsondata=strCut(data)
					insertOp(jsondata);
					$.unblockUI();
				});
				
				$("#search").click(function()
				{
					$.blockUI({ message: "正在加载..." });
					//删除除第一行以外的tr
					$("#tradetable tr:not(:eq(0))").remove();
					$.post("allTradeListStatus.action",{status:$("#condition").val()}, function(data) {
						var jsondata=strCut(data)
						insertOp(jsondata);
						$.unblockUI();
					});
				});
				
			}
		)
		function insertOp(jsondata)
		{
			$.each(jsondata,function(index)
			{
				var _tdtd="</td><td height='30'>";
				var trtr="<tr align='center' width='100%'><td>";
				var obj=jsondata[index];
				var _td_tr="</td></tr>";
				var array=enTozh(obj.status);
				var btn=array[0];
				var statue=array[1];
				var check;
				if(obj.isread=="否")
				{
					check="<input type='button' value='标记为已读' onclick='modifyTrade(this)'/>"
				}
				else
				{
					check="无"
				}
				
				var str=trtr+obj.tid+_tdtd+statue+_tdtd+obj.buyer_nick
				+_tdtd+obj.created+_tdtd+obj.pay_time+_tdtd+obj.payment+_tdtd+obj.modified+_tdtd+obj.isread+_tdtd+btn+_tdtd+check+_tdtd+_td_tr;
				insertTr("tradetable",str);
			});
			senfe("tradetable","#F6F6F6","#FFFFFF");
			pageplugin(8,5,"tradetable","pageplugin");
		}
		function tranGoods(obj)
		{
			var tid=$(obj).parent().parent().children("td").html();
			window.location.href="logisticsIndex.action?tid="+tid;
		}
		function modifyTrade(obj)
		{
			var tid=$(obj).parent().parent().children("td").html();
			$.post("modifyTradeRead.action",{tid:tid},
			function(data)
			{
				if(data==1)
				{
					$(obj).parent().html("无");
				}
				else
				{
					alert("标记失败");
				}
			});
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
	              <td class="font1">&nbsp;<a href="#">订单</a> &gt; <a href="#">订单列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
		<select id="condition">
			<option value="TRADE_NO_CREATE_PAY">没有创建支付宝交易</option>
			<option value="WAIT_SELLER_SEND_GOODS">等待卖家发货</option>
			<option value="WAIT_BUYER_PAY">等待买家付款</option>
			<option value="WAIT_BUYER_CONFIRM_GOODS">卖家已发货</option>
			<option value="TRADE_BUYER_SIGNED">买家已签收</option>
			<option value="TRADE_FINISHED">交易成功</option>
			<option value="TRADE_CLOSED">退款成功，交易关闭</option>
			<option value="TRADE_CLOSED_BY_TAOBAO">交易关闭</option>
		</select>
		<input type="button" value="查询" id="search"/><br/><br/>
		<table id="tradetable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
			<tr height="34" align="center" class="table_title">
				<td width=15%>订单号</td>
				<td width=10%>状态</td>
				<td width=10%>买家昵称</td>
				<td width=10%>订单创建时间</td>
				<td width=10%>付款时间</td>
				<td width=10%>实际付款</td>
				<td width=10%>最后修改时间</td>
				<td width=10%>是否已读</td>
				<td width=10%>操作</td>
				<td width=5%>操作</td>
			</tr>
		</table>
		<div id="pageplugin">                   
     	</div>
	</div>
  </body>
</html>
