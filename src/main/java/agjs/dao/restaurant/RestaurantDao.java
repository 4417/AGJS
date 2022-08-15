package agjs.dao.restaurant;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import agjs.bean.restaurant.RestaurantPo;

public interface RestaurantDao {
	public List<RestaurantPo> getRestaurantAll();

	void delete(Integer restId);
	List<RestaurantPo> getId(Integer id);
	Integer insertRest(RestaurantPo restaurantPo);

	RestaurantPo update(Integer restId, String restName, Blob restPic, String restFloor, String restTime,
			String restIntro, String introTime);
}
