package Service;

import java.util.List;

import Entity.SolFunction;
import Entity.SolRole;

public interface FunctionService {
	public List<SolFunction> functionList();
	public void addFunction(String name,String code);
	public void deleteFunction(int id);
	public void modifyFunction(SolFunction function);
}
