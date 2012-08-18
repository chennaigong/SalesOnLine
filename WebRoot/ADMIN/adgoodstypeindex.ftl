<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>评价列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="../CSS/demo.css" type="text/css">
	<link rel="stylesheet" href="../CSS/style.css" type="text/css">
	<link rel="stylesheet" href="../CSS/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/jquery.ztree.all-3.3.min.js"></script>
	<script type="text/javascript" src="../JS/jquery.paginate.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.blockUI.js"></script>
	<script type="text/javascript">
		var mark_index=2;
		var op_index=3;
		var setting = {
			async: {
				enable: true,
				url:"goodsTypeList.action"
			},
			data: {
				keep: {
					parent: true,
					leaf: true
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};
		
		function onClick(event, treeId, treeNode, clickFlag) {
			var id=treeNode.id;
			//点击类别列表
			if(id.indexOf("type")!=-1)
			{
				
				$.post("goodsTypeListById.action",{id:id.substr(4,id.length)},function(data)
				{
					var jsondata=eval(data);
					//不是叶子节点，即子节点还是货品类别，而不含有货品
					if(jsondata[0].isleaf=="否")
					{
						$.blockUI({ message: "正在加载..." });
						tableClear("goods");
						var table=$("#goods");
						table.append("<tr class='table_title' align='center'><td width='250px'>货品子类别编号</td><td width='250px'>货品子类别名字</td><td width='250px'>是否可用</td><td width='250px'>操作</td></tr>");
						
						$.post("goodsTypeListByPId.action",{id:id.substr(4,id.length)},function(data)
						{
							tableAdd(data,table);
							$.unblockUI();
						});
						//判断选中的节点是否可用
						if(jsondata[0].mark=="是")
						{
							$("#btns").html("请输入货品类别名称");
							$("#btns").append("<input type='text' id='typename'/><br/>");
							$("#btns").append("此类别下是否还有子类别");
							$("#btns").append("<select id='isleaf'><option value='否'>是</option><option value='是'>否</option></select><br/>");
							$("#btns").append("<input type='button' value='添加商品类别' onclick='addGoodsTypePid(\""+id.substr(4,id.length)+"\")'/>");
						}
						else
						{
							$("#btns").html("此类别已被禁用，想添加子类别，请先启用");
						}
					}
					//isleaf是”是“,即无子类别，其下直接为货品，显示货品列表
					else
					{
						loadGoods(id);
						ztreeUpdate(id,treeNode)
					}
				});
			}
			//点击商品列表
			else
			{
				var gid=treeNode.id;
				showDetail(gid.substr(5,gid.length));
			}
			
		}	
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
			
			//加载无父节点的货品类别
			tableClear("goods");
			
			var table=$("#goods");
			table.append("<tr class='table_title' align='center'><td width='250px'>货品类别编号</td><td width='250px'>货品类别名字</td><td width='250px'>是否可用</td><td width='250px'>操作</td></tr>");
			
			$.blockUI({ message: "正在加载..." });
			$.post("goodsTypeListByPId.action",{id:0},function(data)
			{
				tableAdd(data,table);
				$.unblockUI();
			});
			
			$("#btns").html("请输入货品类别名称");
			$("#btns").append("<input type='text' id='typename'/><br/>");
			$("#btns").append("此类别下是否还有子类别");
			$("#btns").append("<select id='isleaf'><option value='否'>是</option><option value='是'>否</option></select><br/>");
			$("#btns").append("<input type='button' value='添加商品类别' onclick='addGoodsType()'/>");
		});
		
		function ztreeUpdate(id,treeNode)
		{
			$.post("goodsListByType.action",{id:id.substr(4,id.length)},function(data)
			{
				treeNode.isParent=true;
				
				var jsondata=eval(data);
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				treeObj.removeChildNodes(treeNode);
				
				$.each(jsondata,function(index)
				{
					var obj=jsondata[index];
					treeObj.addNodes(treeNode,{id:'goods'+obj.id,name:obj.name,icon:'../IMAGES/shop.png'});
				});
			});
		}
		
		function showDetail(id)
		{
			$.post("goodsListById.action",{id:id},function(data)
			{
				var details="";
				var jsondata=eval(data);
				$.each(jsondata,function(index)
				{
					var obj=jsondata[index];
					details+="<div>"+"货品编号:"+obj.id+"</div>";
					details+="<div>"+"货品名称:"+obj.name+"</div>";
					details+="<div>"+"助记码:"+obj.mnemonic+"</div>";
					details+="<div>"+"货品单位:"+obj.depart+"</div>";
					details+="<div>"+"厂家:"+obj.factory+"</div>";
					details+="<div>"+"销售价:"+obj.sellprice+"</div>";
					details+="<div>"+"成本价:"+obj.costprice+"</div>";
					details+="<div>"+"保质期(天):"+obj.durability+"</div>";
					details+="<div>"+"报警天数:"+obj.alarmdays+"</div>";
					details+="<div>"+"备注:"+obj.remark+"</div>";
					details+="<div>"+"可用标志:"+obj.mark+"</div>";
					
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					var node = treeObj.getSelectedNodes()[0];
					
					//区分点击了树状图中的货品还是右边表格中的货品
					if(node.id.indexOf("type")==-1)
					{
						node=node.getParentNode();
					}
					
					if(obj.mark=="是")
						details+="<div><input type='button' value='禁用此货品' onclick='disableGoods("+obj.id+",\""+node.id+"\")'/></div>";
					else
						details+="<div><input type='button' value='启用此货品' onclick='enableGoods("+obj.id+",\""+node.id+"\")'/></div>";
					
					details+="<div><input type='button' value='查看此货品的评价' onclick='rateList("+obj.id+")'/></div>"
				});
				
				$.blockUI({ message: details });	
				$('.blockOverlay').attr('title','点击关闭').click($.unblockUI); 
			});
		}
		
		function rateList(id)
		{
			window.location.href="goodsRateIndex.action?id="+id;
		}
		
		function disableGoods(id,typeid)
		{
			$.post("disableGoods.action",{id:id},function(data)
			{
				if(data==1)
				{
					setTimeout($.unblockUI, 1000);
					alert("禁用成功");
					loadGoods(typeid);
				}
			});
		}
		
		function enableGoods(id,typeid)
		{
			$.post("enableGoods.action",{id:id},function(data)
			{
				if(data==1)
				{
					setTimeout($.unblockUI, 1000);
					alert("启用成功");
					loadGoods(typeid);
				}
			});
		}
		
		function loadGoods(id)
		{
			$.blockUI({ message: "正在加载..." });
			tableClear("goods");
			$("#btns").html("");
			var table=$("#goods");
			table.append("<tr class='table_title' align='center'><td width='200px'>货品编码</td><td width='200px'>货品名称</td><td width='200px'>是否可用</td><td width='200px'>操作</td></tr>");
			$.post("goodsListByType.action",{id:id.substr(4,id.length)},function(data)
			{
				var jsondata=eval(data);
				$.each(jsondata,function(index)
				{
					var _tdtd="</td><td height='30'>";
					var trtr="<tr align='center'><td>";
					var obj=jsondata[index];
					var _td_tr="</td></tr>";
					var btn="<input type='button' value='查看详细' onclick='showDetail("+obj.id+")'/>";
					
					var str=trtr+obj.id+_tdtd+obj.name+_tdtd+obj.mark+_tdtd+btn+_td_tr;
					
					table.append(str);
				});
				senfe("goods","#F6F6F6","#FFFFFF");
				pageplugin(10,5,"goods","pageplugin");
				
				$.unblockUI();
			});
			
			$.post("goodsTypeListById.action",{id:id.substr(4,id.length)},function(data)
			{
				var jsondata=eval(data);
				//判断选中的节点是否可用
				if(jsondata[0].mark=="是")
				{
					$("#btns").html("请输入货品名称:<input id='goods_name' type='text'/></br>");
					$("#btns").append("请输入助记码:<input id='goods_mnemonic' type='text'/></br>");
					$("#btns").append("请输入商品单位:<input id='goods_depart' type='text'/></br>");
					$("#btns").append("请输入厂家:<input id='goods_factory' type='text'/></br>");
					$("#btns").append("请输入销售价:<input id='goods_sellprice' type='text'/></br>");
					$("#btns").append("请输入成本价:<input id='goods_costprice' type='text'/></br>");
					$("#btns").append("请输入保质期(天):<input id='goods_durability' type='text'/></br>");
					$("#btns").append("请输入报警天数:<input id='goods_alarmdays' type='text'/></br>");
					$("#btns").append("请输入备注:<input id='goods_remark' type='text'/></br>");
					$("#btns").append("是否可用:<select id='goods_mark'><option value='是'>是</option><option value='否'>否</option></select></br>");
					$("#btns").append("<input id='goods_submit' type='button' value='提交'/></br>");
					$("#goods_submit").click(function()
					{
						var gtid=id.substr(4,id.length);
						var gname=$("#goods_name").val();
						var gmnemonic=$("#goods_mnemonic").val();
						var gdepart=$("#goods_depart").val();
						var gfactory=$("#goods_factory").val();
						var gsellprice=$("#goods_sellprice").val();
						var gcostprice=$("#goods_costprice").val();
						var gdurability=$("#goods_durability").val();
						var galarmdays=$("#goods_alarmdays").val();
						var gremark=$("#goods_remark").val();
						var gmark=$("#goods_mark").val();
						$.post("doAddGoods.action",{id:gtid,gname:gname,gmnemonic:gmnemonic,gdepart:gdepart
						,gfactory:gfactory,gsellprice:gsellprice,gcostprice:gcostprice,gdurability:gdurability,
						galarmdays:galarmdays,gremark:gremark,gmark:gmark},function(data)
						{
							if(data==1)
							{
							
								var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
								var node = treeObj.getSelectedNodes()[0];
								//添加货品之后跟新ztree
								ztreeUpdate(id,node);
								alert("添加成功");
								loadGoods(id);
							}
						});
					});
				}
				else
				{
					$("#btns").html("此类别已被禁用，想添加货品，请先启用");
				}
			});
		}
		
		function addGoodsTypePid(pid)
		{
			var name=$("#typename").val();
			if(name=="")
			{
				alert("请输入类别名称");
				return;
			}
			$.post("doAddSGoodType.action",{name:name,pId:pid,mark:'是',isLeaf:$("#isleaf").val()},function(data)
			{
				if(data==1)
				{
					alert("添加成功");
					window.location.href="goodsTypeIndex.action";
				}
			})
		}
		
		function addGoodsType()
		{
			var name=$("#typename").val();
			if(name=="")
			{
				alert("请输入类别名称");
				return;
			}
			$.post("doAddSGoodType.action",{name:name,pId:'0',mark:'是',isLeaf:$("#isleaf").val()},function(data)
			{
				if(data==1)
				{
					alert("添加成功");
					window.location.href="goodsTypeIndex.action";
				}
			})
		}
		
		function tableAdd(data,table)
		{
			var jsondata=eval(data);
			$.each(jsondata,function(index)
			{
				var _tdtd="</td><td height='30'>";
				var trtr="<tr align='center'><td>";
				var obj=jsondata[index];
				var _td_tr="</td></tr>";
				var btn="";
				if(obj.mark=="是")
				{
					btn="<input type='button' value='禁用' onclick='disableMark(this)'/>"
				}
				else
				{
					btn="<input type='button' value='启用' onclick='enableMark(this)'/>"
				}
				var str=trtr+obj.id+_tdtd+obj.name+_tdtd+obj.mark+_tdtd+btn+_td_tr;
				
				table.append(str);
			});
			senfe("goods","#F6F6F6","#FFFFFF");
			pageplugin(10,5,"goods","pageplugin");
		}
		
		function disableMark(obj)
		{
			var goodstype=$(obj).parent().parent().children("td");
			$.post("modifyGoodsTypeMarkN.action",{id:goodstype.html(),mark:"否"},function(data)
			{
				if(data==1)
				{
					goodstype.eq(mark_index).html("否");
					goodstype.eq(op_index).html("<input type='button' value='启用' onclick='enableMark(this)'/>");
					alert("禁用成功");
				}
			});
			
		}
		
		function enableMark(obj)
		{
			var goodstype=$(obj).parent().parent().children("td");
			$.post("modifyGoodsTypeMarkY.action",{id:goodstype.html(),mark:"是"},function(data)
			{
				if(data==1)
				{
					goodstype.eq(mark_index).html("是");
					goodstype.eq(op_index).html("<input type='button' value='禁用' onclick='disableMark(this)'/>");
					alert("启用成功");
				}
			});
		}
		
		function tableClear(id)
		{
			$.each($("#"+id+" tr"),function()
			{
				$(this).remove();
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
	              <td class="font1">&nbsp;<a href="#">货品管理</a> &gt; <a href="shopIndex.action">货品列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	 <ul id="treeDemo" class="ztree"></ul>
	  	 <div class="detail">
	  	 	<table id="goods" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
	  	 	</table>
	  	 	<div id="pageplugin">                   
     		</div>
     		<div id="btns"></div>
	  	 </div>
	  	 
	</div>
  </body>
</html>
