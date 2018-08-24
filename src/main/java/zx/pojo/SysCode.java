package zx.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类
 * @author zhangxing
 *
 */
public class SysCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codeValueId;
	private String code;
	private String codeName;
	private String codeValue;
	private String codeValueName;
	private String enabledFlag;
	private Date creationDate;
	public int getCodeValueId() {
		return codeValueId;
	}
	public void setCodeValueId(int codeValueId) {
		this.codeValueId = codeValueId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getCodeValueName() {
		return codeValueName;
	}
	public void setCodeValueName(String codeValueName) {
		this.codeValueName = codeValueName;
	}
	public String getEnabledFlag() {
		return enabledFlag;
	}
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SysCode [codeValueId=" + codeValueId + ", code=" + code + ", codeName=" + codeName + ", codeValue="
				+ codeValue + ", codeValueName=" + codeValueName + ", enabledFlag=" + enabledFlag + ", creationDate="
				+ creationDate + "]";
	}
	
	
}
