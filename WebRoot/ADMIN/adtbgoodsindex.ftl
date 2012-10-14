<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>店铺商品列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css"/>
	<link rel="stylesheet" href="../CSS/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.ztree.all-3.3.min.js"></script>
	<script type="text/javascript" src="../JS/jquery.paginate.js"></script>
	<script type="text/javascript" src="../JS/jquery.blockUI.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.blockUI({ message: "正在加载..." });
				 $.post("tbGoodsList.action", function(data) {
				
					var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td height='30'>";
						var trtr="<tr align='center'><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						if(obj.goods_id==undefined)
							obj.goods_id="无"+"<input type='button' id='modify' value='修改' onclick='modify(this)'/>";
						else
							obj.goods_id=obj.goods_id+"<input type='button' id='modify' value='修改' onclick='modify(this)'/>";
							
						var str=trtr+obj.tbgoods_id+_tdtd+obj.shop_id+_tdtd+obj.goods_id
						+_tdtd+obj.tbgoods_numid+_tdtd+obj.tbgoods_title+_tdtd
						+obj.tbgoods_num+_tdtd+obj.tbgoods_listtime+_tdtd+obj.tbgoods_delisttime+_tdtd+
						obj.tbgoods_modifytime+_tdtd+obj.tbgoods_status+_tdtd+obj.tbgoods_price+_td_tr;
						
						insertTr("ratetable",str);
					});
					senfe("ratetable","#F6F6F6","#FFFFFF");
					pageplugin(5,5,"ratetable","pageplugin");
					
					$.unblockUI();
				});       
			}
		)
		//修改店铺商品id
		function modify(obj)
		{
			var tbid=$(obj).parent().parent().children("td").html();
			
			var setting = {
				async: {
					enable: true,
					url:"goodsTypeList.action"
				},
				data: 
				{
					keep: {
						parent: true,
						leaf: true
					},
					simpleData: {
						enable: true
					}
				},
				callback: {
						onClick: function()
						{
							var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
							var node = treeObj.getSelectedNodes()[0];
							var id=node.id;
							//点击类别列表
							if(id.indexOf("type")!=-1)
							{
								
								$.post("goodsTypeListById.action",{id:id.substr(4,id.length)},function(data)
								{
									var jsondata=eval(data);
									//不是叶子节点，即子节点还是货品类别，而不含有货品
									if(jsondata[0].isleaf=="是")
									{
										$.post("goodsListByTypeMark.action",{id:id.substr(4,id.length)},function(data)
										{
											node.isParent=true;
											
											var jsondata=eval(data);
											var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
											treeObj.removeChildNodes(node);
											var haschild=0;
											$.each(jsondata,function(index)
											{
												var obj=jsondata[index];
												treeObj.addNodes(node,{id:'goods'+obj.id,name:obj.name,icon:'../IMAGES/shop.png'});
												haschild=1;
											});
											if(haschild==0)
											{
												alert("'"+node.name+"'下无商品")
											}
										});
									}
									else
									{
										alert("'"+node.name+"'为非叶子节点")
									}
									
								});
							}
							//点击商品列表
							else
							{
								var gid=node.id;
								gid=gid.substr(5,gid.length);
								
								$.post("modifyTbGoods.action",{id:tbid,gid:gid},function(data)
								{
									if(data==1)
									{
										alert("修改成功");
										$(obj).parent().parent().children("td").eq(2).html(gid+"<input type='button' id='modify' value='修改' onclick='modify(this)'/>")
									}
								})
								
								$.unblockUI();
							}
						}
				}
			};
			$.blockUI({ message: "<ul id='treeDemo' class='ztree'></ul>", 
			css: { 
                top:  ($(window).height() - 400) /2 + 'px', 
                left: ($(window).width() - 400) /2 + 'px', 
                width: '400px',
                height:'400px',
                overflow:'scroll' 
            }  });
            
            $('.blockOverlay').attr('title','点击关闭').click($.unblockUI); 
			$.fn.zTree.init($("#treeDemo"), setting);
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
	              <td class="font1">&nbsp;<a href="#">店铺商品管理</a> &gt; <a href="#">店铺商品列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	 <table id="ratetable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
			<tr height="34" align="center" class="table_title">
				<td width=10%>店铺商品编号</td>
				<td width=10%>所属店铺</td>
				<td width=10%>所属货品编号</td>
				<td width=10%>数字编号</td>
				<td width=15%>标题</td>
				<!--<td width=10%>图片地址</td>-->
				<td width=5%>数量</td>
				<td width=10%>上架时间</td>
				<td width=10%>下架时间</td>
				<td width=10%>修改时间</td>
				<td width=5%>状态</td>
				<td width=5%>价格</td>
			</tr>
		</table>
		<div id="pageplugin">                   
     	</div>
	</div>
  </body>
</html>
