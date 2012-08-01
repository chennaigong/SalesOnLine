<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="../CSS/demo.css" type="text/css">
	<link rel="stylesheet" href="../CSS/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/jquery.ztree.all-3.3.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript">
		
		var MoveTest = {
			errorMsg: "请选择正确的用户",
			curTarget: null,
			curTmpTarget: null,
			noSel: function() {
				try {
					window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
				} catch(e){}
			},
			dragTree2Dom: function(treeId, treeNodes) {
				return !treeNodes[0].isParent;
			},
			prevTree: function(treeId, treeNodes, targetNode) {
				return !targetNode.isParent && targetNode.parentTId == treeNodes[0].parentTId;
			},
			nextTree: function(treeId, treeNodes, targetNode) {
				return !targetNode.isParent && targetNode.parentTId == treeNodes[0].parentTId;
			},
			innerTree: function(treeId, treeNodes, targetNode) {
				return targetNode!=null && targetNode.isParent && targetNode.tId == treeNodes[0].parentTId;
			},
			dropTree2Dom: function(e, treeId, treeNodes, targetNode, moveType) {
				var domId = "dom_" + treeNodes[0].getParentNode().id;
				if (moveType == null) {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					var id=treeNodes[0].getParentNode().id;
					var shopid=treeNodes[0].id;
					//删除数据
					$.post("deleteShopUser.action",{id:id/100000,shopid:shopid/10000000000},function(data)
					{
						if(data==1)
						{
							zTree.removeNode(treeNodes[0]);
							MoveTest.updateType();
						}
					})
				}
			},
			dom2Tree: function(e, treeId, treeNode) {
				var target = MoveTest.curTarget, tmpTarget = MoveTest.curTmpTarget;
				if (!target) return;
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"), parentNode;
				if (treeNode != null && treeNode.id>=100000&&treeNode.id<10000000000) {
					parentNode = treeNode;
					parentNode.isParent = true;
					//添加数据;
					$.post("addShopUser.action",{id:parentNode.id/100000,shopid:target.attr("domId")},function(data)
					{
						if(data==0)
						{
							alert("用户: "+parentNode.name+" 已经可管理店铺: "+target.text());
							return;
						}
						else
						{
							if (tmpTarget) tmpTarget.remove();
							if (!!parentNode) {
								var nodes = zTree.addNodes(parentNode, {id:target.attr("domId")+"0000000000", name: target.text(),icon:'../IMAGES/shop.png'});
								zTree.selectNode(nodes[0]);
							} else {
								alert(MoveTest.errorMsg);
							}
							MoveTest.updateType();
							MoveTest.curTarget = null;
							MoveTest.curTmpTarget = null;
						}
					});
				}
			},
			updateType: function() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
				nodes = zTree.getNodes();
				for (var i=0, l=nodes.length; i<l; i++) {
					var usernodes=nodes[i].children;
					for(var j=0;j<usernodes.length;j++)
					{
						var num = usernodes[j].children ? usernodes[j].children.length : 0;
						usernodes[j].name = usernodes[j].name.replace(/ \(.*\)/gi, "") + " (" + num + ")";
						zTree.updateNode(usernodes[j]);
					}
				}
			},
			bindDom: function() {
				$(".domBtnDiv").bind("mousedown", MoveTest.bindMouseDown);
			},
			bindMouseDown: function(e) {
				var target = e.target;
				if (target!=null && target.className=="domBtn") {
					var doc = $(document), target = $(target),
					docScrollTop = doc.scrollTop(),
					docScrollLeft = doc.scrollLeft();
					curDom = $("<span class='dom_tmp domBtn'>" + target.text() + "</span>");
					curDom.appendTo("body");

					curDom.css({
						"top": (e.clientY + docScrollTop + 3) + "px",
						"left": (e.clientX + docScrollLeft + 3) + "px"
					});
					MoveTest.curTarget = target;
					MoveTest.curTmpTarget = curDom;

					doc.bind("mousemove", MoveTest.bindMouseMove);
					doc.bind("mouseup", MoveTest.bindMouseUp);
					doc.bind("selectstart", MoveTest.docSelect);
				}
				if(e.preventDefault) {
					e.preventDefault();
				}
			},
			bindMouseMove: function(e) {
				MoveTest.noSel();
				var doc = $(document), 
				docScrollTop = doc.scrollTop(),
				docScrollLeft = doc.scrollLeft(),
				tmpTarget = MoveTest.curTmpTarget;
				if (tmpTarget) {
					tmpTarget.css({
						"top": (e.clientY + docScrollTop + 3) + "px",
						"left": (e.clientX + docScrollLeft + 3) + "px"
					});
				}
				return false;
			},
			bindMouseUp: function(e) {
				var doc = $(document);
				doc.unbind("mousemove", MoveTest.bindMouseMove);
				doc.unbind("mouseup", MoveTest.bindMouseUp);
				doc.unbind("selectstart", MoveTest.docSelect);

				var target = MoveTest.curTarget, tmpTarget = MoveTest.curTmpTarget;
				if (tmpTarget) tmpTarget.remove();

				if ($(e.target).parents("#treeDemo").length == 0) {
					if (target) {
						target.removeClass("domBtn_Disabled");
						target.addClass("domBtn");
					}
					MoveTest.curTarget = null;
					MoveTest.curTmpTarget = null;
				}
			},
			bindSelect: function() {
				return false;
			}
		};
		
		var setting = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				drag: {
					prev: MoveTest.prevTree,
					next: MoveTest.nextTree,
					inner: MoveTest.innerTree
				}
			},
			async: {
				enable: true,
				url:"userList.action"
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
				beforeDrag: MoveTest.dragTree2Dom,
				onDrop: MoveTest.dropTree2Dom,
				onMouseUp: MoveTest.dom2Tree
			},
			view: {
				selectedMulti: false
			}
		};
		$(document).ready(function(){
		
			 $.post("shopList.action", function(data) {
			 	var jsondata=strCut(data);
				$.each(jsondata,function(index)
				{
					var obj=jsondata[index];
					var shop="<span class='domBtn' domId='"+obj.id+"'>"+obj.name+"</span>"
					$("#dom_1").append(shop);
				});
				
				$.fn.zTree.init($("#treeDemo"), setting);
				MoveTest.updateType();
				MoveTest.bindDom();
			 });
			
		});
	</script>
  </head>
  
  <body>
    <div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">用户管理</a> &gt; <a href="#">用户列表</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
		<div>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="domBtnDiv">
			<div id="dom_1" class="categoryDiv"></div>
		</div>
    </div>
  </body>
</html>
