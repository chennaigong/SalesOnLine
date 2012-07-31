package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolFunction;

public class FunctionAction extends BaseAction{
	
	private String name;
	private String code;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String functionList()
	{
		try {
			
			List<SolFunction> functionList=functionservice.functionList();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<functionList.size();i++)
			{
				SolFunction function=functionList.get(i);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", function.getFunctionId());
				jsonObject.put("name", function.getFunctionName());
				jsonObject.put("code", function.getFunctionCode());
				jsonArray.put(jsonObject);
			}
			responseMsg=jsonArray.toString();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String doAddFunction()
	{
		functionservice.addFunction(name, code);
		responseMsg="1";
		return SUCCESS;
	}
	
	public String deleteFunction()
	{
		functionservice.deleteFunction(id);
		responseMsg="1";
		return SUCCESS;
	}
	
	public String modifyFunction()
	{
		SolFunction function=new SolFunction();
		function.setFunctionId(id);
		function.setFunctionName(name);
		function.setFunctionCode(code);
		
		functionservice.modifyFunction(function);
		responseMsg="1";
		return SUCCESS;
	}
	
}
