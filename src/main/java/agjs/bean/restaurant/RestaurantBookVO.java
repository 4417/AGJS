package agjs.bean.restaurant;

import java.io.Serializable;
import java.sql.Date;

public class RestaurantBookVO implements java.io.Serializable{

	private Integer REST_BOOK_ID;
	private Integer SALES_ORDER_HEADER_ID;
	private Integer REST_ID;
	private Date REST_DATE;
	private String USER_NAME;
	private Integer REST_NUM;
	private String REST_TEL;
	private String REST_NOTE;
	
	public Integer getREST_BOOK_ID() {
		return REST_BOOK_ID;
	}
	public void setREST_BOOK_ID(Integer REST_BOOK_ID) {
		this.REST_BOOK_ID = REST_BOOK_ID;
	}
	public Integer getSALES_ORDER_HEADER_ID() {
		return SALES_ORDER_HEADER_ID;
	}
	public void setSALES_ORDER_HEADER_ID(Integer SALES_ORDER_HEADER_ID) {
		this.SALES_ORDER_HEADER_ID = SALES_ORDER_HEADER_ID;
	}
	public Integer getREST_ID() {
		return REST_ID;
	}
	public void setREST_ID(Integer REST_ID) {
		this.REST_ID = REST_ID;
	}
	public Date getREST_DATE() {
		return REST_DATE;
	}
	public void setREST_DATE(Date REST_DATE) {
		this.REST_DATE = REST_DATE;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String USER_NAME) {
		this.USER_NAME = USER_NAME;
	}
	public Integer getREST_NUM() {
		return REST_NUM;
	}
	public void setREST_NUM(Integer REST_NUM) {
		this.REST_NUM = REST_NUM;
	}
	public String getREST_TEL() {
		return REST_TEL;
	}
	public void setREST_TEL(String REST_TEL) {
		this.REST_TEL = REST_TEL;
	}
	public String getREST_NOTE() {
		return REST_NOTE;
	}
	public void setREST_NOTE(String REST_NOTE) {
		this.REST_NOTE = REST_NOTE;
	}
}
