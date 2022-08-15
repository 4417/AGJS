package agjs.dao.impl.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.user.UserPo;
import agjs.dao.user.UserDao_2;
import agjs.dao.user.UserDao_3;

@Repository
public class UserDaoImpl_3 implements UserDao_3 {

	@PersistenceContext
	private Session session;

	// 用姓名 身分證 出生 判斷是否有此會員資料
	@Override
	public UserPo selectOrderUser(UserPo user) {

		try {
			System.out.println("dao");
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<UserPo> criteriaQuery = criteriaBuilder.createQuery(UserPo.class);

			Root<UserPo> root = criteriaQuery.from(UserPo.class);
			criteriaQuery.select(root);

			// USER_ACCOUNT= ?
			Predicate p1 = criteriaBuilder.equal(root.get("userName"), user.getUserName());
			Predicate p2 = criteriaBuilder.equal(root.get("userIdentityNumber"), user.getUserIdentityNumber());
			Predicate p3 = criteriaBuilder.equal(root.get("userBirthday"), user.getUserBirthday());

			// where ;
			criteriaQuery = criteriaQuery.where(p1, p2, p3);

			TypedQuery<UserPo> typedQuery = session.createQuery(criteriaQuery);
//			UserPo result = typedQuery.getSingleResult();
//			System.out.println(result);
			return typedQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Serializable insert(UserPo beanPo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(UserPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(UserPo beanPo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserPo select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPo> select(Integer[] idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPo> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
