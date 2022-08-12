package agjs.dao.room;

import java.util.List;

import agjs.bean.room.RoomPhotoPo;
import agjs.dao.CoreDao;

public interface RoomPhotoDao extends CoreDao<RoomPhotoPo, Integer> {

	List<RoomPhotoPo> selectByRoomStyleId(Integer roomStyleId);

}
