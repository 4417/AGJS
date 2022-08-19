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
//import agjs.bean.restaurant.RestaurantBookPo;
//import agjs.service.restaurant.RestaurantBookService;
//
//@RestController
//@RequestMapping("/main/rest_book")
//public class RestaurantBookController {
//	@Autowired
//	private RestaurantBookService restaurantBookService;
//	
//	@GetMapping("/all")
//	public List<RestaurantBookPo> allRBook() {
//		return restaurantBookService.allRBook();
//	}
//	@PutMapping("/insert")
//	public RestaurantBookPo insertBook(@RequestBody RestaurantBookPo restaurantBookPo) {
//		restaurantBookService.insertBook(restaurantBookPo);
//		return restaurantBookPo;
//	}
//	
//	@PostMapping("/searchRBook")
//	public List<RestaurantBookPo> getBookInfo(@RequestBody RestaurantBookPo restaurantBookPo){
//		return restaurantBookService.getBookInfo(restaurantBookPo);
//	}
//}
