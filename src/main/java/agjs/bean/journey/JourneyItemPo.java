package agjs.bean.journey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import agjs.bean.CoreBean;

@Entity
@DynamicUpdate
@Table(name = "JOURNEY_ITEM")
public class JourneyItemPo extends CoreBean {

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

	@Transient
	private Integer totalPrice;

	public JourneyItemPo() {
	}

	@Override
	public String toString() {
		return "JourneyItemPo [journeyItemId=" + journeyItemId + ", sohId=" + sohId + ", journeyId=" + journeyId
				+ ", adults=" + adults + ", children=" + children + ", journeyDate=" + journeyDate + ", totalPrice="
				+ totalPrice + "]";
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

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
