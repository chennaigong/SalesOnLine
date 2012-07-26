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