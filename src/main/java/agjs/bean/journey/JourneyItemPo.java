package agjs.bean.journey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import agjs.bean.CoreBean;

@Entity
@Table(name = "JOURNEY_ITEM")
public class JourneyItemPo extends CoreBean {

//	JOURNEY_ITEM_ID int NOT NULL行程單編號
//	SALES_ORDER_HEADER_ID int NOT NULLFK_訂單編號
//	JOURNEY_ID int NOT NULLFK_行程編號
//	ADULTS int NULL大人
//	CHILDREN int NULL小孩
//	JOURNEY_DATE

	@Id
	@Column(name = "JOURNEY_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer journeyItemId;

	@Column(name = "SALES_ORDER_HEADER_ID", nullable = false)
	private Integer sohId;

	@Column(name = "JOURNEY_ID", nullable = false)
	private Integer journeyId;

	@Column(name = "ADULTS")
	private Integer adults;

	@Column(name = "CHILDREN")
	private Integer children;

	@Column(name = "JOURNEY_DATE", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date journeyDate;

	public JourneyItemPo() {
	}

	@Override
	public String toString() {
		return "JourneyItemPo [journeyItemId=" + journeyItemId + ", sohId=" + sohId + ", journeyId=" + journeyId
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

	public Integer getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
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
