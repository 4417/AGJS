package agjs.bean.journey;

import org.springframework.stereotype.Repository;

@Repository
public class JourneySearchVo {

	String typeName;
	String searchKeyword;
	String startDate;
	String endDate;
	Integer sohId;

	public JourneySearchVo() {
	}

	@Override
	public String toString() {
		return "JourneySearchVo [typeName=" + typeName + ", searchKeyword=" + searchKeyword + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", sohId=" + sohId + "]";
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getSohId() {
		return sohId;
	}

	public void setSohId(Integer sohId) {
		this.sohId = sohId;
	}

}
