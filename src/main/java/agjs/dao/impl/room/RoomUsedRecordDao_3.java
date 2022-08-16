package agjs.dao.impl.room;

import java.util.Date;
import java.util.List;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomUsedRecordVo;

public interface RoomUsedRecordDao_3<T> {

	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	
	boolean delete(Integer sohid);

	boolean insert(RoomUsedRecordPo po);

	List<Integer> selectEmptyRoomList(Date startDate, Date endDate, Integer id, String roomName);

	List<RoomUsedRecordVo> getAll();

	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	String getNamebyStyleId(Integer styleId);

	Integer getRoomIdbyStyleId(Integer roomId);



}
