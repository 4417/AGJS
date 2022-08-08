package agjs.dao.impl.room;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomInformationFacilitiesPo;
import agjs.dao.room.RoomInformationFacilitiesDao;

@Repository
public class RoomInformationFacilitiesDaoImpl implements RoomInformationFacilitiesDao {

	@PersistenceContext
	private Session session;

	@Override
	public void add(RoomInformationFacilitiesPo po) {
		session.save(po);
	}

	// 刪除
	@Override
	public void delete(RoomInformationFacilitiesPo po) {
		System.out.println("delete:" + po.getId().getRoomStyleId() + " - " + po.getId().getRoomFacilitiesId());
		session.delete(po);

	}

	// 尋找要刪除的id
	@Override
	public List<RoomInformationFacilitiesPo> findByRoomStyleId(Integer roomStyleId) {
		System.out.println("findByRoomStyleId:" + roomStyleId);
		Query<RoomInformationFacilitiesPo> query = session.createQuery(
				"FROM RoomInformationFacilitiesPo WHERE id.roomStyleId =: roomStyleId",
				RoomInformationFacilitiesPo.class);
		query.setParameter("roomStyleId", roomStyleId);
		List<RoomInformationFacilitiesPo> poList = query.list();

		if (poList == null || poList.size() < 1) {
			System.out.println("findByRoomStyleId:" + roomStyleId + " no RoomInformationFacilitiesPo ");
		}
		for (RoomInformationFacilitiesPo po : poList) {
			System.out.println(
					"findByRoomStyleId:" + po.getId().getRoomStyleId() + " - " + po.getId().getRoomFacilitiesId());
		}
		return poList;
	}

//	@Override
//	public void update(RoomInformationFacilitiesPo po) {
//		System.out.println("update:" + po.getId().getRoomStyleId() + " - " + po.getId().getRoomFacilitiesId());
//		if (po != null) {
//
//		}
//
//	}

}
