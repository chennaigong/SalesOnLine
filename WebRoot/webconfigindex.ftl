<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网站配置</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("webconfigList.action", function(data) {
				 	var jsondata=strCut(data);
				 	var defaultTime=jsondata[0].defaultTime;
				 	var threadInterval=jsondata[0].threadInterval;
				 	$("#defaultTime").val(defaultTime);
				 	$("#threadInterval").val(threadInterval);
				 });
				 $("#save").click(function()
				 {
				 	 $.post("updateWebconfig.action",{defaultTime:$("#defaultTime").val(),threadInterval:$("#threadInterval").val()}, 
				 	 function(data) {
					 	 $.post("webconfigList.action", function(data) {
						 	var jsondata=strCut(data);
						 	var defaultTime=jsondata[0].defaultTime;
						 	var threadInterval=jsondata[0].threadInterval;
						 	$("#defaultTime").val(defaultTime);
						 	$("#threadInterval").val(threadInterval);
						 });
				 	 	 alert("保存成功");
				 	 });
				 });
			}
		)
	</script>
  </head>
  <body>
  	<input type="hidden" id=""/>
  	更新开始时间(没数据)：<input type="text" id="defaultTime" /><br/>
  	同步的间隔时间：<input type="text" id="threadInterval"/><br/>
  	<input type="button" id="save" value="保存"/>
  </body>
</html>
