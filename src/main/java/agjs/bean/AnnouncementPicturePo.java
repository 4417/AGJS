package agjs.bean;

public class AnnouncementPicturePo {
	
	private Integer anmId;
	private Byte[] anmPicture;
	
	public AnnouncementPicturePo() {
		super();
	}
	
	public Integer getAnmId() {
		return anmId;
	}
	public void setAnmId(Integer anmId) {
		this.anmId = anmId;
	}
	
	public Byte[] getAnmPicture() {
		return anmPicture;
	}
	public void setAnmPicture(Byte[] anmPicture) {
		this.anmPicture = anmPicture;
	}
}
