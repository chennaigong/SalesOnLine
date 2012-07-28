<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>店铺</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("shopList.action", function(data) {
				 	var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
					
						var _tdtd="</td><td>";
						var trtd="<tr height='30' align='center' ><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var tradeSee="<input type='button' value='查看订单' onclick='seeTrade(this)'/>"
						var rateSee="<input type='button' value='查看评价' onclick='seeRate(this)'/>"
						var str=trtd+obj.id+_tdtd+obj.session+_tdtd+obj.ispromise+_tdtd+tradeSee+rateSee+_td_tr
						insertTr("shoptable",str);
						
						senfe("shoptable","#F6F6F6","#FFFFFF");
					});
				 });
			}
		)
		//查出用户名
		function seeTrade(obj)
		{
			var shopid=$(obj).parent().parent().children("td").html();
			window.location.href="tradeIndex.action?shopid="+shopid;
		}
		//查出用户名
		function seeRate(obj)
		{
			var shopid=$(obj).parent().parent().children("td").html();
			window.location.href="rateIndex.action?shopid="+shopid;
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
	              <td class="font1">&nbsp;<a href="#">店铺管理</a> &gt; <a href="#">店铺列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
        <table id="shoptable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
          <tr height="34" align="center" class="table_title">
            <td width=10%>店铺编号</td>
			<td width=50%>店铺标识</td>
			<td width=10%>是否授权</td>
			<td width=30%>操作</td>
          </tr>
        </table>
     </div>
  </body>
</html>
