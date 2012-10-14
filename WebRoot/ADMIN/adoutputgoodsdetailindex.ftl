<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>出明细</title>
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
				
				 $.post("outputGoodsDetailList.action",{id:$("#id").val()}, function(data) {
				 	var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
					
						var _tdtd="</td><td>";
						var trtd="<tr height='30' align='center' ><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var str=trtd+obj.id+_tdtd+obj.quantity+_tdtd+obj.price+_tdtd+obj.goodsname+_td_tr
						insertTr("purchase",str);
						
					});
					senfe("purchase","#F6F6F6","#FFFFFF");
					pageplugin(10,5,"purchase","pageplugin");
					
					$.unblockUI();
				 });
			}
		)
	</script>
  </head>
  <body>
  	<input type="hidden" id="id" value="${id}"/>
  	<div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">出入库管理</a> &gt; <a href="outputGoodsIndex.action">出库列表</a> &gt; <a href="#">出库明细</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	<table id="purchase" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
          <tr height="34" align="center" class="table_title">
            <td width=14%>出库明细编号</td>
			<td width=14%>出库数量</td>
			<td width=14%>出库售价</td>
			<td width=14%>货品编号</td>
          </tr>
        </table>
        <div id="pageplugin">                   
     	</div>
  	</div>
  	
  </body>
</html>
