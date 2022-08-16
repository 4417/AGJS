package agjs.dao.impl.user;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.user.UserPo;
import agjs.dao.user.UserDao_3;

@Repository
public class UserDaoImpl_3 implements UserDao_3 {

	@PersistenceContext
	private Session session;

	// 用姓名 身分證 出生 判斷是否有此會員資料
	@Override
	public UserPo selectOrderUser(UserPo user) {

		System.out.println("dao");
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserPo> criteriaQuery = criteriaBuilder.createQuery(UserPo.class);

		Root<UserPo> root = criteriaQuery.from(UserPo.class);
		criteriaQuery.select(root);

		// 用身分證 生日 找會員
		Predicate p1 = criteriaBuilder.equal(root.get("userIdentityNumber"), user.getUserIdentityNumber());
		Predicate p2 = criteriaBuilder.equal(root.get("userBirthday"), user.getUserBirthday());

		// where ;
		criteriaQuery = criteriaQuery.where(p1, p2);

		TypedQuery<UserPo> typedQuery = session.createQuery(criteriaQuery);
		return typedQuery.getSingleResult();

	}

	@Override
	public List<?> selectOrderUser2(UserPo user) {

		String sql = "SELECT * FROM USER WHERE USER_BIRTHDAY = ?1 AND USER_EMAIL = ?2 ;";

		return session.createSQLQuery(sql).setParameter(1, user.getUserBirthday())
				.setParameter(2, user.getEmailVerifyStatus()).list();
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
