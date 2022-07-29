package agjs.bean.room;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_INFORMATION_FACILITIES")
public class RoomInformationFacilitiesPo {
	@EmbeddedId
	RoomInformationFacilitiesId id;

	public RoomInformationFacilitiesId getId() {
		return id;
	}

	public void setId(RoomInformationFacilitiesId id) {
		this.id = id;
	}

}
