package agjs.bean.order;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyItemVo_2;


//給後臺修改訂單時用的VO
@Repository
public class SalesOrderFrontendAdminVo {
	
//	datefilter: "2022-08-08 - 2022-08-09"
//	status: "客服處理中"

	private Integer salesOrderHeaderId;
	private Date salesOrderStartDate;
	private Date salesOrderEndDate;
	private String salesOrderStatus;
	private String userName;
	private String msg;
	private String errMsg;
//	private List<SalesOrderItemVo> salesOrderItemList;
//	private List<JourneyItemVo_2> jItemList;
	public Integer getSalesOrderHeaderId() {
		return salesOrderHeaderId;
	}
	public void setSalesOrderHeaderId(Integer salesOrderHeaderId) {
		this.salesOrderHeaderId = salesOrderHeaderId;
	}
	public Date getSalesOrderStartDate() {
		return salesOrderStartDate;
	}
	public void setSalesOrderStartDate(Date salesOrderStartDate) {
		this.salesOrderStartDate = salesOrderStartDate;
	}
	public Date getSalesOrderEndDate() {
		return salesOrderEndDate;
	}
	public void setSalesOrderEndDate(Date salesOrderEndDate) {
		this.salesOrderEndDate = salesOrderEndDate;
	}
	public String getSalesOrderStatus() {
		return salesOrderStatus;
	}
	public void setSalesOrderStatus(String salesOrderStatus) {
		this.salesOrderStatus = salesOrderStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
//	public List<SalesOrderItemVo> getSalesOrderItemList() {
//		return salesOrderItemList;
//	}
//	public void setSalesOrderItemList(List<SalesOrderItemVo> salesOrderItemList) {
//		this.salesOrderItemList = salesOrderItemList;
//	}
//	public List<JourneyItemVo_2> getjItemList() {
//		return jItemList;
//	}
//	public void setjItemList(List<JourneyItemVo_2> jItemList) {
//		this.jItemList = jItemList;
//	}
	
	
	
	

}
