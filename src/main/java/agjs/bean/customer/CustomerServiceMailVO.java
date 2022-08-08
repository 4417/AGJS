package agjs.bean.customer;

import java.sql.Date;

public class CustomerServiceMailVO implements java.io.Serializable {
	private Integer faqFormId;
	private String faqTypeName;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String contentText;
	private Date createDate;
	
	public Integer getFaqFormId() {
		return faqFormId;
	}
	public void setFaqFormId(Integer faqFormId) {
		this.faqFormId = faqFormId;
	}
	public String getFaqTypeName() {
		return faqTypeName;
	}
	public void setFaqTypeName(String faqTypeName) {
		this.faqTypeName = faqTypeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getContentText() {
		return contentText;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
