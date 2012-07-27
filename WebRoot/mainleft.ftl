<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="CSS/left.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="JS/jquery.easing.1.3.js"></script>
<script>
$(document).ready(function(){
	$.easing.def = "easeOutBounce";
	$('.dropdown:eq(0)').slideDown('slow');
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
		    <li class="button"><a href="#" class="T1">我的信息</a></li>

            <li class="dropdown">
                <ul>
                    <li><a href="tradeIndex.action" target="main">-我的订单</a></li>
                    <li><a href="rateIndex.action" target="main">-我的评价</a></li>
                </ul>
			</li>
          </ul>
      </li>
      
  </ul>
  <li class="two"><a href="#">管理界面</a></li>
</ul> 
</body>
</html>