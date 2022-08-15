package agjs.bean.restaurant;


import java.sql.Blob;
import java.util.List;

public class RestaurantVo {

//	REST_ID, REST_NAME, REST_PIC, REST_FLOOR, REST_TIME, REST_INTRO, INTRO_TIME
	
	private Integer restId;
	private String restName;
	private java.sql.Blob restPic;
	private String restFloor;
	private String restTime;
	private String restIntro;
	private String introTime;
	private List<Integer> restaurantList;
	
	

	public RestaurantVo() {
		super();

	}

	public RestaurantVo(Integer restId, String restName, Blob restPic, String restFloor, String restTime,
			String restIntro, String introTime) {
		super();
		this.restId = restId;
		this.restName = restName;
		this.restPic = restPic;
		this.restFloor = restFloor;
		this.restTime = restTime;
		this.restIntro = restIntro;
		this.introTime = introTime;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public Blob getRestPic() {
		return restPic;
	}

	public void setRestPic(Blob restPic) {
		this.restPic = restPic;
	}

	public String getRestFloor() {
		return restFloor;
	}

	public void setRestFloor(String restFloor) {
		this.restFloor = restFloor;
	}

	public String getRestTime() {
		return restTime;
	}

	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}

	public String getRestIntro() {
		return restIntro;
	}

	public void setRestIntro(String restIntro) {
		this.restIntro = restIntro;
	}

	public String getIntroTime() {
		return introTime;
	}

	public void setIntroTime(String introTime) {
		this.introTime = introTime;
	}

	public List<Integer> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<Integer> restaurantList) {
		this.restaurantList = restaurantList;
	}

}
