package agjs.dao.impl.user;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.user.AdministratorPo;
import agjs.dao.user.AdministratorDao;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
	@PersistenceContext
	private Session session;
	
	//select * from ADMINISTRATOR where ADMINISTRATOR_ACCOUNT = ? and ADMINISTRATOR_PASSWORD = ?;
	@Override
	public AdministratorPo selectLogin(AdministratorPo administrator) {
		
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<AdministratorPo> query = builder.createQuery(AdministratorPo.class);
			
			//from ADMINISTRATOR
			Root<AdministratorPo> root= query.from(AdministratorPo.class);
			
			//ADMINISTRATOR_ACCOUNT = ?
			Predicate p1= builder.equal(root.get("administratorAccount"), administrator.getAdministratorAccount());
			
			//ADMINISTRATOR_PASSWORD = ?
			Predicate p2= builder.equal(root.get("administratorPassword"), administrator.getAdministratorPassword());
			
			//where ADMINISTRATOR_ACCOUNT = ? and ADMINISTRATOR_PASSWORD = ?
			query=query.where(p1,p2);
			
			TypedQuery<AdministratorPo> typedQuery=session.createQuery(query);
			AdministratorPo result=typedQuery.getSingleResult();
			
			return result;
			
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
