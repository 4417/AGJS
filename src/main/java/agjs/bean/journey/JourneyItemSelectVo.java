package agjs.bean.journey;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 搜尋行程訂單 接收前端的查詢資料
 */
public class JourneyItemSelectVo {

	private String sohId;
	private String journeyItemId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date endDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date updateDate;
	private String typeId;
	private String typeName;

	public JourneyItemSelectVo() {
	}

	@Override
	public String toString() {
		return "JourneyItemSelectVo [sohId=" + sohId + ", journeyItemId=" + journeyItemId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", updateDate=" + updateDate + ", typeId=" + typeId + ", typeName="
				+ typeName + "]";
	}

	public String getJourneyItemId() {
		return journeyItemId;
	}

	public void setJourneyItemId(String journeyItemId) {
		this.journeyItemId = journeyItemId;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getSohId() {
		return sohId;
	}

	public void setSohId(String sohId) {
		this.sohId = sohId;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
