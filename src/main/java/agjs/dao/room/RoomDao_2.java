package agjs.dao.room;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyPo;
import agjs.bean.room.RoomUsedRecordPo;

public interface RoomDao_2 {

	Integer selectFromDateAndRoomStyle(Date startDate, Date endDate, Integer id, String roomName);

	Integer selectByJourneyName(String name);

	Integer selectByDateAndName(Date startDate, Integer id, String name);

	boolean deleteByHeaderId(Integer id);

	boolean insertByHeaderId(List<RoomUsedRecordPo> po);

	List<?> selectForRoomId(Date startDate, Date endDate, Integer id, String roomName);

	List<JourneyItemPo> selectbySohId(Integer id);

	boolean updateJourneyDate(JourneyItemPo po);

	List<?> selectForRoomStyleId(Date startDate, Date endDate, Integer sohId, Integer roomStyleId, Integer count);

}