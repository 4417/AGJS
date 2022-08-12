package agjs.bean.order;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


//給後台查詢訂單顯示用的Vo
public class SalesOrderVo {

	private Integer salesOrderHeaderId;	//訂單編號
	private String userName;			//會員姓名
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createDate;			//訂單建立日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderStartDate;		//入住日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderEndDate;			//退房日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderChangeDate;		//訂單修改日期
	private String salesOrderStatus;	//訂單狀態
	private String orderRemark;			//訂單備註
//	private List<RoomPo> roomList;		//房型明細list
	private String roomName;			//房型名稱
	private Integer orderRoomQuantity;  //每筆訂單明細的房間數量
	private Integer orderRoomPrice;  	//每筆訂單明細的房間單價
	private Integer journeyItemPrice;	//行程單總額
	
	public Integer getSalesOrderHeaderId() {
		return salesOrderHeaderId;
	}
	public void setSalesOrderHeaderId(Integer salesOrderHeaderId) {
		this.salesOrderHeaderId = salesOrderHeaderId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public Date getOrderChangeDate() {
		return orderChangeDate;
	}
	public void setOrderChangeDate(Date orderChangeDate) {
		this.orderChangeDate = orderChangeDate;
	}
	public String getSalesOrderStatus() {
		return salesOrderStatus;
	}
	public void setSalesOrderStatus(String salesOrderStatus) {
		this.salesOrderStatus = salesOrderStatus;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
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
	public Integer getOrderRoomPrice() {
		return orderRoomPrice;
	}
	public void setOrderRoomPrice(Integer orderRoomPrice) {
		this.orderRoomPrice = orderRoomPrice;
	}
	public Integer getJourneyItemPrice() {
		return journeyItemPrice;
	}
	public void setJourneyItemPrice(Integer journeyItemPrice) {
		this.journeyItemPrice = journeyItemPrice;
	}
	
	



}
