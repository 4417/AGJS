package agjs.dao.impl.room;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyPo;
import agjs.bean.room.RoomPhotoPo;
import agjs.dao.room.RoomPhotoDao;

@Repository
public class RoomPhotoDaoImpl implements RoomPhotoDao {

	@PersistenceContext
	private Session session;

	@Override
	public int insert(RoomPhotoPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(RoomPhotoPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(RoomPhotoPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RoomPhotoPo select(Integer id) {
		// TODO Auto-generated method stub
		return null;
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
