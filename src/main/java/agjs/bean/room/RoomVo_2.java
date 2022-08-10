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
	private Integer salesOrderHeaderId;
	private String journeyName;
	private Integer applyLimit;
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

	public Integer getSalesOrderHeaderId() {
		return salesOrderHeaderId;
	}

	public void setSalesOrderHeaderId(Integer salesOrderHeaderId) {
		this.salesOrderHeaderId = salesOrderHeaderId;
	}
	

	public String getJourneyName() {
		return journeyName;
	}

	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}

	public Integer getApplyLimit() {
		return applyLimit;
	}

	public void setApplyLimit(Integer applyLimit) {
		this.applyLimit = applyLimit;
	}

	@Override
	public String toString() {
		return "RoomVo_2 [count=" + count + ", msg=" + msg + ", orderStartDate=" + orderStartDate + ", orderEndDate="
				+ orderEndDate + ", roomName=" + roomName + ", orderRoomQuantity=" + orderRoomQuantity
				+ ", salesOrderHeaderId=" + salesOrderHeaderId + ", journeyName=" + journeyName + ", applyLimit="
				+ applyLimit + "]";
	}

	

	
	


	

}
