package agjs.dao.restaurant;

import java.util.List;

import agjs.bean.restaurant.RestaurantPo;

public interface RestaurantDao {
	public List<RestaurantPo> selectRestaurant();
}
