package agjs.dao.impl.restaurant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.restaurant.RestaurantPo;
import agjs.dao.restaurant.RestaurantDao;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {
	
	@PersistenceContext
	private Session session;

	@Override
	public List<RestaurantPo> selectRestaurant() {

		List<RestaurantPo> restaurantPoList = new ArrayList<RestaurantPo>();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<RestaurantPo> criteriaQuery = criteriaBuilder.createQuery(RestaurantPo.class);

			Root<RestaurantPo> root = criteriaQuery.from(RestaurantPo.class);
			criteriaQuery.select(root);

			Query<RestaurantPo> query = session.createQuery(criteriaQuery);
			restaurantPoList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restaurantPoList;

	}
}
