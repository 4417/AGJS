package agjs.bean.announcement;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ANNOUNCEMENT")
public class AnnouncementPo {
	
	@Id
	@Column(name = "ANM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer anmId;
	
	@Column(name = "ADMINISTRATOR_ID")
	private Integer administratorId;
	
	@Column(name = "ANM_ORDER_ID")
	private Integer anmOrderId;
	
	@Column(name = "ANM_STATUS")
	private String anmStatus;
	
	@Column(name = "ANM_TITLE")
	private String anmTitle;
	
	@Column(name = "ANM_CONTENT")
	private String anmContent;
	
	@Column(name = "ANM_TYPE_ID")
	private Integer anmTypeId;
	
	@Column(name = "ANM_START_DATE")
	private Date anmStartDate;
	
	@Column(name = "ANM_END_DATE", nullable = true)
	private Date anmEndDate;
	
	@Override
	public String toString() {
		return "AnnouncementPo [anmId=" + anmId + ", administratorId=" + administratorId + ", anmOrderId=" + anmOrderId
				+ ", anmStatus=" + anmStatus + ", anmTitle=" + anmTitle + ", anmContent=" + anmContent
				+ ", anmTypeId=" + anmTypeId + ", anmStartDate=" + anmStartDate + ", anmEndDate=" + anmEndDate + "]";
	}
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
	
	public String getAnmStatus() {
		return anmStatus;
	}
	public void setAnmStatus(String anmStatus) {
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
