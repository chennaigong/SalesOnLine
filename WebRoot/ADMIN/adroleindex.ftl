<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色管理</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.paginate.js"></script>
	<script type="text/javascript">
		var id_index=0;
		var name_index=1;
		var function_index=2;
		var enable_index=3;
		var mark_index=4;
		var modify_index=5;
		$(document).ready
		(
			function()
			{
				 $.post("roleList.action", function(data) {
				 	var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
					
						var _tdtd="</td><td>";
						var trtd="<tr height='30' align='center' ><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var btn;
						if(obj.mark=="是")
						{
							btn="<input type='button' value='禁用' onclick='disableMark(this)'/>";
						}
						else
						{
							btn="<input type='button' value='启用' onclick='enableMark(this)'/>";
						}
						var modify="<input type='button' value='修改' onclick='modify(this)'/>"
						var str=trtd+obj.id+_tdtd+obj.name+_tdtd+obj.havefun+_tdtd+obj.mark+_tdtd+btn+_tdtd+modify+_tdtd+_td_tr
						insertTr("roletable",str);
						
					});
					senfe("roletable","#F6F6F6","#FFFFFF");
					pageplugin(10,5,"roletable","pageplugin");
				 });
			}
		)
		//禁用
		function disableMark(obj)
		{
			var role=$(obj).parent().parent().children("td");
			var roleid=role.html();
			$.post("disabledRole.action",{id:roleid},function(data)
			{
				if(data==1)
				{
					alert("编号为"+roleid+"的角色禁用成功");
					role.eq(enable_index).html("否");
					role.eq(mark_index).html("<input type='button' value='启用' onclick='enableMark(this)'/>");
				}
			});
		}
		//启用
		function enableMark(obj)
		{
			var role=$(obj).parent().parent().children("td");
			var roleid=role.html();
			var roleid=$(obj).parent().parent().children("td").html();
			$.post("enabledRole.action",{id:roleid},function(data)
			{
				if(data==1)
				{
					alert("编号为"+roleid+"的角色启用成功");
					role.eq(enable_index).html("是");
					role.eq(mark_index).html("<input type='button' value='禁用' onclick='disableMark(this)'/>");
				}
			});
		}
		//修改
		function modify(obj)
		{
			var role=$(obj).parent().parent().children("td");
			var roleid=role.html();
			var name=role.eq(name_index).html();
			var functionStr=role.eq(function_index).html();
			var functions=functionStr.split(",");
			
			//显示编辑器
			role.eq(name_index).html("<input type='text' value='"+name+"'/>");
			
			//清空角色具有的功能td
			role.eq(function_index).html("");
			
			//获取功能列表
			$.post("functionList.action",function(data)
			{
				var jsondata=strCut(data);
				$.each(jsondata,function(index)
				{
					var obj=jsondata[index];
					var isSame=false;
					//找出已经具有的功能
					for(var i=0;i<functions.length;i++)
					{
						if(functions[i].split("-")[0]==obj.id)
						{
							isSame=true;
						}
					}
					var checkbox;
					if(isSame)
					{
						checkbox="<input type='checkbox' name='"+obj.name+"' checked='checked' value=\""+obj.id+"\"/>"+obj.name+"　";
					}
					else
					{
						checkbox="<input type='checkbox' name='"+obj.name+"' value=\""+obj.id+"\"/>"+obj.name+" 　";
					}
					//显示功能选择checkbox
					role.eq(function_index).append(checkbox);
					//将修改按钮改为保存按钮
					
					role.eq(modify_index).html("<input type='button' value='保存' onclick='save(this)'/>")
				});
			});
		}
		//保存
		function save(obj)
		{
			var role=$(obj).parent().parent().children("td");
			var checked=role.eq(function_index).children("input:checked");
			var havefun="";
			$(checked).each(
			function()
			{
				havefun+=$(this).val()+"-"+$(this).attr("name");
				havefun+=",";
			});
			var name=role.eq(name_index).children("input").val();
			var roleid=role.eq(id_index).html();
			var functionids="";
			$(checked).each(function()
			{
				functionids+=$(this).val();
				functionids+=",";
			});
			if(functionids=="")
			{
				alert("至少选择一个功能");
			}
			else if(name=="")
			{
				alert("角色名称不能为空");
			}
			else
			{
				$.post("modifyRole.action",{functionids:functionids,name:name,id:roleid},function(data)
				{
					alert("修改成功");
					role.eq(modify_index).html("<input type='button' value='修改' onclick='modify(this)'/>")
					role.eq(name_index).html(name);
					role.eq(function_index).html(havefun.substr(0,havefun.length-1));
				});
			}
			
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
	              <td class="font1">&nbsp;<a href="#">角色管理</a> &gt; <a href="#">角色列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
        <table id="roletable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
          <tr height="34" align="center" class="table_title">
            <td width=20%>角色编号</td>
			<td width=20%>角色名称</td>
			<td width=20%>角色具有的功能</td>
			<td width=20%>是否可用</td>
			<td width=10%>操作</td>
			<td width=10%>操作</td>
          </tr>
        </table>
        <div id="pageplugin">                   
     	</div>
     </div>
  </body>
</html>
