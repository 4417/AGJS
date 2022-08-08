package agjs.bean.room;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class RoomUsedRecordVo {
	@EmbeddedId
	private RoomUsedRecordIdVo roomUsedRecordIdVo;
	private Integer roomStyleId;
	private String roomName;
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "GMT+8")
	private Date orderEndDate;
	private String userName;
	private Integer source;
	
	public Integer getRoomId() {
		return roomUsedRecordIdVo.getRoomId();
	}

	public Date getOrderStartDate() {
		return roomUsedRecordIdVo.getOrderStartDate();
	}

	public Integer getRoomStyleId() {
		return roomStyleId;
	}

	public void setRoomStyleId(Integer roomStyleId) {
		this.roomStyleId = roomStyleId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Date getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
}
