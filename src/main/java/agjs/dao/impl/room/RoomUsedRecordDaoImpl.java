package agjs.dao.impl.room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomUsedRecordVo;
import agjs.dao.room.RoomUsedRecordDao;

@Repository
public class RoomUsedRecordDaoImpl implements RoomUsedRecordDao<RoomUsedRecordVo> {
	@PersistenceContext
	private Session session;

	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<RoomUsedRecordVo> getAll() {
		List<RoomUsedRecordVo> list = new ArrayList<RoomUsedRecordVo>();
		
		String sql = "select e.roomId,e.roomStyleId,e.roomName,record.ORDER_START_DATE as orderStartDate,record.ORDER_END_DATE as orderEndDate,record.USER_NAME as userName,record.SOURCE"
				+ " from"
				+ " (select room.ROOM_ID as roomId,style.ROOM_STYLE_ID as roomStyleId,style.ROOM_NAME as roomName"
				+ " from ROOM room join ROOM_STYLE style on room.ROOM_STYLE_ID = style.ROOM_STYLE_ID) as e"
				+ " join ROOM_USED_RECORD record" + " on e.roomId = record.ROOM_ID";
		System.out.println(sql);
		list = session.createSQLQuery(sql).addEntity(RoomUsedRecordVo.class).list();
		
		for (RoomUsedRecordVo roomUsedRecordVo : list) {
			System.out.println("DATE:"+roomUsedRecordVo.getOrderEndDate());
		}
		return list;
	}

}
