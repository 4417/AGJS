package agjs.dao.room;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import agjs.bean.journey.JourneyPo;
import agjs.bean.room.RoomUsedRecordPo;

public interface RoomDao_2 {

	Integer  selectFromDateAndRoomStyle(Date startDate, Date endDate, String name, String roomName);

	Integer selectByJourneyName(String name);

	Integer selectByDateAndName(Date startDate, String name);

	boolean deleteByHeaderId(Integer id);

}