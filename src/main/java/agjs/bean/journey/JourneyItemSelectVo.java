package agjs.bean.journey;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 搜尋行程訂單 接收前端的查詢資料
 */
@Repository
public class JourneyItemSelectVo {

	private String sohId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date endDate;
	private String typeName;

	public JourneyItemSelectVo() {
	}

	@Override
	public String toString() {
		return "JourneyItemSelectVo [sohId=" + sohId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", typeName=" + typeName + "]";
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
