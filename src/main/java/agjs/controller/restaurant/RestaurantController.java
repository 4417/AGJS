package agjs.controller.restaurant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.restaurant.RestaurantPo;
import agjs.bean.restaurant.RestaurantVo;
import agjs.service.restaurant.RestaurantService;

@RestController
//@RequestMapping(path ={"/admin/restaurant"})
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping("/restaurant")
	public List<RestaurantPo> getRestaurantAll(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("RestaurantPo");
		doOptions(request, response);
		return restaurantService.getRestaurantAll();
	}

	@PostMapping(value ="/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RestaurantPo insert(@RequestBody RestaurantVo restaurantPojo) {
		System.out.println("Post");
		System.out.println("restaurantPojo:" + restaurantPojo);
		System.out.println(restaurantPojo.getRestName());
		System.out.println(restaurantPojo.getRestPic());
		System.out.println(restaurantPojo.getRestFloor());
		System.out.println(restaurantPojo.getRestTime());
		System.out.println(restaurantPojo.getRestIntro());
		System.out.println(restaurantPojo.getIntroTime());
		System.out.println(restaurantPojo.getRestaurantList());
		for (Integer facilitiesId : restaurantPojo.getRestaurantList()) {
			System.out.println(facilitiesId);
		}
		
		RestaurantPo restaurantPo = new RestaurantPo();
		restaurantPo.setRestName(restaurantPojo.getRestName());
		restaurantPo.setRestPic(restaurantPojo.getRestPic());
		restaurantPo.setRestFloor(restaurantPojo.getRestFloor());
		restaurantPo.setRestTime(restaurantPojo.getRestTime());
		restaurantPo.setRestIntro(restaurantPojo.getRestIntro());
		restaurantPo.setIntroTime(restaurantPojo.getIntroTime());

		Integer id = restaurantService.insert(restaurantPo, restaurantPojo.getRestaurantList());
//		roomStylePo = service.getById(id);
		return restaurantPo;
	}

	@DeleteMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RestaurantPo delete(@RequestBody Integer[] restIds) {
		System.out.println("Delete");
		for (Integer restId : restIds) {
			System.out.println(restId);
		}
		// 將收到的id丟到service層
		restaurantService.delete(restIds);

		return null;
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
//	throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
// 重要 給跨域請求呼叫
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");

	}
}