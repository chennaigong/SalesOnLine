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

/**
 * @param o	table��id
 * @param a	�����б���
 * @param b ż���б���
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