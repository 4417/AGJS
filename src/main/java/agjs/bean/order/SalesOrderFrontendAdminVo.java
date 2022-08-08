package agjs.bean.order;

import java.util.Date;
import org.springframework.stereotype.Repository;

@Repository
public class SalesOrderFrontendAdminVo {
	
//	datefilter: "2022-08-08 - 2022-08-09"
//	status: "客服處理中"

	private Integer salesOrderHeaderId;
	private Date startDate;
	private Date endDate;
	private String status;
	
	@Override
	public String toString() {
		return "SalesOrderFrontendVo [salesOrderHeaderId=" + salesOrderHeaderId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", status=" + status + "]";
	}
	
	public Integer getSalesOrderHeaderId() {
		return salesOrderHeaderId;
	}
	public void setSalesOrderHeaderId(Integer salesOrderHeaderId) {
		this.salesOrderHeaderId = salesOrderHeaderId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
