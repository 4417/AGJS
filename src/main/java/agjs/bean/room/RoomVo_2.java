package agjs.bean.room;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RoomVo_2 {
	
	private Integer count;
	private String msg;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderEndDate;
	private String roomName;
	private Integer orderRoomQuantity; 
	public RoomVo_2() {
		super();
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getOrderStartDate() {
		return orderStartDate;
	}
	public void setOrderStartDate(Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}
	public Date getOrderEndDate() {
		return orderEndDate;
	}
	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public Integer getOrderRoomQuantity() {
		return orderRoomQuantity;
	}
	public void setOrderRoomQuantity(Integer orderRoomQuantity) {
		this.orderRoomQuantity = orderRoomQuantity;
	}

	@Override
	public String toString() {
		return "RoomVo_2 [count=" + count + ", msg=" + msg + ", orderStartDate=" + orderStartDate + ", orderEndDate="
				+ orderEndDate + ", roomName=" + roomName + ", orderRoomQuantity=" + orderRoomQuantity + "]";
	}
	


	

}
