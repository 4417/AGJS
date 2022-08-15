package agjs.service.impl.restaurant;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.restaurant.RestaurantPo;
import agjs.dao.restaurant.RestaurantDao;
import agjs.service.restaurant.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantDao dao;

	@Override
	public List<RestaurantPo> getRestaurantAll() {
		return null;
	}

	@Override
	public void delete(Integer[] restIds) {
		// TODO Auto-generated method stub
	}

	@Override
	public Integer insertRest(RestaurantPo restaurantPo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantPo update(Integer restId, String restName, Blob restPic, String restFloor, Date restTime,
			String restIntro, String introTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(RestaurantPo restaurantPo, List<Integer> restaurantList) {
		// TODO Auto-generated method stub
		return null;
	};
}