package agjs.bean.room;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomInformationFacilitiesId implements Serializable {
	@Column(name = "ROOM_STYLE_ID")
	private Integer roomStyleId;
	@Column(name = "ROOM_FACILITIES_ID")
	private Integer roomFacilitiesId;
	
	public Integer getRoomStyleId() {
		return roomStyleId;
	}

	public void setRoomStyleId(Integer roomStyleId) {
		this.roomStyleId = roomStyleId;
	}

	public Integer getRoomFacilitiesId() {
		return roomFacilitiesId;
	}

	public void setRoomFacilitiesId(Integer roomFacilitiesId) {
		this.roomFacilitiesId = roomFacilitiesId;
	}

}