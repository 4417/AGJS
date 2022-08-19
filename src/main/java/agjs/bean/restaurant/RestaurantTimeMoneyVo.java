package agjs.bean.restaurant;


public class RestaurantTimeMoneyVo implements java.io.Serializable {

//	MONEY_ID, REST_ID, MONEY, MONEY_CONTENT
	private Integer MONEY_ID;
	private Integer REST_ID;
	private String MONEY;
	private String MONEY_CONTENT;

	public Integer getMONEY_ID() {
		return MONEY_ID;
	}

	public void setMONEY_ID(Integer MONEY_ID) {
		this.MONEY_ID = MONEY_ID;
	}

	public Integer getREST_ID() {
		return REST_ID;
	}

	public void setREST_ID(Integer REST_ID) {
		this.REST_ID = REST_ID;
	}

	public String getMONEY() {
		return MONEY;
	}

	public void setMONEY(String MONEY) {
		this.MONEY = MONEY;
	}

	public String getMONEY_CONTENT() {
		return MONEY_CONTENT;
	}

	public void setMONEY_CONTENT(String MONEY_CONTENT) {
		this.MONEY_CONTENT = MONEY_CONTENT;
	}

	
}
