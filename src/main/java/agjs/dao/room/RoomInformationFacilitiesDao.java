package agjs.dao.room;

import java.util.List;

import agjs.bean.room.RoomInformationFacilitiesPo;

public interface RoomInformationFacilitiesDao {

	void add(RoomInformationFacilitiesPo roomInformationFacilitiesPo);
	void delete(RoomInformationFacilitiesPo roomInformationFacilitiesPo);
	List<RoomInformationFacilitiesPo> findByRoomStyleId(Integer roomStyleId);
//	void update(RoomInformationFacilitiesPo roomInformationFacilitiesPo);
}
