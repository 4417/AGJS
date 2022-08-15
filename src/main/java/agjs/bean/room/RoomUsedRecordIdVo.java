package agjs.bean.room;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class RoomUsedRecordIdVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer roomId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderStartDate;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RoomUsedRecordIdVo other = (RoomUsedRecordIdVo) o;
		return Objects.equals(roomId, other.getRoomId()) && Objects.equals(orderStartDate, other.getOrderStartDate());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(roomId, orderStartDate);
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Date getOrderStartDate() {
		return orderStartDate;
	}
	
	public void setOrderStartDate(@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}
}
