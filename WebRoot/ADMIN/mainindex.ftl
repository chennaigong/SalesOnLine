﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>淘宝店铺管理系统</title>
<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
<script>
	$(document).ready
	(
		function()
		{
			<#if (responseMsg?exists)>
				alert("${responseMsg}")
			</#if>
		}
	)
</script>
</head>
<frameset rows="70,*,24" cols="*" framespacing="0" frameborder="no" border="0">
  <frame src="mainTop.action" name="topFrame" scrolling="no">
  <frameset cols="240,*" name="btFrame" frameborder="NO" border="0" framespacing="0">
    <frame src="mainLeft.action" noresize name="menu" scrolling="yes">
    <frame src="addShop.action" noresize name="main" scrolling="yes">
  </frameset>
  <frame src="mainDown.action" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
  <input type="text" id="top_session" value="123"/>
</frameset>

<noframes><body>
</body>
</noframes></html>
