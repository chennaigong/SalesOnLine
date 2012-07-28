/**
 * @param tbid 	table的id
 * @param str 	传入的值
 * @return		在table最后一行加入str内容
 */
function insertTr(tbid,str)
{
	var rowNum=$("#"+tbid+" tr").length;
	$("#"+tbid+" tr:eq("+(rowNum-1)+")").after(str);
}

/**
 * @param str 	需要截的字符串(淘宝API返回的结果)
 * @return		json格式
 */
function strCut(str)
{
	var start=str.indexOf("[");
	var end=str.lastIndexOf("]")+1;
	var datacut=str.substring(start,end);
	return eval(datacut);
}

/**
 * @param o	table的id
 * @param a	奇数行背景
 * @param b 偶数行背景
 * @return
 */
function senfe(o,a,b){
	 var t=document.getElementById(o).getElementsByTagName("tr");
	 for(var i=0;i<t.length;i++){
		  t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
		  t[i].onmouseout=function(){
		   if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
		  }
	 }
}

function enTozh(status)
{
	var array=new Array();
	if(status=="TRADE_NO_CREATE_PAY")
	{
		array[0]="无";
		array[1]="没有创建支付宝交易";
	}
	else if(status=="WAIT_SELLER_SEND_GOODS")
	{
		array[0]='<input type="button" value="发货" onclick="tranGoods(this)"/>';
		array[1]="等待卖家发货";
	}
	else if(status=="WAIT_BUYER_PAY")
	{
		array[0]="无";
		array[1]="等待买家付款";
	}
	else if(status=="WAIT_BUYER_CONFIRM_GOODS")
	{
		array[0]="无";
		array[1]="卖家已发货";
	}
	else if(status=="TRADE_BUYER_SIGNED")
	{
		array[0]="无";
		array[1]="买家已签收";
	}
	else if(status=="TRADE_FINISHED")
	{
		array[0]="无";
		array[1]="交易成功";
	}
	else if(status=="TRADE_CLOSED")
	{
		array[0]="无";
		array[1]="退款成功，交易关闭";
	}
	else if(status=="TRADE_CLOSED_BY_TAOBAO")
	{
		array[0]="无";
		array[1]="交易关闭";
	}
	return array;
}