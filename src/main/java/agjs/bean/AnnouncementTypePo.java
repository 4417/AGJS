package agjs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ANNOUNCEMENT_TYPE")
public class AnnouncementTypePo {
	
	@Id
	@Column(name = "ANM_TYPE_ID")
	private Integer anmTypeId;
	
	@Column(name = "ANM_TYPE")
	private String anmType;
	
	
	public AnnouncementTypePo() {
		super();
	}
	public Integer getAnmTypeId() {
		return anmTypeId;
	}
	public void setAnmTypeId(Integer anmTypeId) {
		this.anmTypeId = anmTypeId;
	}
	public String getAnmType() {
		return anmType;
	}
	public void setAnmType(String anmType) {
		this.anmType = anmType;
	}
}
