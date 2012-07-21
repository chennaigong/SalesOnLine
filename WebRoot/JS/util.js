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
