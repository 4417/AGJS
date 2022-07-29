package agjs.bean.journey;

import javax.persistence.Column;

public class JourneyItemVo {

	@Column(name = "JOURNEY_ITEM_ID")
	private Integer journeyItemId;

	private Integer sohId;

	@Column(name = "JOURNEY_NAME")
	private String journeyName;

	@Column(name = "ADULTS")
	private Integer adults;

	@Column(name = "CHILDREN")
	private Integer children;

	@Column(name = "JOURNEY_DATE")
	private java.util.Date journeyDate;

	public JourneyItemVo() {
	}

	@Override
	public String toString() {
		return "JourneyItemVo [journeyItemId=" + journeyItemId + ", sohId=" + sohId + ", journeyName=" + journeyName
				+ ", adults=" + adults + ", children=" + children + ", journeyDate=" + journeyDate + "]";
	}

	public Integer getJourneyItemId() {
		return journeyItemId;
	}

	public void setJourneyItemId(Integer journeyItemId) {
		this.journeyItemId = journeyItemId;
	}

	public Integer getSohId() {
		return sohId;
	}

	public void setSohId(Integer sohId) {
		this.sohId = sohId;
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

	public java.util.Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(java.util.Date journeyDate) {
		this.journeyDate = journeyDate;
	}

}
