package agjs.bean.journey;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import agjs.bean.CoreBean;

@Entity
@Table(name = "JOURNEY")
public class JourneyPo extends CoreBean {

//	JOURNEY_ID  int NOT NULL行程編號
//	JOURNEY_NAME  varchar(20) NOT NULL行程名稱
//	JOURNEY_TYPE_ID  int NOT NULLFK_行程類型編號
//	JOURNEY_PRICE  int NOT NULL行程價格
//	JOURNEY_PRICE_CHILD  int NOT NULL行程價格(兒童)
//	APPLY_LIMIT  int NOT NULL報名人數上限
//	JOURNEY_PICTURE  blob NULL行程照片
//	JOURNET_INFO  text NULL行程介紹
//	LAUNCHED tiny  int NOT NULL是否上架(是否開放加購)

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
				+ ", launched=" + launched + "]";
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

}
