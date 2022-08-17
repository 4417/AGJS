package agjs.bean.room;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StartBookingVo {

	private String[] styleIdStrings;

	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "GMT+8")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "GMT+8")
	private Date endDate;

	public StartBookingVo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StartBookingVo [styleIdStrings=" + Arrays.toString(styleIdStrings) + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	public String[] getStyleIdStrings() {
		return styleIdStrings;
	}

	public void setStyleIdStrings(String[] styleIdStrings) {
		this.styleIdStrings = styleIdStrings;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
