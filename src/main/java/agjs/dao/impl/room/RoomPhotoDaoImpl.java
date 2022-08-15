package agjs.dao.impl.room;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomPhotoPo;
import agjs.dao.room.RoomPhotoDao;

@Repository
public class RoomPhotoDaoImpl implements RoomPhotoDao {

	@PersistenceContext
	private Session session;

	@Override
	public Serializable insert(RoomPhotoPo beanPo) {
		return session.save(beanPo);
	}

	@Override
	public int deleteById(RoomPhotoPo beanPo) {
		System.out.println("deletePhoto:" + beanPo.getRoomPhotoId() + "-" + beanPo.getRoomStyleId());
		session.delete(beanPo);
		return beanPo.getRoomPhotoId();
	}

	@Override
	public int update(RoomPhotoPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RoomPhotoPo select(Integer id) {
		RoomPhotoPo photo = session.get(RoomPhotoPo.class, id);
		return photo;
	}

	@Override
	public List<RoomPhotoPo> select(Integer[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomPhotoPo> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomPhotoPo> selectByRoomStyleId(Integer roomStyleId) {

//		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//		CriteriaQuery<RoomPhotoPo> criteriaQuery = criteriaBuilder.createQuery(RoomPhotoPo.class);
//
//		Root<RoomPhotoPo> root = criteriaQuery.from(RoomPhotoPo.class);
//
//		Predicate p1 = criteriaBuilder.equal(root.get("roomStyleId"), roomStyleId);
//
//		criteriaQuery = criteriaQuery.where(p1);
//
//		TypedQuery<RoomPhotoPo> typedQuery = session.createQuery(criteriaQuery);
//		RoomPhotoPo result = typedQuery.getSingleResult();
		System.out.println(roomStyleId);

		String hql = "FROM RoomPhotoPo WHERE roomStyleId = :id";

		return session.createQuery(hql, RoomPhotoPo.class).setParameter("id", roomStyleId).list();

	}

}
