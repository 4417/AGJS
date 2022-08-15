package agjs.bean.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.user.UserPo;

public class OrderSubmitdVo {

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date startDay;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date endDay;

	private SalesOrderHeaderPo soh;
	private List<SalesOrderItemPo> soiList;
	private List<JourneyItemPo> jiList;
	private UserPo user;

	private String remark;

	public OrderSubmitdVo() {
	}

	@Override
	public String toString() {
		return "OrderSubmitdVo [startDay=" + startDay + ", endDay=" + endDay + ", soh=" + soh + ", soiList=" + soiList
				+ ", jiList=" + jiList + ", user=" + user + ", remark=" + remark + "]";
	}

	public java.util.Date getStartDay() {
		return startDay;
	}

	public void setStartDay(java.util.Date startDay) {
		this.startDay = startDay;
	}

	public java.util.Date getEndDay() {
		return endDay;
	}

	public void setEndDay(java.util.Date endDay) {
		this.endDay = endDay;
	}

	public SalesOrderHeaderPo getSoh() {
		return soh;
	}

	public void setSoh(SalesOrderHeaderPo soh) {
		this.soh = soh;
	}

	public List<SalesOrderItemPo> getSoiList() {
		return soiList;
	}

	public void setSoiList(List<SalesOrderItemPo> soiList) {
		this.soiList = soiList;
	}

	public List<JourneyItemPo> getJiList() {
		return jiList;
	}

	public void setJiList(List<JourneyItemPo> jiList) {
		this.jiList = jiList;
	}

	public UserPo getUser() {
		return user;
	}

	public void setUser(UserPo user) {
		this.user = user;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
