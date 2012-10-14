<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>采购货品</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css"/>
	<link rel="stylesheet" href="../CSS/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/jquery.paginate.js"></script>
	<script type="text/javascript" src="../JS/jquery.ztree.all-3.3.min.js"></script>
	<script type="text/javascript" src="../JS/jquery.blockUI.js"></script>
	<script type="text/javascript" src="../JS/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				$("#addgoods").click(
				function()
				{
					var g="<tr><td>货品选择:<input type='text' disabled='true'/><input type='button' value='选择' onclick='chooseGoods(this)'/></td>";
					g+="<td>采购数量:<input type='text'/></td><td> 成本价:<input type='text'/></td></tr>";
					$("#goodsdiv").append(g)
				});
				$("#storage").click(function()
				{
					var detail="";
					var isover=true;
					var isright=true;
					$("#goodsdiv tr").each(function(i)
					{
						var goodId=$(this).children("td").eq(0).children("input").val();
						var quantity=$(this).children("td").eq(1).children("input").val();
						var cost=$(this).children("td").eq(2).children("input").val();
						if(goodId==""||quantity==""||cost=="")
						{
							isover=false;
						}
						var patten = /^-?\d+\.?\d*$/;
						var patten2 = /^-?\d+$/;
	                    if(!patten.test(cost)) {  
	                   		isright=false;
	                    } 
	                    if(!patten2.test(quantity)) {  
	                        isright=false;
	                    } 
						innerd=goodId.split("-")[0]+"-"+quantity+"-"+cost;
						detail+=innerd;
						if(i<parseInt($("#goodsdiv tr").length-1))
						{
							detail+=",";
						}
					});
					if(detail=="")
					{
						alert("请添加货品");
						return;
					}
					var ptime=$("#ptime").val();
					var pbussiness=$("#pbussiness").val();
					var pprice=$("#pprice").val();
					var pprint=$("#pprint").val();
					
					if(ptime==""||pbussiness==""||pprice==""||pprint=="")
					{
						isover=false;
					}
					if(!isover)
					{
						alert("请输入完整");
						return;
					}
					if(!isright)
					{
						alert("请输入正确的信息，成本价只能为数字，数量只能为整数");
						return;
					}
					
					$.post("doAddPurchase.action",{detail:detail,ptime:ptime,pbussiness:pbussiness,
						pprice:pprice,pprint:pprint},function(data)
					{
						if(data==1)
						{
							alert("采购货品成功");
							window.location.href="purchaseList.action";
						}
					})
					
					
				});
			}
		)
		function chooseGoods(obj)
		{
			
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
								var name=node.name;
								$(obj).parent().children().eq(0).val(gid+"-"+name);
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
    <div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">采购管理</a> &gt; <a href="#">采购货品</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
     	<div id="input">
     		<table id="goodsdiv"></table>
     		<input type="button" value="点击增加采购的货品" id="addgoods"/><br/>
     		采购时间：<input type="text" id="ptime"   class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',qsEnabled:true,quickSel:['2000-1-10','2000-2-20']})"/></br>
     		商家名称：<input type="text" id="pbussiness"/><br/>
     		实际付款：<input type="text" id="pprice"/><br/>
     		采购单据：<input type="text" id="pprint"/><br/>
     		<input type="button" id="storage" value="采购"/>
     	</div>
    </div>
  </body>
</html>
