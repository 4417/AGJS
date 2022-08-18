package agjs.bean.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "ECPAY_ORDER")
public class EcpayOrderPo {

	@Id
	@Column(name = "ECPAY_ID")
	private String ecpayId;

	@Column(name = "SALES_ORDER_ID")
	private Integer salesOrderHeaderId;

	public EcpayOrderPo() {
	}

	@Override
	public String toString() {
		return "EcpayOrderPo [ecpayId=" + ecpayId + ", salesOrderHeaderId=" + salesOrderHeaderId + "]";
	}

	public String getEcpayId() {
		return ecpayId;
	}

	public void setEcpayId(String ecpayId) {
		this.ecpayId = ecpayId;
	}

	public Integer getSalesOrderHeaderId() {
		return salesOrderHeaderId;
	}

	public void setSalesOrderHeaderId(Integer salesOrderHeaderId) {
		this.salesOrderHeaderId = salesOrderHeaderId;
	}

}
