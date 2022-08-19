package agjs.service.restaurant;

import agjs.bean.restaurant.RestaurantTimeMoneyVo;
import agjs.dao.restaurant.RestaurantTimeMoneyDaoNew;
import agjs.dao.restaurant.RestaurantTimeMoneyDao_interface;





public class RestaurantTimeMoneyServiceNew {
	private RestaurantTimeMoneyDao_interface dao;
	
	public RestaurantTimeMoneyServiceNew() {
		dao = new RestaurantTimeMoneyDaoNew();
	}
	
	public RestaurantTimeMoneyVo addTM(Integer REST_ID, String MONEY, String MONEY_CONTENT) {

		RestaurantTimeMoneyVo restaurantTimeMoneyVo = new RestaurantTimeMoneyVo();

		restaurantTimeMoneyVo.setREST_ID(REST_ID);
		restaurantTimeMoneyVo.setMONEY(MONEY);
		restaurantTimeMoneyVo.setMONEY_CONTENT(MONEY_CONTENT);
		dao.insert(restaurantTimeMoneyVo);

		return restaurantTimeMoneyVo;
	}
	
	public void delete(Integer Money_ID) {
		dao.delete(Money_ID);
	}
	
	
	
	
}