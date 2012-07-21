package Entity;

/**
 * Example entity. @author MyEclipse Persistence Tools
 */

public class Example implements java.io.Serializable {

	// Fields

	private Integer expId;
	private String expName;

	// Constructors

	/** default constructor */
	public Example() {
	}

	/** minimal constructor */
	public Example(Integer expId) {
		this.expId = expId;
	}

	/** full constructor */
	public Example(Integer expId, String expName) {
		this.expId = expId;
		this.expName = expName;
	}

	// Property accessors

	public Integer getExpId() {
		return this.expId;
	}

	public void setExpId(Integer expId) {
		this.expId = expId;
	}

	public String getExpName() {
		return this.expName;
	}

	public void setExpName(String expName) {
		this.expName = expName;
	}

}