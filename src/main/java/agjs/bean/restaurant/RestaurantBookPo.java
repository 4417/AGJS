package agjs.bean.restaurant;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REST_BOOK")
public class RestaurantBookPo {

	@Id
	@Column(name = "REST_BOOK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer restBookId;
	
	@Column(name = "SALES_ORDER_HEADER_ID")
	private Integer salesOrderHeaderId;
	
	@Column(name = "REST_ID")
	private Integer restId;
	
	@Column(name = "REST_DATE")
	private Date restDate;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "REST_NUM")
	private Integer restNum;
	
	@Column(name = "REST_TEL")
	private String restTel;
	
	@Column(name = "REST_NOTE")
	private String restNote;

	@Override
	public String toString() {
		return "RestaurantBookPo [restBookId=" + restBookId + ", salesOrderHeaderId=" + salesOrderHeaderId + ", restId="
				+ restId + ", restDate=" + restDate + ", userName=" + userName + ", restNum=" + restNum + ", restTel="
				+ restTel + ", restNote=" + restNote + "]";
	}
	public RestaurantBookPo() {
			super();
	}
	public Integer getRestBookId() {
		return restBookId;
	}

	public void setRestBookId(Integer restBookId) {
		this.restBookId = restBookId;
	}

	public Integer getSalesOrderHeaderId() {
		return salesOrderHeaderId;
	}

	public void setSalesOrderHeaderId(Integer salesOrderHeaderId) {
		this.salesOrderHeaderId = salesOrderHeaderId;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	public Date getRestDate() {
		return restDate;
	}

	public void setRestDate(Date restDate) {
		this.restDate = restDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRestNum() {
		return restNum;
	}

	public void setRestNum(Integer restNum) {
		this.restNum = restNum;
	}

	public String getRestTel() {
		return restTel;
	}

	public void setRestTel(String restTel) {
		this.restTel = restTel;
	}

	public String getRestNote() {
		return restNote;
	}

	public void setRestNote(String restNote) {
		this.restNote = restNote;
	}
}
