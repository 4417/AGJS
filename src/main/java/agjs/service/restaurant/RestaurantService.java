package agjs.service.restaurant;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;


import agjs.bean.restaurant.RestaurantPo;

public interface RestaurantService {

	public List<RestaurantPo> getRestaurantAll();

	void delete(Integer[] restIds);

	Integer insertRest(RestaurantPo restaurantPo);

	RestaurantPo update(Integer restId, String restName, Blob restPic, String restFloor, Date restTime,
			String restIntro, String introTime);

	public Integer insert(RestaurantPo restaurantPo, List<Integer> restaurantList);
}