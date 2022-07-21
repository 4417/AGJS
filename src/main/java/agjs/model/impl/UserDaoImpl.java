package agjs.model.impl;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.UserPo;
import agjs.dao.UserDao;
@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private Session session;

	@Override
	public UserPo selectLogin(UserPo user) {
		String hql = "from UserPo where userAccount = :userAccount and userPassword = :userPassword";
		return session.createQuery(hql,UserPo.class)
		.setParameter("userAccount", user.getUserAccount())
		.setParameter("userPassword", user.getUserPassword())
		.uniqueResult();
	}

}
