package agjs.bean.room;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_PHOTO")
public class RoomPhotoPo {

//	ROOM_PHOTO_ID, ROOM_STYLE_ID, ROOM_PHOTO
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_PHOTO_ID")
	private Integer roomPhotoId;
	@Column(name = "ROOM_STYLE_ID")
	private Integer roomStyleId;
	@Column(name = "ROOM_PHOTO")
	private byte[] roomPhoto;

	public RoomPhotoPo() {
	}

	@Override
	public String toString() {
		return "RoomPhotoPo [roomPhotoId=" + roomPhotoId + ", roomStyleId=" + roomStyleId + ", roomPhoto="
				+ Arrays.toString(roomPhoto) + "]";
	}

	public Integer getRoomPhotoId() {
		return roomPhotoId;
	}

	public void setRoomPhotoId(Integer roomPhotoId) {
		this.roomPhotoId = roomPhotoId;
	}

	public Integer getRoomStyleId() {
		return roomStyleId;
	}

	public void setRoomStyleId(Integer roomStyleId) {
		this.roomStyleId = roomStyleId;
	}

	public byte[] getRoomPhoto() {
		return roomPhoto;
	}

	public void setRoomPhoto(byte[] roomPhoto) {
		this.roomPhoto = roomPhoto;
	}

}
