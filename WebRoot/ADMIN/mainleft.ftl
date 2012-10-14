<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="../CSS/left.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../JS/jquery.easing.1.3.js"></script>
<script>
$(document).ready(function(){
	$.easing.def = "easeOutBounce";
	$('.dropdown:eq(1)').slideDown('slow');
	$('li.button a').click(function(e){
		var dropDown = $(this).parent().next();
		$('.dropdown').not(dropDown).slideUp('slow');
		dropDown.slideToggle('slow');
		e.preventDefault();
	})
});
</script>
</head>
<body>
<ul class="sidemenu">
  <li class="one"><a href="#">管理界面</a></li>
  <ul class="container">
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">用户管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="userIndex.action" target="main">-用户列表</a></li>
                    <li><a href="addUser.action" target="main">-添加用户</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">店铺管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="shopIndex.action" target="main">-店铺列表</a></li>
                    <li><a href="javascript:parent.location.href='mainIndex.action'" target="main">-授权店铺</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">订单管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="allTradeIndex.action" target="main">-订单列表</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">评价管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="allRateIndex.action" target="main">-评价列表</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">功能管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="functionIndex.action" target="main">-功能列表</a></li>
                    <li><a href="addFunction.action" target="main">-添加功能</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">角色管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="roleIndex.action" target="main">-角色列表</a></li>
                    <li><a href="addRole.action" target="main">-添加角色</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">货品管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="goodsTypeIndex.action" target="main">-货品列表</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">采购管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="purchaseIndex.action" target="main">-采购货品</a></li>
                    <li><a href="purchaseList.action" target="main">-采购记录</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">出入库管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="inputGoodsIndex.action" target="main">-入库列表</a></li>
                    <li><a href="outputGoodsIndex.action" target="main">-出库列表</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">店铺商品管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="tbGoodsIndex.action" target="main">-店铺商品列表</a></li>
                </ul>
			</li>
          </ul>
      </li>
      <li class="menu">
          <ul>
		    <li class="button"><a href="#" class="T1">网站管理</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="webconfigIndex.action" target="main">-网站配置</a></li>
                </ul>
			</li>
          </ul>
      </li>
      
  </ul>
  <li class="two"><a href="#">管理界面</a></li>
</ul> 
</body>
</html>
