package agjs.bean.restaurant;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "REST_LIST")
public class RestaurantPo {

//	REST_ID, REST_NAME, REST_PIC, REST_FLOOR, REST_TIME, REST_INTRO, INTRO_TIME
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REST_ID")
	private Integer restId;
	@Column(name = "REST_NAME")
	private String restName;
	@Column(name = "REST_PIC")
	private java.sql.Blob restPic;
	@Column(name = "REST_FLOOR")
	private String restFloor;
	@Column(name = "REST_TIME")
	private java.sql.Date restTime;
	@Column(name = "REST_INTRO")
	private String restIntro;
	@Column(name = "INTRO_TIME")
	private String introTime;

	@Transient
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public RestaurantPo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantPo(Integer restId, String restName, Blob restPic, String restFloor, Date restTime,
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

	public java.sql.Date getRestTime() {
		return restTime;
	}

	public void setRestTime(java.sql.Date restTime) {
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

}
