package agjs.bean.journey;

import java.util.Arrays;

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
@Table(name = "JOURNEY")
public class JourneyPo extends CoreBean {

	@Id
	@Column(name = "JOURNEY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer journeyId;

	@Column(name = "JOURNEY_NAME", nullable = false)
	private String journeyName;

	@Column(name = "JOURNEY_TYPE_ID", nullable = false)
	private Integer typeId;

	@Column(name = "JOURNEY_PRICE", nullable = false)
	private Integer journeyPrice;

	@Column(name = "JOURNEY_PRICE_CHILD", nullable = false)
	private Integer journeyPriceChild;

	@Column(name = "APPLY_LIMIT", nullable = false)
	private Integer applyLimit;

	@Column(name = "JOURNEY_PICTURE")
	private byte[] journeyPicture;

	@Column(name = "JOURNET_INFO")
	private String journeyInfo;

	@Column(name = "LAUNCHED", nullable = false)
	private boolean launched;

	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date date;

	@Transient
	private String dateString;

	@Transient
	private Integer peopleCount;

	@Transient
	private String typeName;

	@Transient
	private Integer rest;

	public JourneyPo() {
	}

	public Integer getJourneyId() {
		return journeyId;
	}

	@Override
	public String toString() {
		return "JourneyPo [journeyId=" + journeyId + ", journeyName=" + journeyName + ", typeId=" + typeId
				+ ", journeyPrice=" + journeyPrice + ", journeyPriceChild=" + journeyPriceChild + ", applyLimit="
				+ applyLimit + ", journeyPicture=" + Arrays.toString(journeyPicture) + ", journeyInfo=" + journeyInfo
				+ ", launched=" + launched + ", date=" + date + ", dateString=" + dateString + ", peopleCount="
				+ peopleCount + ", typeName=" + typeName + ", rest=" + rest + "]";
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

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getJourneyPrice() {
		return journeyPrice;
	}

	public void setJourneyPrice(Integer journeyPrice) {
		this.journeyPrice = journeyPrice;
	}

	public Integer getJourneyPriceChild() {
		return journeyPriceChild;
	}

	public void setJourneyPriceChild(Integer journeyPriceChild) {
		this.journeyPriceChild = journeyPriceChild;
	}

	public Integer getApplyLimit() {
		return applyLimit;
	}

	public void setApplyLimit(Integer applyLimit) {
		this.applyLimit = applyLimit;
	}

	public byte[] getJourneyPicture() {
		return journeyPicture;
	}

	public void setJourneyPicture(byte[] journeyPicture) {
		this.journeyPicture = journeyPicture;
	}

	public String getJourneyInfo() {
		return journeyInfo;
	}

	public void setJourneyInfo(String journeyInfo) {
		this.journeyInfo = journeyInfo;
	}

	public boolean isLaunched() {
		return launched;
	}

	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Integer getRest() {
		return rest;
	}

	public void setRest(Integer rest) {
		this.rest = rest;
	}

}
