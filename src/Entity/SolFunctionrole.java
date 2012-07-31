package Entity;

/**
 * SolFunctionrole entity. @author MyEclipse Persistence Tools
 */

public class SolFunctionrole implements java.io.Serializable {

	// Fields

	private Integer functionroleId;
	private SolRole solRole;
	private SolFunction solFunction;
	private String functionroleMark;

	// Constructors

	/** default constructor */
	public SolFunctionrole() {
	}

	/** minimal constructor */
	public SolFunctionrole(Integer functionroleId) {
		this.functionroleId = functionroleId;
	}

	/** full constructor */
	public SolFunctionrole(Integer functionroleId, SolRole solRole,
			SolFunction solFunction, String functionroleMark) {
		this.functionroleId = functionroleId;
		this.solRole = solRole;
		this.solFunction = solFunction;
		this.functionroleMark = functionroleMark;
	}

	// Property accessors

	public Integer getFunctionroleId() {
		return this.functionroleId;
	}

	public void setFunctionroleId(Integer functionroleId) {
		this.functionroleId = functionroleId;
	}

	public SolRole getSolRole() {
		return this.solRole;
	}

	public void setSolRole(SolRole solRole) {
		this.solRole = solRole;
	}

	public SolFunction getSolFunction() {
		return this.solFunction;
	}

	public void setSolFunction(SolFunction solFunction) {
		this.solFunction = solFunction;
	}

	public String getFunctionroleMark() {
		return this.functionroleMark;
	}

	public void setFunctionroleMark(String functionroleMark) {
		this.functionroleMark = functionroleMark;
	}

}