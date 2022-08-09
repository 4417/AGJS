package agjs.dao.room;

import java.util.Date;

public interface RoomDao_2 {

	Integer  selectFromDateAndRoomStyle(Date startDate, Date endDate, String roomName);
}