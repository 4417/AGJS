package agjs.bean.room;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_INFORMATION_FACILITIES")
public class RoomInformationFacilitiesPo {
	@EmbeddedId
	RoomInformationFacilitiesId id;

//	ROOM_STYLE_ID, ROOM_FACILITIES_ID
//	@Id
//	@Column(name = "ROOM_STYLE_ID")
//	private Integer roomStyleId;
//	@Id
//	@Column(name = "ROOM_FACILITIES_ID")
//	private Integer roomFacilitiesId;
//
//	public Integer getRoomStyleId() {
//		return roomStyleId;
//	}
//
//	public void setRoomStyleId(Integer roomStyleId) {
//		this.roomStyleId = roomStyleId;
//	}
//
//	public Integer getRoomFacilitiesId() {
//		return roomFacilitiesId;
//	}
//
//	public void setRoomFacilitiesId(Integer roomFacilitiesId) {
//		this.roomFacilitiesId = roomFacilitiesId;
//	}
	

	public RoomInformationFacilitiesId getId() {
		return id;
	}

	public void setId(RoomInformationFacilitiesId id) {
		this.id = id;
	}

}
