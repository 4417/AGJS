package agjs.dao.impl.user;


import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.user.UserPo;
import agjs.dao.user.UserDao_2;


@Repository
public class UserDaoImpl_2 implements UserDao_2 {
	@PersistenceContext
	private Session session;

	//查詢會員id獲得 UserPo
	//select * from USER where USER_ID = ?
	@Override
	public UserPo selectById(Integer id) {
		String hql = "from UserPo where USER_ID = :id";
		return session.createQuery(hql,UserPo.class)
		.setParameter("id", id)
		.uniqueResult();
	}
	
}
