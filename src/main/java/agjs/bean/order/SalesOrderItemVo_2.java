package agjs.bean.order;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalesOrderItemVo_2 {
	
	private Integer userId;
	private Integer salesOrderHeaderId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderEndDate;
	private String roomName;
	private Integer orderRoomQuantity;  //每筆訂單明細的房間數量
	private Integer orderRoomPrice;  	//房間單價
	private String journeyName;
	private Integer adults;  			//每筆行程明細的大人數量
	private Integer children;  			//每筆行程明細的小孩數量
	private Integer journeyItemPrice;  	//每筆行程明細的總金額
	private String salesOrderStatus;
	private Integer roomPrice;  		//訂單房間總價
	private Integer journeyPrice;  		//訂單行程總價
	public SalesOrderItemVo_2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalesOrderItemVo_2(Integer userId, Integer salesOrderHeaderId, Date createDate, Date orderStartDate,
			Date orderEndDate, String roomName, Integer orderRoomQuantity, Integer orderRoomPrice, String journeyName,
			Integer adults, Integer children, Integer journeyItemPrice, String salesOrderStatus, Integer roomPrice,
			Integer journeyPrice) {
		super();
		this.userId = userId;
		this.salesOrderHeaderId = salesOrderHeaderId;
		this.createDate = createDate;
		this.orderStartDate = orderStartDate;
		this.orderEndDate = orderEndDate;
		this.roomName = roomName;
		this.orderRoomQuantity = orderRoomQuantity;
		this.orderRoomPrice = orderRoomPrice;
		this.journeyName = journeyName;
		this.adults = adults;
		this.children = children;
		this.journeyItemPrice = journeyItemPrice;
		this.salesOrderStatus = salesOrderStatus;
		this.roomPrice = roomPrice;
		this.journeyPrice = journeyPrice;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSalesOrderHeaderId() {
		return salesOrderHeaderId;
	}
	public void setSalesOrderHeaderId(Integer salesOrderHeaderId) {
		this.salesOrderHeaderId = salesOrderHeaderId;
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
	public String getJourneyName() {
		return journeyName;
	}
	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}
	public Integer getAdults() {
		return adults;
	}
	public void setAdults(Integer adults) {
		this.adults = adults;
	}
	public Integer getChildren() {
		return children;
	}
	public void setChildren(Integer children) {
		this.children = children;
	}
	public Integer getJourneyItemPrice() {
		return journeyItemPrice;
	}
	public void setJourneyItemPrice(Integer journeyItemPrice) {
		this.journeyItemPrice = journeyItemPrice;
	}
	public String getSalesOrderStatus() {
		return salesOrderStatus;
	}
	public void setSalesOrderStatus(String salesOrderStatus) {
		this.salesOrderStatus = salesOrderStatus;
	}
	public Integer getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Integer roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Integer getJourneyPrice() {
		return journeyPrice;
	}
	public void setJourneyPrice(Integer journeyPrice) {
		this.journeyPrice = journeyPrice;
	}
	@Override
	public String toString() {
		return "SalesOrderItemVo_2 [userId=" + userId + ", salesOrderHeaderId=" + salesOrderHeaderId + ", createDate="
				+ createDate + ", orderStartDate=" + orderStartDate + ", orderEndDate=" + orderEndDate + ", roomName="
				+ roomName + ", orderRoomQuantity=" + orderRoomQuantity + ", orderRoomPrice=" + orderRoomPrice
				+ ", journeyName=" + journeyName + ", adults=" + adults + ", children=" + children
				+ ", journeyItemPrice=" + journeyItemPrice + ", salesOrderStatus=" + salesOrderStatus + ", roomPrice="
				+ roomPrice + ", journeyPrice=" + journeyPrice + "]";
	}
	
	
	
	
}
