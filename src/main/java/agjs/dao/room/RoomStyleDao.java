package agjs.dao.room;

import java.util.List;

import agjs.bean.room.RoomStylePo;

public interface RoomStyleDao<T> {

	List<T> getAll();

	T getId(Integer id);

	Integer add(T roomStylePo);
}
