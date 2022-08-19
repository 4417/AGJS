package agjs.bean.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "ROOM_STYLE")
public class RoomStylePo {
//	ROOM_STYLE_ID, ROOM_NAME, ROOM_QUANTITY, BED_TYPE, ROOM_TYPE, ORDER_ROOM_PRICE, ROOM_DESCRIPTION
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_STYLE_ID")
	private Integer roomStyleId;
	@Column(name = "ROOM_NAME")
	private String roomName;
	@Column(name = "ROOM_QUANTITY")
	private Integer roomQuantity;
	@Column(name = "BED_TYPE")
	private String bedType;
	@Column(name = "ROOM_TYPE")
	private String roomType;
	@Column(name = "ORDER_ROOM_PRICE")
	private Integer orderRoomPrice;
	@Column(name = "ROOM_DESCRIPTION")
	private String roomDescription;
	
	
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
}
	