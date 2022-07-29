package agjs.dao.impl.user;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.user.UserPo;
import agjs.dao.user.UserDao;


@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private Session session;

	//會員登入
	//select * from USER where USER_ACCOUNT = ? and USER_PASSWORD = ?
	@Override
	public UserPo selectLogin(UserPo user) {
		String hql = "from UserPo where userAccount = :userAccount and userPassword = :userPassword";
		return session.createQuery(hql,UserPo.class)
		.setParameter("userAccount", user.getUserAccount())
		.setParameter("userPassword", user.getUserPassword())
		.uniqueResult();
	}
	
	//會員帳號查詢
	//select * from USER where USER_ACCOUNT= ? ;
	@Override
	public UserPo selectByAccount(String account) {
		
		try {
			//根據設定的帳號密碼查詢有無符合的資料，若已有這資料則不給註冊
			CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
			CriteriaQuery<UserPo> criteriaQuery=criteriaBuilder.createQuery(UserPo.class);
			
			//from USER
			Root<UserPo> root= criteriaQuery.from(UserPo.class);
			
			//USER_ACCOUNT= ?
			Predicate p1= criteriaBuilder.equal(root.get("userAccount"), account);
			
			//where USER_ACCOUNT= "JRIEWOJ492";
			criteriaQuery=criteriaQuery.where(p1);
			
			TypedQuery<UserPo> typedQuery=session.createQuery(criteriaQuery);
			UserPo result=typedQuery.getSingleResult();
			
			System.out.println("p1:"+p1);
			System.out.println("result:"+result);
			return result;
			
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//會員註冊
	//insert into USER() values()
	@Override
	public UserPo insert(UserPo user) {
		//確認使用者註冊時有確實填上資料
		if(user!=null && user.getUserAccount()!=null) {
			Serializable pk=session.save(user);
			return user;
		}
		return null;
	}
	
	//會員資料修改
	@Override
	public UserPo update(UserPo user) {
		//確認使用者修改時有確實填上資料
		if(user!=null) {
			return (UserPo) session.merge(user);
		}
		return null;
	}
}
