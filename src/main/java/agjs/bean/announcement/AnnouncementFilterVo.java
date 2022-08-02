package agjs.bean.announcement;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;

@Repository
public class AnnouncementFilterVo {

	// {startDate: "2022/7/24", anmStatus: ["1", "0"], anmTypeId: ["2", "3"]}
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date anmStartDate;
	private String[] anmStatus;
	private Integer[] anmTypeId;
	private String keyword;
	
	
	public AnnouncementFilterVo() {
	}

	@Override
	public String toString() {
		return "AnnouncementFilterVo [anmStartDate=" + anmStartDate + ", anmStatus=" + Arrays.toString(anmStatus)
				+ ", anmTypeId=" + Arrays.toString(anmTypeId) + ", keyword=" + keyword + "]";
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getAnmStartDate() {
		return anmStartDate;
	}

	public void setAnmStartDate(Date anmStartDate) {
		this.anmStartDate = anmStartDate;
	}

	public String[] getAnmStatus() {
		return anmStatus;
	}

	public void setAnmStatus(String[] anmStatus) {
		this.anmStatus = anmStatus;
	}

	public Integer[] getAnmTypeId() {
		return anmTypeId;
	}

	public void setAnmTypeId(Integer[] anmTypeId) {
		this.anmTypeId = anmTypeId;
	}

}
