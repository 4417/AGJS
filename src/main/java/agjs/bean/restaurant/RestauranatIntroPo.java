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
public class RestauranatIntroPo {

//	AD_ID, REST_ID, AD_NAME, AD_PIC, AD_INTRO, AD_TIME
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AD_ID")
	private Integer adId;
	@Column(name = "REST_ID")
	private Integer restId;
	@Column(name = "AD_NAME")
	private String adName;
	@Column(name = "AD_PIC")
	private Blob adPic;
	@Column(name = "AD_INTRO")
	private String adIntro;
	@Column(name = "AD_TIME")
	private java.sql.Date adTime;

	@Transient
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public RestauranatIntroPo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestauranatIntroPo(Integer adId, Integer restId, String adName, Blob adPic, String adIntro,
			Date adTime) {
		super();
		this.adId = adId;
		this.restId = restId;
		this.adName = adName;
		this.adPic = adPic;
		this.adIntro = adIntro;
		this.adTime = adTime;
	}

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public Blob getAdPic() {
		return adPic;
	}

	public void setAdPic(Blob adPic) {
		this.adPic = adPic;
	}

	public String getAdIntro() {
		return adIntro;
	}

	public void setAdIntro(String adIntro) {
		this.adIntro = adIntro;
	}

	public java.sql.Date getAdTime() {
		return adTime;
	}

	public void setAdTime(java.sql.Date adTime) {
		this.adTime = adTime;
	}
}
