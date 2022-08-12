package agjs.dao.restaurant;

import java.util.List;

import agjs.bean.restaurant.RestaurantTimeMoneyPo;

public interface RestaurantTimeMoneyDao {
	List<RestaurantTimeMoneyPo> allTMoney();
	List<RestaurantTimeMoneyPo> selectType(RestaurantTimeMoneyPo restaurantTimeMoneyPo);
	RestaurantTimeMoneyPo insertMoney(RestaurantTimeMoneyPo restaurantTimeMoneyPo);
	RestaurantTimeMoneyPo updateMoney(Integer moneyId);	
	List<RestaurantTimeMoneyPo> getTimeInfo(RestaurantTimeMoneyPo restaurantTimeMoneyPo);
}
