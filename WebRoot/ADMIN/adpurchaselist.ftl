<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>采购记录</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.paginate.js"></script>
	<script type="text/javascript" src="../JS/jquery.blockUI.js"></script>
	<script>
		$(document).ready
		(
			function()
			{
				$.blockUI({ message: "正在加载..." });
				
				 $.post("doPurchaseList.action", function(data) {
				 	var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
					
						var _tdtd="</td><td>";
						var trtd="<tr height='30' align='center' ><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var btn="<input type='button' value='查看明细' onclick='seeDetail(this)'/>"
						var str=trtd+obj.id+_tdtd+obj.bussiness+_tdtd+obj.time+_tdtd+obj.price+_tdtd+obj.print+_tdtd+btn+_td_tr
						insertTr("purchase",str);
						
					});
					senfe("purchase","#F6F6F6","#FFFFFF");
					pageplugin(10,5,"purchase","pageplugin");
					
					$.unblockUI();
				 });
			}
		)
		function seeDetail(obj)
		{
			var purchase=$(obj).parent().parent().children("td");
			window.location.href="purchaseDetailList.action?id="+purchase.html();
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
	              <td class="font1">&nbsp;<a href="#">采购管理</a> &gt; <a href="#">采购记录</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	<table id="purchase" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
          <tr height="34" align="center" class="table_title">
            <td width=20%>采购编号</td>
			<td width=20%>商家名称</td>
			<td width=20%>采购时间</td>
			<td width=20%>实际付款</td>
			<td width=10%>采购单据号</td>
			<td width=10%>操作</td>
          </tr>
        </table>
        <div id="pageplugin">                   
     	</div>
  	</div>
  	
  </body>
</html>
