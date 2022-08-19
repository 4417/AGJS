package agjs.dao.impl.user;

import java.io.Serializable;
import java.util.Base64;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
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
	
	//會員帳號查詢，根據設定的帳號密碼查詢有無符合的資料，若已有這資料則不給註冊
	//select * from USER where USER_ACCOUNT= ? ;
	@Override
	public UserPo selectByAccount(String account) {
		
		try {
			CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
			CriteriaQuery<UserPo> criteriaQuery=criteriaBuilder.createQuery(UserPo.class);
			
			//from USER
			Root<UserPo> root= criteriaQuery.from(UserPo.class);
			
			//USER_ACCOUNT= ?
			Predicate p1= criteriaBuilder.equal(root.get("userAccount"), account);
			
			//where USER_ACCOUNT= "JRIEWOJ492";
			criteriaQuery=criteriaQuery.where(p1);
			
			TypedQuery<UserPo> typedQuery=session.createQuery(criteriaQuery);
			UserPo result=((Query<UserPo>) typedQuery).uniqueResult();
			
			return result;
			
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//會員登入
	//select * from USER where USER_EMAIL = ?
	@Override
	public UserPo selectByMail(String email) {
		String hql = "from UserPo where userEmail = :userEmail";
		return session.createQuery(hql,UserPo.class)
		.setParameter("userEmail", email)
		.uniqueResult();
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
		System.out.println("DAO的user:"+user);
		if(user!=null) {
			System.out.println("DAO更新後的user:"+(UserPo) session.merge(user));
			return user=(UserPo) session.merge(user);
		}
		return null;
	}
}
