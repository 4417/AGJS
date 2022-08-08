package agjs.bean.restaurant;

import java.sql.Blob;
import java.sql.Date;

public class RestaurantVO implements java.io.Serializable {
	private Integer adId;
	private Integer restId;
	private String adName;
	private Blob adPic;
	private String adIntro;
	private Date adTime;

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

	public Date getAdTime() {
		return adTime;
	}

	public void setAdTime(Date adTime) {
		this.adTime = adTime;
	}
}
