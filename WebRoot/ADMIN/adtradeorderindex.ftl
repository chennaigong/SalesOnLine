<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>评价列表</title>
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
				 $.post("tradeOrderList.action",{tid:$("#tid").val()}, function(data) {
				
					var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td height='30'>";
						var trtr="<tr align='center'><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						
						var str=trtr+obj.id+_tdtd+obj.tid+_tdtd+obj.oid
						+_tdtd+obj.numid+_tdtd+obj.name+_tdtd+obj.num+_td_tr;
						
						insertTr("ratetable",str);
						
						
					});
					senfe("ratetable","#F6F6F6","#FFFFFF");
					pageplugin(5,5,"ratetable","pageplugin");
					
					$.unblockUI();
				});       
			}
		)
	</script>
  </head>
  
  <body>
  	<input type="hidden" value="${tid}" id="tid"/>
  	<div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">订单管理</a> &gt; <a href="allTradeIndex.action">订单列表</a> &gt; <a href="#">订单明细</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	 <table id="ratetable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
			<tr height="34" align="center" class="table_title">
				<td width=15%>明细编号</td>
				<td width=10%>父订单编号</td>
				<td width=10%>子订单编号</td>
				<td width=15%>商品数字编号</td>
				<td width=15%>商品名称</td>
				<td width=10%>购买数量</td>
			</tr>
		</table>
		<div id="pageplugin">                   
     	</div>
	</div>
  </body>
</html>
