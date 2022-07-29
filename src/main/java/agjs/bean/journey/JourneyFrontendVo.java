package agjs.bean.journey;

import java.util.Arrays;

import org.springframework.stereotype.Repository;

/**
 * mapping 前端json 新增/編輯 欄位 frontend
 */
@Repository
public class JourneyFrontendVo {

//	{"journeyName":"繁星沙岸",
//		"journeyPrice":"",
//		"journeyPriceChild":"",
//		"applyLimit":"",
//		"journeyInfo":"",
//		"launched":"上架",
//		"journeyPicture":"11111"}

	private String journeyId;
	private String journeyName;
	private String journeyTypeName;
	private Integer journeyPrice;
	private Integer journeyPriceChild;
	private Integer applyLimit;
	private byte[] journeyPicture;
	private String journeyInfo;
	private String launched;

	public JourneyFrontendVo() {
	}

	@Override
	public String toString() {
		return "JourneyFrontendVo [journeyId=" + journeyId + ", journeyName=" + journeyName + ", journeyTypeName="
				+ journeyTypeName + ", journeyPrice=" + journeyPrice + ", journeyPriceChild=" + journeyPriceChild
				+ ", applyLimit=" + applyLimit + ", journeyPicture=" + Arrays.toString(journeyPicture)
				+ ", journeyInfo=" + journeyInfo + ", launched=" + launched + "]";
	}

	public String getJourneyTypeName() {
		return journeyTypeName;
	}

	public void setJourneyTypeName(String journeyTypeName) {
		this.journeyTypeName = journeyTypeName;
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

	public String getLaunched() {
		return launched;
	}

	public void setLaunched(String launched) {
		this.launched = launched;
	}

}
