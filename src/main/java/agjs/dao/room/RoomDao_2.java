package agjs.dao.room;

import java.util.Date;
import java.util.List;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.room.RoomUsedRecordPo;

public interface RoomDao_2 {

	Integer selectFromDateAndRoomStyle(Date startDate, Date endDate, Integer id, String roomName);

	Integer selectByJourneyName(String name);

	Integer selectByDateAndName(Date startDate, Integer id, String name);

	boolean deleteByHeaderId(Integer id) throws Exception;

	boolean insertByHeaderId(List<RoomUsedRecordPo> po) throws Exception;

	List<?> selectForRoomId(Date startDate, Date endDate, Integer id, String roomName);

	List<JourneyItemPo> selectbySohId(Integer id);

	boolean updateJourneyDate(JourneyItemPo po) throws Exception;

	List<?> selectForRoomStyleId(Date startDate, Date endDate, Integer roomStyleId, Integer count);

	Integer selectRoomStyleEmptyByDate(Date startDate, Date endDate, Integer count);
}