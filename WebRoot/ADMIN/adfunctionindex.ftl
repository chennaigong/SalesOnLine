<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>店铺</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.paginate.js"></script>
	<script type="text/javascript" src="../JS/jquery.blockUI.js"></script>
	<script type="text/javascript">
		var id_index=0;
		var name_index=1;
		var code_index=2;
		var op_index=3;
		$(document).ready
		(
			function()
			{
				 $.blockUI({ message: "正在加载..." });
				 
				 $.post("functionList.action", function(data) {
				 	var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
					
						var _tdtd="</td><td>";
						var trtd="<tr height='30' align='center' ><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var deletefun="<input type='button' value='删除' onclick='deleteFun(this)'/>"
						var modifyfun="<input type='button' value='修改' onclick='modifyFun(this)'/>"
						var str=trtd+obj.id+_tdtd+obj.name+_tdtd+obj.code+_tdtd+deletefun+modifyfun+_tdtd+_td_tr
						insertTr("fuctiontable",str);
						
					});
					senfe("fuctiontable","#F6F6F6","#FFFFFF");
					pageplugin(10,5,"fuctiontable","pageplugin");
					
					$.unblockUI();
				 });
			}
		)
		//删除功能
		function deleteFun(obj)
		{
			var id=$(obj).parent().parent().children("td").html();
			$.post("deleteFunction.action",{id:id},
			function(data)
			{
				if(data==1)
				{
					alert("删除成功");
					$(obj).parent().parent().remove();
				}
			})
		}
		//修改功能
		function modifyFun(obj)
		{
			var data=$(obj).parent().parent().children("td")
			var name=data.eq(name_index).html();
			var code=data.eq(code_index).html();
			data.eq(name_index).html("<input type='text' value='"+name+"'/>")
			data.eq(code_index).html("<input type='text' value='"+code+"'/>")
			data.eq(op_index).html("<input type='button' value='保存' onclick='savaFun(this)'/><input type='button' value='取消' onclick='reset(this)'/>")
		}
		
		function savaFun(obj)
		{
			var data=$(obj).parent().parent().children("td");
			var id=data.html();
			var name=data.eq(name_index).children("input").val();
			var code=data.eq(code_index).children("input").val();
			
			if(name=="")
			{
				alert("请输入功能名称");
				return;
			}
			if(code=="")
			{
				alert("请输入功能代码");
				return;
			}
			
			$.post("modifyFunction.action",{id:id,name:name,code:code},function(res)
			{
				if(res==1)
				{
					alert("编号为"+id+"的功能修改成功");
					data.eq(name_index).html(name);
					data.eq(code_index).html(code);
					var deletefun="<input type='button' value='删除' onclick='deleteFun(this)'/>"
					var modifyfun="<input type='button' value='修改' onclick='modifyFun(this)'/>"
					data.eq(op_index).html(deletefun+modifyfun);
				}
			})
		}
		
		function reset(obj)
		{
			var data=$(obj).parent().parent().children("td");
			
			var deletefun="<input type='button' value='删除' onclick='deleteFun(this)'/>"
			var modifyfun="<input type='button' value='修改' onclick='modifyFun(this)'/>"
			var name=data.eq(name_index).children("input").val();
			var code=data.eq(code_index).children("input").val();
			
			data.eq(name_index).html(name);
			data.eq(code_index).html(code);
			data.eq(op_index).html(deletefun+modifyfun);
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
	              <td class="font1">&nbsp;<a href="#">功能管理</a> &gt; <a href="#">功能列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
        <table id="fuctiontable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
          <tr height="34" align="center" class="table_title">
            <td width=25%>功能编号编号</td>
			<td width=25%>功能名称</td>
			<td width=25%>功能代码</td>
			<td width=25%>操作</td>
          </tr>
        </table>
        <div id="pageplugin">                   
     	</div>
     </div>
  </body>
</html>
