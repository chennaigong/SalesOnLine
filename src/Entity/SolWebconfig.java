package Entity;

/**
 * SolWebconfig entity. @author MyEclipse Persistence Tools
 */

public class SolWebconfig implements java.io.Serializable {

	// Fields

	private Integer webconfigId;
	private String webconfigKey;
	private String webconfigValue;

	// Constructors

	/** default constructor */
	public SolWebconfig() {
	}

	/** minimal constructor */
	public SolWebconfig(Integer webconfigId) {
		this.webconfigId = webconfigId;
	}

	/** full constructor */
	public SolWebconfig(Integer webconfigId, String webconfigKey,
			String webconfigValue) {
		this.webconfigId = webconfigId;
		this.webconfigKey = webconfigKey;
		this.webconfigValue = webconfigValue;
	}

	// Property accessors

	public Integer getWebconfigId() {
		return this.webconfigId;
	}

	public void setWebconfigId(Integer webconfigId) {
		this.webconfigId = webconfigId;
	}

	public String getWebconfigKey() {
		return this.webconfigKey;
	}

	public void setWebconfigKey(String webconfigKey) {
		this.webconfigKey = webconfigKey;
	}

	public String getWebconfigValue() {
		return this.webconfigValue;
	}

	public void setWebconfigValue(String webconfigValue) {
		this.webconfigValue = webconfigValue;
	}

}