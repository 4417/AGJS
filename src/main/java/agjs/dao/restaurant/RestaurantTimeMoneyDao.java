package agjs.dao.restaurant;

import java.util.List;

import agjs.bean.restaurant.RestaurantBookPo;

public interface RestaurantTimeMoneyDao {
	List<RestaurantBookPo> allRBook();
	List<RestaurantBookPo> selectBookDate(RestaurantBookPo restaurantBookPo);
	List<RestaurantBookPo> selectType(RestaurantBookPo restaurantBookPo);
	RestaurantBookPo insertBook(RestaurantBookPo restaurantBookPo);
	RestaurantBookPo updateBook(Integer restBookId);	
	List<RestaurantBookPo> getBookInfo(RestaurantBookPo restaurantBookPo);
}
