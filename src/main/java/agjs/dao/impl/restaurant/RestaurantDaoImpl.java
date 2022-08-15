package agjs.dao.impl.restaurant;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.restaurant.RestaurantPo;
import agjs.dao.restaurant.RestaurantDao;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

	@PersistenceContext
	private Session session;

	public List<RestaurantPo> getRestaurantAll() {
		List<RestaurantPo> list = new ArrayList<RestaurantPo>();
		try {
			Query<RestaurantPo> query = session.createQuery("FROM RestaurantPo", RestaurantPo.class);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	};

	@Override
	public void delete(Integer restId) {
		RestaurantPo po = session.get(RestaurantPo.class, restId);
		session.delete(po);
	}

	@Override
	public Integer insertRest(RestaurantPo restaurantPo) {
		session.save(restaurantPo);
		return restaurantPo.getRestId();
	}

	@Override
	public RestaurantPo update(Integer restId, String restName, Blob restPic, String restFloor, String restTime,
			String restIntro, String introTime) {
		// 如果id不是空值(id確認是存在)
		if (restId != null) {
			// 找尋在RoomStylePo裡面的此id
			RestaurantPo temp = session.get(RestaurantPo.class, restId);
			// 有找到此id的話，就將資料塞進去
			if (temp != null) {
				temp.setRestName(restName);
				temp.setRestPic(restPic);
				temp.setRestFloor(restFloor);
				temp.setRestTime(restTime);
				temp.setRestIntro(restIntro);
				temp.setIntroTime(introTime);
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<RestaurantPo> getId(Integer id) {
		RestaurantPo getId = session.get(RestaurantPo.class, id);
		return (List<RestaurantPo>) getId;
	}
}
