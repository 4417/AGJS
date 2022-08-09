//package agjs.controller.restaurant;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import agjs.bean.restaurant.RestaurantTimeMoneyPo;
//import agjs.service.restaurant.RestaurantTimeMoneyService;
//
//
//
//@RestController
//@RequestMapping("/main/rest_book")
//public class RestaurantTimeMoneyController {
//	@Autowired
//	private RestaurantTimeMoneyService restaurantTimeMoneyService;
//	
//	@GetMapping("/all")
//	public List<RestaurantTimeMoneyPo> allTMoney() {
//		return restaurantTimeMoneyService.allTMoney();
//	}
//	@PutMapping("/insert")
//	public RestaurantTimeMoneyPo insertRest(@RequestBody RestaurantTimeMoneyPo restaurantTimeMoneyPo) {
//		restaurantTimeMoneyService.insertMoney(restaurantTimeMoneyPo);
//		return restaurantTimeMoneyPo;
//	}
//	
//	@PostMapping("/searchRBook")
//	public List<RestaurantTimeMoneyPo> selectStartDate(@RequestBody RestaurantTimeMoneyPo restaurantTimeMoneyPo){
//		return restaurantTimeMoneyService.selectStartDate(restaurantTimeMoneyPo);
//	}
//}
