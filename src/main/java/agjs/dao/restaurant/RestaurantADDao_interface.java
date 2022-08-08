package agjs.dao.restaurant;

import java.util.List;

import agjs.bean.restaurant.RestaurantADVO;

public interface RestaurantADDao_interface {
	 public void insert(RestaurantADVO restaurantADVO);
     public void update(RestaurantADVO restaurantADVO);
     public void delete(Integer adId);
     public RestaurantADVO findByPrimaryKey(Integer adid);
     public List<RestaurantADVO> getAll();
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
