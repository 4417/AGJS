package agjs.bean.room;

/**
 * 房型卡 資料
 */
public class RoomCardVo {

	private String roomStyleId;
	private String roomName;
	private String roomQuantity;
	private String bedType;
	private String roomType;
	private String orderRoomPrice;
	private String roomDescription;
	private String roomPhoto;

	public RoomCardVo() {
	}

	@Override
	public String toString() {
		return "RoomCardVo [roomStyleId=" + roomStyleId + ", roomName=" + roomName + ", roomQuantity=" + roomQuantity
				+ ", bedType=" + bedType + ", roomType=" + roomType + ", orderRoomPrice=" + orderRoomPrice
				+ ", roomDescription=" + roomDescription + ", roomPhoto=" + roomPhoto + "]";
	}

	public String getRoomStyleId() {
		return roomStyleId;
	}

	public void setRoomStyleId(String roomStyleId) {
		this.roomStyleId = roomStyleId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(String roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getOrderRoomPrice() {
		return orderRoomPrice;
	}

	public void setOrderRoomPrice(String orderRoomPrice) {
		this.orderRoomPrice = orderRoomPrice;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getRoomPhoto() {
		return roomPhoto;
	}

	public void setRoomPhoto(String roomPhoto) {
		this.roomPhoto = roomPhoto;
	}

}
