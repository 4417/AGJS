package agjs.service.restaurant;

import java.util.List;

import org.springframework.stereotype.Service;

import agjs.bean.restaurant.RestaurantBookPo;
public interface RestaurantBookService {
	List<RestaurantBookPo> allRBook();
	List<RestaurantBookPo> selectBookDate(RestaurantBookPo restaurantBookPo);
	List<RestaurantBookPo> selectType(RestaurantBookPo restaurantBookPo);
	RestaurantBookPo insertBook(RestaurantBookPo restaurantBookPo);
	RestaurantBookPo updateBook(RestaurantBookPo restaurantBookPo);
	List<RestaurantBookPo> getBookInfo(RestaurantBookPo restaurantBookPo);
}
