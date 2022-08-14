package agjs.bean.order;


//後台查詢用的訂單明細Vo
public class SalesOrderItemVo {

	private Integer salesOrderItemId;
	private Integer salesOrderHeaderId;
	private Integer roomStyleId;
	private String roomName;
	private Integer orderRoomQuantity;
	private Integer orderRoomPrice;
	
	public SalesOrderItemVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SalesOrderItemVo(Integer salesOrderItemId, Integer salesOrderHeaderId, Integer roomStyleId, String roomName,
			Integer orderRoomQuantity, Integer orderRoomPrice) {
		super();
		this.salesOrderItemId = salesOrderItemId;
		this.salesOrderHeaderId = salesOrderHeaderId;
		this.roomStyleId = roomStyleId;
		this.roomName = roomName;
		this.orderRoomQuantity = orderRoomQuantity;
		this.orderRoomPrice = orderRoomPrice;
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

	
	
}
