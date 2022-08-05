package agjs.bean.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import agjs.bean.journey.JourneyItemPo;

public class OrderSubmitdVo {

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date startDay;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date endDay;

	private SalesOrderHeaderPo soh;
	private List<SalesOrderItemPo> soiList;
	private List<JourneyItemPo> journeyItemList;

	private String remark;

	public OrderSubmitdVo() {
	}

}
