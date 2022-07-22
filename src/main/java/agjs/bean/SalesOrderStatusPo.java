package agjs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SALES_ORDER_STATUS")
public class SalesOrderStatusPo {

//	SALES_ORDER_STATUS_ID int PK 
//	SALES_ORDER_STATUS varchar(25)
	
	@Id
	@Column(name = "SALES_ORDER_STATUS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesOrderStatusId;
	
	@Column(name = "SALES_ORDER_STATUS")
	private String salesOrderStatus;
	
	@Override
	public String toString() {
		return "SOStatusPO [salesOrderStatusId=" + salesOrderStatusId + ", salesOrderStatus=" + salesOrderStatus + "]";
	}
	
	public String getSalesOrderStatus() {
		return salesOrderStatus;
	}

	public void setSalesOrderStatus(String salesOrderStatus) {
		this.salesOrderStatus = salesOrderStatus;
	}

	public Integer getSalesOrderStatusId() {
		return salesOrderStatusId;
	}
	
	public void setSalesOrderStatusId(Integer salesOrderStatusId) {
		this.salesOrderStatusId = salesOrderStatusId;
	}
}
