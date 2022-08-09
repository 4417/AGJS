package agjs.service.impl.restaurant;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.restaurant.RestaurantTimeMoneyPo;
import agjs.dao.restaurant.RestaurantBookDao;
import agjs.service.restaurant.RestaurantTimeMoneyService;

@Service
public class RestaurantTimeMoneyServiceImpl implements RestaurantTimeMoneyService{

	@Autowired
	private RestaurantBookDao restaurantBookDao;
	
	
	
	@Override
	public List<RestaurantTimeMoneyPo> selectStartDate(RestaurantTimeMoneyPo restaurantTimeMoneyPo){
		return null;
	}
	
	
	@Override
	public List<RestaurantTimeMoneyPo> allTMoney(){
		return null;
	}
	
	@Override
	public RestaurantTimeMoneyPo insertMoney(RestaurantTimeMoneyPo restaurantTimeMoneyPo){
		return null;
		}

	public List<RestaurantTimeMoneyPo> delete(RestaurantTimeMoneyPo restaurantTimeMoneyPo){
		return null;
	}
	
}
