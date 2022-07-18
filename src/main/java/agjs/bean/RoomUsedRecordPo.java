package agjs.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_USED_RECORD")
public class RoomUsedRecordPo {

//	ROOM_USED_RECORD
//	ROOM_USED_RECORD_IDint NOT NULL客房使用紀錄ID
//	ROOM_IDint NOT NULL房號
//	SALES_ORDER_HEADER_IDint NULL訂單編號
//	ORDER_START_DATEdate NOT NULL入住日期
//	ORDER_END_DATEdate NOT NULL退房日期
//	USER_NAMEvarchar(30) NULL會員姓名
//	SOURCEtinyint(1) NOT NULL

	@Id
	@Column(name = "ROOM_USED_RECORD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ROOM_ID")
	private Integer roomId;

	@Column(name = "SALES_ORDER_HEADER_ID")
	private Integer oderHeaderId;

	@Column(name = "ORDER_START_DATE")
	private Date startDate;

	@Column(name = "ORDER_END_DATE")
	private Date endDate;

	@Column(name = "USER_NAME", nullable = true)
	private String userName;

	@Column(name = "SOURCE")
	private Integer source;

	@Override
	public String toString() {
		return "RoomUsedRedordDo [id=" + id + ", roomId=" + roomId + ", oderHeaderId=" + oderHeaderId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", userName=" + userName + ", source=" + source + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getOderHeaderId() {
		return oderHeaderId;
	}

	public void setOderHeaderId(Integer oderHeaderId) {
		this.oderHeaderId = oderHeaderId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

}
