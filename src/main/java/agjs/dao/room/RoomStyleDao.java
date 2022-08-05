package agjs.dao.room;

import java.util.List;

import agjs.bean.room.RoomStylePo;

public interface RoomStyleDao<T> {

	List<T> getAll();

	T getId(Integer id);

	Integer add(T roomStylePo);
	
	void delete(Integer roomStyleId);

	RoomStylePo update(Integer id,String roomName, String bedType, Integer orderRoomPrice, String roomDescription,
			Integer roomQuantity, String roomType);
	
}
