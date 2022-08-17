package agjs.service.restaurant;

import java.sql.Date;
import java.util.List;

import agjs.bean.restaurant.RestaurantBookVO;
import agjs.dao.restaurant.RestaurantBookDaoNew;
import agjs.dao.restaurant.RestaurantBookDao_interface;

public class RestaurantBookServiceNew {
	private RestaurantBookDao_interface dao;
	
	public RestaurantBookServiceNew() {
		dao = new RestaurantBookDaoNew();
	}
	// 新增
		public RestaurantBookVO add(Integer SALES_ORDER_HEADER_ID, Integer REST_ID, 
				Date REST_DATE, String USER_NAME, Integer REST_NUM, String REST_TEL,String REST_NOTE) {
			
			RestaurantBookVO restaurantBookVO = new RestaurantBookVO();
			
			restaurantBookVO.setSALES_ORDER_HEADER_ID(SALES_ORDER_HEADER_ID);
			restaurantBookVO.setREST_ID(REST_ID);
			restaurantBookVO.setREST_DATE(REST_DATE);
			restaurantBookVO.setUSER_NAME(USER_NAME);
			restaurantBookVO.setREST_NUM(REST_NUM);
			restaurantBookVO.setREST_TEL(REST_TEL);
			restaurantBookVO.setREST_NOTE(REST_NOTE);
			dao.insert(restaurantBookVO);
			
			return restaurantBookVO;
		}

}
