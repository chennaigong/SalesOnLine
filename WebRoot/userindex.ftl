<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="JS/util.js"></script>
	<script type="text/javascript">
		$(document).ready
		(
			function()
			{
				 $.post("userList.action", function(data) {
				 	var jsondata=strCut(data);
					$.each(jsondata,function(index)
					{
						var _tdtd="</td><td align='center' height=50>";
						var trtd="<tr><td>";
						var obj=jsondata[index];
						var _td_tr="</td></tr>";
						var tradeSee="<input type='button' value='查看订单' onclick='seeTrade(this)'/>"
						var rateSee="<input type='button' value='查看评价' onclick='seeRate(this)'/>"
						var str=trtd+obj.username+_tdtd+obj.password+_tdtd+obj.session+_tdtd+obj.ispromise+_tdtd+tradeSee+rateSee+_td_tr
						insertTr("usertable",str);
					});
				 });
			}
		)
		//查出用户名
		function seeTrade(obj)
		{
			var username=$(obj).parent().parent().children("td").html();
			window.location.href="tradeIndex.action?username="+username;
		}
		//查出用户名
		function seeRate(obj)
		{
			var username=$(obj).parent().parent().children("td").html();
			window.location.href="rateIndex.action?username="+username;
		}
	</script>
  </head>
  
  <body>
  	<table id="usertable">
		<tr>
			<th width=200>用户名</th>
			<th width=200>密码</th>
			<th width=200>sessionKey</th>
			<th width=200>是否授权</th>
			<th width=200>操作</th>
		</tr>
	</table>
  </body>
</html>
