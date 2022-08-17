package agjs.dao.restaurant;

import java.util.List;

import agjs.bean.restaurant.RestaurantTimeMoneyVo;

public interface RestaurantTimeMoneyDao_interface {
	 public void insert(RestaurantTimeMoneyVo restaurantTimeMoneyVo);
     public void delete(Integer MONEY_ID);
//     public void update(RestaurantADVO restaurantADVO);
//     public RestaurantADVO findByPrimaryKey(Integer adid);
//     public List<RestaurantADVO> getAll();
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
