package agjs.bean.room;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * mapping 前端json 欄位
 */
public class RoomStatusVo {

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date endDate;
	private List<String> emptyRoomId;
	private Set<String> emptyRoomStyleId;
	private boolean emptyRoomStatus;

	public RoomStatusVo() {
	}

	@Override
	public String toString() {
		return "RoomStatusVo [startDate=" + startDate + ", endDate=" + endDate + ", emptyRoomId=" + emptyRoomId
				+ ", emptyRoomStyleId=" + emptyRoomStyleId + ", emptyRoomStatus=" + emptyRoomStatus + "]";
	}

	public Set<String> getEmptyRoomStyleId() {
		return emptyRoomStyleId;
	}

	public void setEmptyRoomStyleId(Set<String> emptyRoomStyleId) {
		this.emptyRoomStyleId = emptyRoomStyleId;
	}

	public List<String> getEmptyRoomId() {
		return emptyRoomId;
	}

	public void setEmptyRoomId(List<String> emptyRoomId) {
		this.emptyRoomId = emptyRoomId;
	}

	public boolean isEmptyRoomStatus() {
		return emptyRoomStatus;
	}

	public void setEmptyRoomStatus(boolean emptyRoomStatus) {
		this.emptyRoomStatus = emptyRoomStatus;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

}
