package agjs.bean.journey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import agjs.bean.CoreBean;

@Entity
@Table(name = "JOURNEY_TYPE")
public class JourneyTypePo extends CoreBean {

	@Id
	@Column(name = "JOURNEY_TYPE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer typeId;
	@Column(name = "JOURNEY_TYPE", nullable = false)
	private String typeName;

	public JourneyTypePo() {
	}

	@Override
	public String toString() {
		return "JourneyTypePo [typeId=" + typeId + ", typeName=" + typeName + "]";
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
