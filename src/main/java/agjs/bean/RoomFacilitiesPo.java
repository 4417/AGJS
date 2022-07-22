package agjs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_FACILITIES")
public class RoomFacilitiesPo {

//	ROOM_FACILITIES_ID, ROOM_FACILITIES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_FACILITIES_ID")
	private Integer roomFacilitiesId;
	@Column(name = "ROOM_FACILITIES")
	private String roomFacilities;

	public Integer getRoomFacilitiesId() {
		return roomFacilitiesId;
	}

	public void setRoomFacilitiesId(Integer roomFacilitiesId) {
		this.roomFacilitiesId = roomFacilitiesId;
	}

	public String getRoomFacilities() {
		return roomFacilities;
	}

	public void setRoomFacilities(String roomFacilities) {
		this.roomFacilities = roomFacilities;
	}

}
