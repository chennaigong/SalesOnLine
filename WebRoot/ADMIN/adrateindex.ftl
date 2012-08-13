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
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("rateList.action",{shopid:$("#shopid").val()}, function(data) {
				
					var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td height='30'>";
						var trtr="<tr align='center'><td>";
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
					senfe("ratetable","#F6F6F6","#FFFFFF");
					pageplugin(5,5,"ratetable","pageplugin");
				});       
			}
		)
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
	              <td class="font1">&nbsp;<a href="#">店铺管理</a> &gt; <a href="shopIndex.action">店铺列表</a> &gt; <a href="#">评价列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	 <table id="ratetable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
			<tr height="34" align="center" class="table_title">
				<td width=15%>订单号</td>
				<td width=10%>买家昵称</td>
				<td width=10%>评价结果</td>
				<td width=10%>评价时间</td>
				<td width=15%>评价的商品</td>
				<td width=10%>价格</td>
				<td width=15%>评价的内容</td>
				<td width=15%>评价的解释</td>
			</tr>
		</table>
		<div id="pageplugin">                   
     	</div>
	</div>
  </body>
</html>
