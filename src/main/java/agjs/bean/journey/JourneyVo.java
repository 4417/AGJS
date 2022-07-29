package agjs.bean.journey;

import org.springframework.stereotype.Repository;

/**
 * 搜尋行程 將JourneyPo轉回前端格式
 */
@Repository
public class JourneyVo {

//content.journeyTypeName
//content.journeyPrice
//content.journeyPriceChild
//content.applyLimit
//content.launched
	// journeyId
	// journeyPicture
	private String journeyId;
	private String journeyName;
	private String journeyTypeName;
	private String journeyPrice;
	private String journeyPriceChild;
	private String applyLimit;
	private boolean launched;
	private String journeyPicture;
	private String info;

	public JourneyVo() {
	}

	@Override
	public String toString() {
		return "JourneyVo [journeyId=" + journeyId + ", journeyTypeName=" + journeyTypeName + ", journeyPrice="
				+ journeyPrice + ", journeyPriceChild=" + journeyPriceChild + ", applyLimit=" + applyLimit
				+ ", launched=" + launched + ", journeyPicture=" + journeyPicture + "]";
	}

	public String getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(String journeyId) {
		this.journeyId = journeyId;
	}

	public String getJourneyName() {
		return journeyName;
	}

	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}

	public String getJourneyTypeName() {
		return journeyTypeName;
	}

	public void setJourneyTypeName(String journeyTypeName) {
		this.journeyTypeName = journeyTypeName;
	}

	public String getJourneyPrice() {
		return journeyPrice;
	}

	public void setJourneyPrice(String journeyPrice) {
		this.journeyPrice = journeyPrice;
	}

	public String getJourneyPriceChild() {
		return journeyPriceChild;
	}

	public void setJourneyPriceChild(String journeyPriceChild) {
		this.journeyPriceChild = journeyPriceChild;
	}

	public String getApplyLimit() {
		return applyLimit;
	}

	public void setApplyLimit(String applyLimit) {
		this.applyLimit = applyLimit;
	}

	public boolean isLaunched() {
		return launched;
	}

	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

	public String getJourneyPicture() {
		return journeyPicture;
	}

	public void setJourneyPicture(String journeyPicture) {
		this.journeyPicture = journeyPicture;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
