package agjs.dao.impl.user;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
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
import agjs.ecpay.payment.integration.exception.EcpayException;

@Repository
public class UserDaoImpl_3 implements UserDao_3 {

	@PersistenceContext
	private Session session;

	// 用姓名 身分證 出生 判斷是否有此會員資料
	@Override
	public UserPo selectOrderUser(UserPo user) throws Exception {

		System.out.println("dao");
		System.out.println(user);
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
	public UserPo selectOrderUser2(UserPo user) throws Exception {

		String hql = "FROM UserPo WHERE userEmail = :mail AND userName = :name";
//		String hql = "from UserPo where USER_ID = :id";
		UserPo po = session.createQuery(hql, UserPo.class).setParameter("mail", user.getUserEmail())
				.setParameter("name", user.getUserName()).uniqueResult();

		System.out.println(po);
		return po;
	}

	// 會員註冊
	@Override
	public Serializable insert(UserPo beanPo) {
		if (beanPo != null && beanPo.getUserAccount() != null) {
			Serializable pk = session.save(beanPo);
			return pk;
		}
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
