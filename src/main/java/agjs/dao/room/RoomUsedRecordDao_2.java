package agjs.dao.room;

import java.util.Date;
import java.util.List;

import agjs.bean.room.RoomUsedRecordPo;

public interface RoomUsedRecordDao_2<T> {
	
	List<T> getAll();
	
	public boolean delete(Integer sohid);
	
	boolean insertByHeaderId(RoomUsedRecordPo po);


	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	
	List<Object[]> getNameAndStyleId();

	List<Object[]> selectEmptyRoomList(Date startDate, Date endDate, Integer id, String roomName);
}
