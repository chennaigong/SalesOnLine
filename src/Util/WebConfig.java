package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WebConfig {
	public final static String APPKEY="21062760";	//应用appkey
	public final static String APPSECRET="24bbd02b8bbf9a4fe5943617468cd0b3";	//应用secret
	public final static String APIURL="http://gw.api.taobao.com/router/rest";
	
	public final static String GETSESSIONKEYURL="http://container.api.taobao.com/container?appkey="+APPKEY;	//获取sessionKey地址
	
	public final static String PROJECTNAME="SalesOnLine";
	
	//重启服务器
	public final static void resetServer(String fileUrl)
	{
		String resetBat="reset.bat";
		String content= "@echo off\r\n"+
						"call shutdown.bat\r\n"+
						"call startup.bat\r\n"+
						":end\r\n";
		File file=new File(fileUrl+resetBat);
		if(!file.exists())
		{
			try 
			{
				file.createNewFile();
				FileWriter fileWriter=new FileWriter(file);
				fileWriter.write(content);
				fileWriter.close();
				
			} catch (Exception e) {
			}
			
		}
		try {
			Process proc = Runtime.getRuntime().exec(fileUrl+resetBat);
		    StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "Error");            
		    StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "Output");
		    errorGobbler.start();
		    outputGobbler.start();
		    proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
