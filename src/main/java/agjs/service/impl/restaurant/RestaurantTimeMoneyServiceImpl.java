package agjs.service.impl.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.restaurant.RestaurantPo;
import agjs.dao.restaurant.RestaurantDao;
import agjs.service.restaurant.RestaurantService;

@Service
public class RestaurantTimeMoneyServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantDao dao;

	@Override
	public List<RestaurantPo> getRestaurantAll() {
		return null;
	}
	@Override
	public List<RestaurantPo> selectStartDate(RestaurantPo restaurantPo){
		return null;
	};
	@Override
	public List<RestaurantPo> delete(RestaurantPo restaurantPo){
		return null;
	};
	@Override
	public RestaurantPo insertRest(RestaurantPo restaurantPo) {return null;};
}