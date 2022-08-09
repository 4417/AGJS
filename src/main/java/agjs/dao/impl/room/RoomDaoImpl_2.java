package agjs.dao.impl.room;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.dao.room.RoomDao_2;

@Repository
public class RoomDaoImpl_2 implements RoomDao_2 {
	@PersistenceContext
	private Session session;
	
	//訂單修改1：判斷該日期、房型、數量是否符合使用者的修改需求
	@Override
	public Integer selectFromDateAndRoomStyle(Date startDate, Date endDate, String roomName) {
		String sql="select count(r.ROOM_ID) from ROOM r "
				+ "where r.ROOM_ID not in "
				+ "(select rur.ROOM_ID from ROOM_USED_RECORD rur "
				+ "where (?1 < rur.ORDER_END_DATE) and (?2 > rur.ORDER_START_DATE)) "
				+ "and r.ROOM_STYLE_ID = (select rs.ROOM_STYLE_ID "
				+ "from ROOM_STYLE rs where rs.ROOM_NAME like ?3)";
		

		BigInteger bigInteger = (BigInteger) session.createSQLQuery(sql)
			.setParameter(1, startDate).setParameter(2, endDate).setParameter(3, roomName).uniqueResult();
		return  bigInteger.intValue();
	}
	
}
