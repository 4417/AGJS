package agjs.bean;

import java.sql.Date;

public class AnnouncementPo {
	
	private Integer anmId;
	private Integer administratorId;
	private Integer anmOrderId;
	private Integer anmStatus;
	private String anmTitle;
	private String anmContent;
	private Integer anmTypeId;
	private Date anmStartDate;
	private Date anmEndDate;
	
	public AnnouncementPo() {
		super();
	}
	public Integer getAnmId() {
		return anmId;
	}
	public void setAnmId(Integer anmId) {
		this.anmId = anmId;
	}
	
	public Integer getAdministratorId() {
		return administratorId;
	}
	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}
	
	public Integer getAnmOrderId() {
		return anmOrderId;
	}
	public void setAnmOrderId(Integer anmOrderId) {
		this.anmOrderId = anmOrderId;
	}
	
	public Integer getAnmStatus() {
		return anmStatus;
	}
	public void setAnmStatus(Integer anmStatus) {
		this.anmStatus = anmStatus;
	}
	
	public String getAnmTitle() {
		return anmTitle;
	}
	public void setAnmTitle(String anmTitle) {
		this.anmTitle = anmTitle;
	}
	
	public String getAnmContent() {
		return anmContent;
	}
	public void setAnmContent(String anmContent) {
		this.anmContent = anmContent;
	}
	
	public Integer getAnmTypeId() {
		return anmTypeId;
	}
	public void setAnmTypeId(Integer anmTypeId) {
		this.anmTypeId = anmTypeId;
	}
	
	public Date getAnmStartDate() {
		return anmStartDate;
	}
	public void setAnmStartDate(Date anmStartDate) {
		this.anmStartDate = anmStartDate;
	}
	
	public Date getAnmEndDate() {
		return anmEndDate;
	}
	public void setAnmEndDate(Date anmEndDate) {
		this.anmEndDate = anmEndDate;
	}
}
