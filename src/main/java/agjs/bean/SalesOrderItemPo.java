package agjs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SalesOrderItemPo {

	//SALES_ORDER_ITEM_ID int AI PK 
//	SALES_ORDER_HEADER_ID int 
//	ROOM_STYLE_ID int 
//	ORDER_ROOM_QUANTITY int 
//	ORDER_ROOM_PRICE int
	
	@Id
	@Column(name = "SALES_ORDER_ITEM_ID")
	private Integer salesOrderItemId;
	
	@Column(name = "SALES_ORDER_HEADER_ID")
	private Integer salesOrderHeaderId;
	
	@Column(name = "ROOM_STYLE_ID")
	private Integer roomStyleId;
	
	@Column(name = "ORDER_ROOM_QUANTITY")
	private Integer orderRoomQuantity;
	
	@Column(name = "ORDER_ROOM_PRICE")
	private Integer orderRoomPrice;

	@Override
	public String toString() {
		return "SalesOrderItemPo [salesOrderItemId=" + salesOrderItemId + ", salesOrderHeaderId=" + salesOrderHeaderId
				+ ", roomStyleId=" + roomStyleId + ", orderRoomQuantity=" + orderRoomQuantity + ", orderRoomPrice="
				+ orderRoomPrice + "]";
	}
	
	public Integer getSalesOrderItemId() {
		return salesOrderItemId;
	}

	public void setSalesOrderItemId(Integer salesOrderItemId) {
		this.salesOrderItemId = salesOrderItemId;
	}

	public Integer getSalesOrderHeaderId() {
		return salesOrderHeaderId;
	}

	public void setSalesOrderHeaderId(Integer salesOrderHeaderId) {
		this.salesOrderHeaderId = salesOrderHeaderId;
	}

	public Integer getRoomStyleId() {
		return roomStyleId;
	}

	public void setRoomStyleId(Integer roomStyleId) {
		this.roomStyleId = roomStyleId;
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

	
}
