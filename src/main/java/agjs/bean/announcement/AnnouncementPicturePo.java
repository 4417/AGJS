package agjs.bean.announcement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ANNOUNCEMENT_PICTURE")
public class AnnouncementPicturePo {
	
	@Id
	@Column(name = "ANM_PICTURE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer anmPictureId;
	
	@Column(name = "ANM_ID")
	private Integer anmId;
	
	@Column(name = "ANM_PICTURE")
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
