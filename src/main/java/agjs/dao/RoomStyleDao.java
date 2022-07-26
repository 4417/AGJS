package agjs.dao;

import java.util.List;

import agjs.bean.RoomStylePo;

public interface RoomStyleDao<T> {

	List<T> getAll();

	T getId(Integer id);

	Integer add(RoomStylePo roomStylePo);
}
