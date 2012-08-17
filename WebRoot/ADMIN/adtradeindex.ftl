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
				 $.post("tradeList.action",{shopid:$("#shopid").val()}, function(data) {
					var jsondata=strCut(data)
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td height='30'>";
						var trtr="<tr align='center'><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var array=enTozh(obj.status);
						var statue=array[1];
						
						var str=trtr+obj.tid+_tdtd+statue+_tdtd+obj.buyer_nick
						+_tdtd+obj.created+_tdtd+obj.pay_time+_tdtd+obj.payment+_tdtd+obj.modified+_tdtd+obj.isread+_td_tr;
						insertTr("tradetable",str);
						
					});
					senfe("tradetable","#F6F6F6","#FFFFFF");
					pageplugin(8,5,"tradetable","pageplugin");
					
					$.unblockUI();
				});
				
			}
		)
		function tranGoods(obj)
		{
			var tid=$(obj).parent().parent().children("td").html();
			window.location.href="logisticsIndex.action?tid="+tid+"&shopid="+$("#shopid").val();
		}
	</script>
	
  </head>
  
  <body>
 	<input type="hidden" value="${shopid}" id="shopid"/>
 	<div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">店铺管理</a> &gt; <a href="shopIndex.action">店铺列表</a> &gt; <a href="#">订单列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
		 <table id="tradetable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
			<tr height="34" align="center" class="table_title">
				<td width=15%>订单号</td>
				<td width=15%>状态</td>
				<td width=10%>买家昵称</td>
				<td width=15%>订单创建时间</td>
				<td width=15%>付款时间</td>
				<td width=10%>实际付款</td>
				<td width=15%>最后修改时间</td>
				<td width=5%>是否已读</td>
			</tr>
		</table>
		<div id="pageplugin">                   
     	</div>
	</div>
  </body>
</html>
