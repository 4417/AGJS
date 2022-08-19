package agjs.bean.restaurant;

import java.sql.Blob;
import java.sql.Date;

public class RestaurantVo implements java.io.Serializable{

//	REST_ID, REST_NAME, REST_PIC, REST_FLOOR, REST_TIME, REST_INTRO, INTRO_TIME
	private Integer REST_ID;
	private String REST_NAME;
	private java.sql.Blob REST_PIC;
	private String REST_FLOOR;
	private String REST_TIME;
	private String REST_INTRO;
	private Date INTRO_TIME;

	
	public Integer getREST_ID() {
		return REST_ID;
	}

	public void setREST_ID(Integer REST_ID) {
		this.REST_ID = REST_ID;
	}

	public String getREST_NAME() {
		return REST_NAME;
	}

	public void setREST_NAME(String REST_NAME) {
		this.REST_NAME = REST_NAME;
	}

	public Blob getREST_PIC() {
		return REST_PIC;
	}

	public void setREST_PIC(Blob REST_PIC) {
		this.REST_PIC = REST_PIC;
	}

	public String getREST_FLOOR() {
		return REST_FLOOR;
	}

	public void setREST_FLOOR(String REST_FLOOR) {
		this.REST_FLOOR = REST_FLOOR;
	}

	public String getREST_TIME() {
		return REST_TIME;
	}

	public void setREST_TIME(String REST_TIME) {
		this.REST_TIME = REST_TIME;
	}

	public String getREST_INTRO() {
		return REST_INTRO;
	}

	public void setREST_INTRO(String REST_INTRO) {
		this.REST_INTRO = REST_INTRO;
	}

	public Date getINTRO_TIME() {
		return INTRO_TIME;
	}

	public void setINTRO_TIME(Date INTRO_TIME) {
		this.INTRO_TIME = INTRO_TIME;
	}

}
