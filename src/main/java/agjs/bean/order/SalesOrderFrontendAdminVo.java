package agjs.bean.order;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;


//給後臺修改訂單時用的VO
@Repository
public class SalesOrderFrontendAdminVo {
	
//	datefilter: "2022-08-08 - 2022-08-09"
//	status: "客服處理中"

	private Integer salesOrderHeaderId;
	private Date startDate;
	private Date endDate;
	private String status;
	private String userName;
	private List<SalesOrderItemVo> salesOrderItemList;
	
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<SalesOrderItemVo> getSalesOrderItemList() {
		return salesOrderItemList;
	}
	public void setSalesOrderItemList(List<SalesOrderItemVo> salesOrderItemList) {
		this.salesOrderItemList = salesOrderItemList;
	}

	
	

}
