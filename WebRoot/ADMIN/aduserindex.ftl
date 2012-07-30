<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("userList.action", function(data) {
				 	var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td>";
						var trtd="<tr height='30' align='center' ><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var str=trtd+obj.username+_tdtd+obj.password+_tdtd+obj.post+_tdtd+obj.rating+_tdtd+obj.can+_td_tr
						insertTr("usertable",str);
						
						senfe("usertable","#F6F6F6","#FFFFFF");
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
	              <td class="font1">&nbsp;<a href="#">用户管理</a> &gt; <a href="#">用户列表1</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
        <table id="usertable" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#DBE6E3"  class="warp_table">
          <tr height="34" align="center" class="table_title">
            <td width=20%>用户名</td>
			<td width=20%>密码</td>
			<td width=20%>职位</td>
			<td width=20%>等级</td>
			<td width=20%>可操作店铺编号</td>
          </tr>
        </table>
     </div>
  </body>
</html>
