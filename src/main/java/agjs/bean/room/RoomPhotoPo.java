package agjs.bean.room;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROOM_PHOTO_ID")
	private Integer RoomPhotoId;
	@Column(name = "ROOM_STYLE_ID")
	private Integer RoomStyleId;
	@Column(name = "ROOM_PHOTO")
	private java.sql.Blob RoomPhoto;

	public Integer getRoomPhotoId() {
		return RoomPhotoId;
	}

	public void setRoomPhotoId(Integer roomPhotoId) {
		RoomPhotoId = roomPhotoId;
	}

	public Integer getRoomStyleId() {
		return RoomStyleId;
	}

	public void setRoomStyleId(Integer roomStyleId) {
		RoomStyleId = roomStyleId;
	}

	public java.sql.Blob getRoomPhoto() {
		return RoomPhoto;
	}

	public void setRoomPhoto(java.sql.Blob roomPhoto) {
		RoomPhoto = roomPhoto;
	}

}
