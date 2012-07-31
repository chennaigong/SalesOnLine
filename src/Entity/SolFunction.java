package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolFunction entity. @author MyEclipse Persistence Tools
 */

public class SolFunction implements java.io.Serializable {

	// Fields

	private Integer functionId;
	private String functionName;
	private String functionCode;
	private Set solFunctionroles = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolFunction() {
	}

	/** minimal constructor */
	public SolFunction(Integer functionId) {
		this.functionId = functionId;
	}

	/** full constructor */
	public SolFunction(Integer functionId, String functionName,
			String functionCode, Set solFunctionroles) {
		this.functionId = functionId;
		this.functionName = functionName;
		this.functionCode = functionCode;
		this.solFunctionroles = solFunctionroles;
	}

	// Property accessors

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionCode() {
		return this.functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public Set getSolFunctionroles() {
		return this.solFunctionroles;
	}

	public void setSolFunctionroles(Set solFunctionroles) {
		this.solFunctionroles = solFunctionroles;
	}

}