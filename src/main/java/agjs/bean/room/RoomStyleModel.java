package agjs.bean.room;

import java.util.List;

public class RoomStyleModel {

	private Integer roomStyleId;
	private String roomName;
	private Integer roomQuantity;
	private String bedType;
	private String roomType;
	private Integer orderRoomPrice;
	private String roomDescription;
	private List<Integer> roomFacilitiesIdList;

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

	public Integer getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(Integer roomQuantity) {
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

	public Integer getOrderRoomPrice() {
		return orderRoomPrice;
	}

	public void setOrderRoomPrice(Integer orderRoomPrice) {
		this.orderRoomPrice = orderRoomPrice;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public List<Integer> getRoomFacilitiesIdList() {
		return roomFacilitiesIdList;
	}

	public void setRoomFacilitiesIdList(List<Integer> roomFacilitiesIdList) {
		this.roomFacilitiesIdList = roomFacilitiesIdList;
	}

}
