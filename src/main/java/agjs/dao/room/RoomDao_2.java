package agjs.dao.room;

import java.util.Date;

import agjs.bean.journey.JourneyPo;

public interface RoomDao_2 {

	Integer  selectFromDateAndRoomStyle(Date startDate, Date endDate, String roomName);

	Integer selectByJourneyName(String name);

	Integer selectByDateAndName(Date startDate, String name);

}