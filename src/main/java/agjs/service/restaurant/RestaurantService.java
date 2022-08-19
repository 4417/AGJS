package agjs.service.restaurant;

import java.sql.Blob;
import java.sql.Date;

import agjs.bean.restaurant.RestaurantVo;

import agjs.dao.restaurant.RestaurantDaoNew;
import agjs.dao.restaurant.RestaurantDao_interface;

public class RestaurantService {
	private RestaurantDao_interface dao;

	public RestaurantService() {
		dao = new RestaurantDaoNew();
	}

//	public RestaurantVo addR(String REST_NAME, Blob REST_PIC, String REST_FLOOR, String REST_TIME, String REST_INTRO,
//			Date INTRO_TIME) {
//
//		RestaurantVo restaurantVo = new RestaurantVo();
//
//		restaurantVo.setREST_NAME(REST_NAME);
//		restaurantVo.setREST_PIC(REST_PIC);
//		restaurantVo.setREST_FLOOR(REST_FLOOR);
//		restaurantVo.setREST_TIME(REST_TIME);
//		restaurantVo.setREST_INTRO(REST_INTRO);
//		restaurantVo.setINTRO_TIME(INTRO_TIME);
//		dao.insert(restaurantVo);
//
//		return restaurantVo;
//	}
//
//	public void deleteAD(Integer REST_ID) {
//		dao.delete(REST_ID);
//		
//	}

	public RestaurantVo update(Integer REST_ID, String REST_NAME, Blob REST_PIC, String REST_FLOOR, String REST_TIME, String REST_INTRO,
			Date INTRO_TIME) {
	
			RestaurantVo restaurantVo = new RestaurantVo();
	
			restaurantVo.setREST_ID(REST_ID);
			restaurantVo.setREST_NAME(REST_NAME);
			restaurantVo.setREST_PIC(REST_PIC);
			restaurantVo.setREST_FLOOR(REST_FLOOR);
			restaurantVo.setREST_TIME(REST_TIME);
			restaurantVo.setREST_INTRO(REST_INTRO);
			restaurantVo.setINTRO_TIME(INTRO_TIME);
			dao.update(restaurantVo);
	
			return restaurantVo;
		}
	}