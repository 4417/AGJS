package agjs.controller.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import agjs.bean.restaurant.RestaurantPo;
import agjs.bean.user.UserPo;
import agjs.service.restaurant.RestaurantService;

@RestController
@RequestMapping(path ={"/admin/restaurant"})
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping("/select")
	public List<RestaurantPo> select() {
		System.out.println("select");
		return restaurantService.getRestaurantAll();
	}

	@PostMapping("/select2")
	public List<RestaurantPo> select2(@RequestBody RestaurantPo restaurantPo) {
		return restaurantService.getRestaurantAll();
	}

	@PutMapping("/insert")
	public RestaurantPo insertRest(@RequestBody RestaurantPo restaurantPo) {
		restaurantService.insertRest(restaurantPo);
		System.out.println("insert");
		return restaurantPo;
	}
	@DeleteMapping("/delete")
	public List<RestaurantPo> delete(@RequestBody RestaurantPo restaurantPo){
		return restaurantService.delete(restaurantPo);
	}
}
