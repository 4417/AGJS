package agjs.bean.journey;


import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

/***給後臺訂單顯示用的行程單***/


public class JourneyItemVo_2 {

	private Integer journeyId;			//行程單編號
	private String journeyName;			//行程名稱
	private Integer adults;  			//每筆行程明細的大人數量
	private Integer children;  			//每筆行程明細的小孩數量
	private Integer journeyItemPrice;  	//每筆行程明細的總金額
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date journeyDate;			//行程日期
	

	
	@Override
	public String toString() {
		return "JourneyItemVo_2 [journeyId=" + journeyId + ", journeyName=" + journeyName + ", adults=" + adults
				+ ", children=" + children + ", journeyItemPrice=" + journeyItemPrice + ", journeyDate=" + journeyDate
				+ "]";
	}
	public Integer getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
	}
	public String getJourneyName() {
		return journeyName;
	}
	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}
	public Integer getAdults() {
		return adults;
	}
	public void setAdults(Integer adults) {
		this.adults = adults;
	}
	public Integer getChildren() {
		return children;
	}
	public void setChildren(Integer children) {
		this.children = children;
	}
	public Integer getJourneyItemPrice() {
		return journeyItemPrice;
	}
	public void setJourneyItemPrice(Integer journeyItemPrice) {
		this.journeyItemPrice = journeyItemPrice;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	
	
}
