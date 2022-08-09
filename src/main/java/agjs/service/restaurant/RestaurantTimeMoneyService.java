package agjs.service.restaurant;

import java.util.List;

import agjs.bean.restaurant.RestaurantTimeMoneyPo;

public interface RestaurantTimeMoneyService {

	public List<RestaurantTimeMoneyPo> allTMoney();
	List<RestaurantTimeMoneyPo> selectStartDate(RestaurantTimeMoneyPo restaurantTimeMoneyPo);
	List<RestaurantTimeMoneyPo> delete(RestaurantTimeMoneyPo restaurantTimeMoneyPo);
	RestaurantTimeMoneyPo insertMoney(RestaurantTimeMoneyPo restaurantTimeMoneyPo);
}
