package agjs.dao.room;

import java.util.List;

import agjs.dao.CoreDao;

public interface RoomStatusDao extends CoreDao {

	List<Integer> selectEmptyRoomByDateRange(java.util.Date startDate, java.util.Date endDate);

}
