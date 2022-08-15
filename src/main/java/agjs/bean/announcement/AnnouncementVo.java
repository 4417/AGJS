package agjs.bean.announcement;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AnnouncementVo {
	
	private Integer anmId;
	private Integer anmOrderId;
	private String anmTitle;
	private String anmContent;
	private String anmStatus;
	private String anmType;
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date anmStartDate;
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date anmEndDate;
	
	public AnnouncementVo() {
	}

	@Override
	public String toString() {
		return "AnnouncementVo [anmId=" + anmId + ", anmOrderId=" + anmOrderId + ", anmTitle=" + anmTitle
				+ ", anmContent=" + anmContent + ", anmStatus=" + anmStatus + ", anmType=" + anmType + ", anmStartDate="
				+ anmStartDate + ", anmEndDate=" + anmEndDate + "]";
	}

	public Integer getAnmId() {
		return anmId;
	}

	public void setAnmId(Integer anmId) {
		this.anmId = anmId;
	}

	public Integer getAnmOrderId() {
		return anmOrderId;
	}

	public void setAnmOrderId(Integer anmOrderId) {
		this.anmOrderId = anmOrderId;
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

	public String getAnmType() {
		return anmType;
	}

	public void setAnmType(String anmType) {
		this.anmType = anmType;
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

	public String getAnmStatus() {
		return anmStatus;
	}

	public void setAnmStatus(String anmStatus) {
		this.anmStatus = anmStatus;
	}

}
