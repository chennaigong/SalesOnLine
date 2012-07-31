package ServiceImpl;

import java.util.List;

import Entity.SolFunction;
import Entity.SolRates;
import Service.FunctionService;

public class FunctionServiceImpl extends BaseServiceImpl<SolFunction> implements FunctionService{
	
	public List<SolFunction> functionList()
	{
		return basedao.findAll(SolFunction.class);
	}

	@Override
	public void addFunction(String name, String code) {
		SolFunction function=new SolFunction();
		function.setFunctionName(name);
		function.setFunctionCode(code);
		basedao.save(function);
	}

	@Override
	public void deleteFunction(int id) {
		SolFunction function=basedao.findById(SolFunction.class, id);
		basedao.delete(function);
	}

	@Override
	public void modifyFunction(SolFunction function) {
		basedao.saveOrUpdate(function);
		
	}

}
