<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网站配置</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript" src="../JS/My97DatePicker/WdatePicker.js"></script>
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
				 	 if($("#defaultTime").val()=="")
				 	 {
				 	 	alert("请输入开始时间")
				 	 	return;
				 	 }
				 	 if($("#threadInterval").val()=="")
				 	 {
				 	 	alert("请输入间隔时间")
				 	 	return;
				 	 }
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
  	<div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">网站管理</a> &gt; <a href="#">网站配置</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
	  	更新开始时间(没数据)：<input type="text" id="defaultTime"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',qsEnabled:true,quickSel:['2000-1-10','2000-2-20']})"/><br/><br/>
	  	同步的间隔时间：<input type="text" id="threadInterval" /><br/><br/>
	  	<input type="button" id="save" value="保存"/>
	</div>
  </body>
</html>
