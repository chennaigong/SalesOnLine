<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>评价</title>
<link href="../CSS/tab.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="../CSS/style.css"/>
<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../JS/pie/highcharts.js"></script>
<script type="text/javascript" src="../JS/pie/exporting.js"></script>
<script type="text/javascript">
	$(document).ready
	(
		function()
		{
			 $.post("goodsRateList.action",{id:$("#id").val()},function(data) {
			 	var good=parseInt(data.split(",")[0]);
			 	var neutral=parseInt(data.split(",")[1]);
			 	var bad=parseInt(data.split(",")[2]);
			 	chart = new Highcharts.Chart({
		            chart: {
		                renderTo: 'container',
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: '货品的评价'
		            },
		            tooltip: {
		                formatter: function() {
		                    return '<b>'+ this.point.name +'</b>: '+ this.y;
		                }
		            },
		            plotOptions: {
		                pie: {
		                    allowPointSelect: true,
		                    cursor: 'pointer',
		                    dataLabels: {
		                        enabled: true,
		                        color: '#000000',
		                        connectorColor: '#000000',
		                        formatter: function() {
		                            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
		                        }
		                    }
		                }
		            },
		            series: [{
		                type: 'pie',
		                name: 'Browser share',
		                data: [
		                    ['好评',   good],
		                    ['中评',       neutral],
		                    {
		                        name: '差评',
		                        y: bad,
		                        sliced: true,
		                        selected: true
		                    }
		                ]
		            }]
		        });
			 });
		}
	)
</script>
</head>

<body>
<input type="hidden" id="id" value="${id}"/>
	<div class="head">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
	      <tr>
	        <td>
	          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
	            <tr>
	              <td class="font1">&nbsp;<a href="#">货品管理</a> &gt; <a href="goodsTypeIndex.action">货品列表</a> &gt; <a href="#">评价</a></td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	    </table>
	</div>
	<div class="con" style="margin-top:-2px;">
		<div id="container" style="height:500px;"></div>
	</div>
	
</body>
</html>
