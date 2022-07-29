package agjs.bean.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class RoomPo {

//	ROOM_ID, ROOM_STYLE_ID
	@Id
	@Column(name = "ROOM_ID")
	private Integer roomId;
	@Column(name = "ROOM_STYLE_ID")
	private Integer roomStyleId;
	
	

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getRoomStyleId() {
		return roomStyleId;
	}

	public void setRoomStyleId(Integer roomStyleId) {
		this.roomStyleId = roomStyleId;
	}

}
