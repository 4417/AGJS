package agjs.service.restaurant;

import java.util.List;


import agjs.bean.restaurant.RestaurantPo;

public interface RestaurantService {

	public List<RestaurantPo> getRestaurantAll();
	List<RestaurantPo> selectStartDate(RestaurantPo restaurantPo);
	List<RestaurantPo> delete(RestaurantPo restaurantPo);
	RestaurantPo insertRest(RestaurantPo restaurantPo);
}
