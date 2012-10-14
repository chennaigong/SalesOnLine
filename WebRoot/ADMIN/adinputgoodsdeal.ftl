<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>入库操作</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.paginate.js"></script>
	<script type="text/javascript" src="../JS/jquery.blockUI.js"></script>
	<script type="text/javascript" src="../JS/My97DatePicker/WdatePicker.js"></script>
	<script>
		$(document).ready
		(
			function()
			{
				$.blockUI({ message: "正在加载..." });
				
				 $.post("inputGoodsDetailList.action",{id:$("#id").val()}, function(data) {
				 	var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
					
						var _tdtd="</td><td>";
						var trtd="<tr height='30' align='center' ><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						obj.warntime="<input type='text' class='Wdate' onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',qsEnabled:true,quickSel:['2000-1-10','2000-2-20']})\"/>";
						obj.badtime="<input type='text' class='Wdate' onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',qsEnabled:true,quickSel:['2000-1-10','2000-2-20']})\"/>";
						obj.iquantity="<input type='text'/>"
						var str=trtd+obj.id+_tdtd+obj.goods+_tdtd+obj.warntime+_tdtd+obj.badtime+
							_tdtd+obj.iquantity+_tdtd+obj.cost+_td_tr
						insertTr("purchase",str);
						
					});
					senfe("purchase","#F6F6F6","#FFFFFF");
					pageplugin(10,5,"purchase","pageplugin");
					
					$.unblockUI();
				 });
				 $("#addIn").click(function()
				 {
				 	var isover=true;
				 	var isright=true;
				 	
				 	var mark=$("#mark").val();
				 	var print=$("#print").val();
				 	var detail="";
				 	if(mark==""||print=="")
				 	{
				 		isover=false;
				 	}
				 	$("#purchase tr").each(function(i)
					{
						if(i==0)
						{}
						else
						{
							var detailid=$(this).children("td").eq(0).html();
							var warntime=$(this).children("td").eq(2).children("input").val();
							var badtime=$(this).children("td").eq(3).children("input").val();
							var quantity=$(this).children("td").eq(4).children("input").val();
							if(detailid==""||warntime==""||badtime==""||quantity=="")
							{
								isover=false;
							}
							var patten2 = /^-?\d+$/;
							if(!patten2.test(quantity)) 
							{  
	                        	isright=false;
	                    	} 
	                    	var innerd=detailid+"@"+warntime+"@"+badtime+"@"+quantity;
	                    	detail+=innerd;
	                    	if(i<parseInt($("#purchase tr").length-1))
							{
								detail+=",";
							}
						}
					});
					if(!isover)
					{
						alert("请输入完整");
						return;
					}
					if(!isright)
					{
						alert("请输入正确的信息，数量只能为整数");
						return;
					}
					$.post("updateInputGoods.action",{id:$("#id").val(),mark:mark,print:print,detail:detail},function(data)
					{
						if(data==1)
						{
							alert("入库成功");
							window.location.href="inputGoodsIndex.action";
						}
					})
				 })
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
	              <td class="font1">&nbsp;<a href="#">出入库管理</a> &gt; <a href="inputGoodsIndex.action">入库列表</a> &gt; <a href="#">入库操作</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
		请输入备注：<input type="text" id="mark"/><br/><br/>
		请输入入库单据号:<input type="text" id="print"/><br/><br/>
	  	<table id="purchase" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
          <tr height="34" align="center" class="table_title">
            <td width=14%>入库明细编号</td>
			<td width=14%>货品名称</td>
			<td width=14%>预警时间</td>
			<td width=14%>失效时间</td>
			<td width=14%>入库数量</td>
			<td width=14%>成本价</td>
          </tr>
        </table>
        <div id="pageplugin">                   
     	</div><br/><br/><br/>
     	<input type="button" value="入库" id="addIn"/>
  	</div>
  	
  </body>
</html>
