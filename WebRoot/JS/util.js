/**
 * @param tbid 	table��id
 * @param str 	�����ֵ
 * @return		��table���һ�м���str����
 */
function insertTr(tbid,str)
{
	var rowNum=$("#"+tbid+" tr").length;
	$("#"+tbid+" tr:eq("+(rowNum-1)+")").after(str);
}
/**
 * @param str 	��Ҫ�ص��ַ���(�Ա�API���صĽ��)
 * @return		json��ʽ
 */
function strCut(str)
{
	var start=str.indexOf("[");
	var end=str.lastIndexOf("]")+1;
	var datacut=str.substring(start,end);
	return eval(datacut);
}
