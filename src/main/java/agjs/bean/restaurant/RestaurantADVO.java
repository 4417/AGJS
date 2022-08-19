package agjs.bean.restaurant;

import java.sql.Blob;

import javax.servlet.http.Part;

import org.hibernate.internal.util.BytesHelper;

public class RestaurantADVO implements java.io.Serializable {
	private Integer AD_ID;
	private Integer REST_ID;
	private String AD_NAME;
//	private byte[] AD_PIC;
	private Blob AD_PIC;
	private String AD_INTRO;
	private String AD_TIME;

	public Integer getAD_ID() {
		return AD_ID;
	}

	public void setAD_ID(Integer AD_ID) {
		this.AD_ID = AD_ID;
	}

	public Integer getRestId() {
		return REST_ID;
	}

	public void setRestId(Integer REST_ID) {
		this.REST_ID = REST_ID;
	}

	public String getAdName() {
		return AD_NAME;
	}

	public void setAdName(String AD_NAME) {
		this.AD_NAME = AD_NAME;
	}

	public Blob getAdPic() {
		return AD_PIC;
	}

	public void setAdPic(Blob AD_PIC) {
		this.AD_PIC = AD_PIC;
	}

	public String getAdIntro() {
		return AD_INTRO;
	}

	public void setAdIntro(String AD_INTRO) {
		this.AD_INTRO = AD_INTRO;
	}

	public String getAdTime() {
		return AD_TIME;
	}

	public void setAdTime(String AD_TIME) {
		this.AD_TIME = AD_TIME;
	}
}
