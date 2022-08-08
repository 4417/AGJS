package agjs.bean.journey;

import agjs.bean.CoreBean;

public class JourneyTypeVo extends CoreBean {

	private String typeId;
	private String journeyType;

	public JourneyTypeVo() {

	}

	@Override
	public String toString() {
		return "JourneyTypeVo [typeId=" + typeId + ", journeyType=" + journeyType + "]";
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getJourneyType() {
		return journeyType;
	}

	public void setJourneyType(String journeyType) {
		this.journeyType = journeyType;
	}

}
