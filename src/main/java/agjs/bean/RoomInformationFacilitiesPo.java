package agjs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_INFORMATION_FACILITIES")
public class RoomInformationFacilitiesPo {

//	ROOM_STYLE_ID, ROOM_FACILITIES_ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROOM_STYLE_ID")
	private Integer roomStyleId;

	@Column(name = "ROOM_FACILITIES_ID")
	public Integer getRoomStyleId() {
		return roomStyleId;
	}

	public void setRoomStyleId(Integer roomStyleId) {
		this.roomStyleId = roomStyleId;
	}

	public Integer getRoomFacilities() {
		return roomFacilities;
	}

	public void setRoomFacilities(Integer roomFacilities) {
		this.roomFacilities = roomFacilities;
	}

	@Column(name = "ROOM_FACILITIES_ID")
	private Integer roomFacilities;

}
