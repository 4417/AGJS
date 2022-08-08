package agjs.bean.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ADMINISTRATOR")
public class AdministratorPo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ADMINISTRATOR_ID")
	private Integer administratorId;
	@Column(name = "ADMINISTRATOR_ACCOUNT")
	private String administratorAccount;
	@Column(name = "ADMINISTRATOR_PASSWORD")
	private String administratorPassword;
	@Transient
	private String errorMsg;
	@Transient
	private String email;
	@Transient
	private String verifyMsg;
	@Transient
	private String newAdministratorPassword;
	
	public AdministratorPo() {
	}

	public AdministratorPo(Integer administratorId, String administratorAccount, String administratorPassword) {
		super();
		this.administratorId = administratorId;
		this.administratorAccount = administratorAccount;
		this.administratorPassword = administratorPassword;
	}

	public Integer getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

	public String getAdministratorAccount() {
		return administratorAccount;
	}

	public void setAdministratorAccount(String administratorAccount) {
		this.administratorAccount = administratorAccount;
	}

	public String getAdministratorPassword() {
		return administratorPassword;
	}

	public void setAdministratorPassword(String administratorPassword) {
		this.administratorPassword = administratorPassword;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerifyMsg() {
		return verifyMsg;
	}

	public void setVerifyMsg(String verifyMsg) {
		this.verifyMsg = verifyMsg;
	}

	public String getNewAdministratorPassword() {
		return newAdministratorPassword;
	}

	public void setNewAdministratorPassword(String newAdministratorPassword) {
		this.newAdministratorPassword = newAdministratorPassword;
	}
	
	
	

}
