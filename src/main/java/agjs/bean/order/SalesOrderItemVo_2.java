package agjs.bean.order;

import java.util.Date;

public class SalesOrderItemVo_2 {
	
	private Integer userId;
	private Integer salesOrderHeaderId;
	private Date createDate;
	private Date orderStartDate;
	private Date orderEndDate;
	private String roomName;
	private Integer orderRoomQuantity;
	private Integer orderRoomPrice;
	private Integer orderRoomTotalPrice;
	private String journeyName;
	private Integer adults;
	private Integer children;
	private Integer journeyTotalPrice;
	private String salesOrderStatus;
	private Integer roomPrice;
	private Integer journeyPrice;
	
	public SalesOrderItemVo_2() {
		super();
	}
	
	
	public SalesOrderItemVo_2(Integer userId, Integer salesOrderHeaderId, Date createDate, Date orderStartDate,
			Date orderEndDate, String roomName, Integer orderRoomQuantity, Integer orderRoomPrice,
			Integer orderRoomTotalPrice, String journeyName, Integer adults, Integer children,
			Integer journeyTotalPrice, String salesOrderStatus, Integer roomPrice, Integer journeyPrice) {
		super();
		this.userId = userId;
		this.salesOrderHeaderId = salesOrderHeaderId;
		this.createDate = createDate;
		this.orderStartDate = orderStartDate;
		this.orderEndDate = orderEndDate;
		this.roomName = roomName;
		this.orderRoomQuantity = orderRoomQuantity;
		this.orderRoomPrice = orderRoomPrice;
		this.orderRoomTotalPrice = orderRoomTotalPrice;
		this.journeyName = journeyName;
		this.adults = adults;
		this.children = children;
		this.journeyTotalPrice = journeyTotalPrice;
		this.salesOrderStatus = salesOrderStatus;
		this.roomPrice = roomPrice;
		this.journeyPrice = journeyPrice;
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

	public Integer getOrderRoomTotalPrice() {
		return orderRoomTotalPrice;
	}

	public void setOrderRoomTotalPrice(Integer orderRoomTotalPrice) {
		this.orderRoomTotalPrice = orderRoomTotalPrice;
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

	public Integer getJourneyTotalPrice() {
		return journeyTotalPrice;
	}

	public void setJourneyTotalPrice(Integer journeyTotalPrice) {
		this.journeyTotalPrice = journeyTotalPrice;
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


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "SalesOrderItemVo_2 [userId=" + userId + ", salesOrderHeaderId=" + salesOrderHeaderId + ", createDate="
				+ createDate + ", orderStartDate=" + orderStartDate + ", orderEndDate=" + orderEndDate + ", roomName="
				+ roomName + ", orderRoomQuantity=" + orderRoomQuantity + ", orderRoomPrice=" + orderRoomPrice
				+ ", orderRoomTotalPrice=" + orderRoomTotalPrice + ", journeyName=" + journeyName + ", adults=" + adults
				+ ", children=" + children + ", journeyTotalPrice=" + journeyTotalPrice + ", salesOrderStatus="
				+ salesOrderStatus + ", roomPrice=" + roomPrice + ", journeyPrice=" + journeyPrice + "]";
	}



	

	
}
