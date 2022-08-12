package agjs.dao.impl.room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
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
			System.out.println("DATE:" + roomUsedRecordVo.getOrderEndDate());
		}
		return list;
	}

	/*
	 * 用日期與房型查詢
	 */

	@Override
	public List<RoomUsedRecordVo> select(Date date, String roomName) {
		List<RoomUsedRecordVo> list = new ArrayList<RoomUsedRecordVo>();

		String sql = "select e.roomId,e.roomStyleId,e.roomName,record.ORDER_START_DATE as orderStartDate,record.ORDER_END_DATE as orderEndDate,record.USER_NAME as userName,record.SOURCE"
				+ " from"
				+ " (select room.ROOM_ID as roomId,style.ROOM_STYLE_ID as roomStyleId,style.ROOM_NAME as roomName"
				+ " from ROOM room join ROOM_STYLE style on room.ROOM_STYLE_ID = style.ROOM_STYLE_ID) as e"
				+ " join ROOM_USED_RECORD record" + " on e.roomId = record.ROOM_ID"
				+ " where ?1 between ORDER_START_DATE and ORDER_END_DATE and roomName like ?2";
		System.out.println(sql);
		String st = "%" + roomName + "%";
		list = session.createSQLQuery(sql).setParameter(1, date).setParameter(2, st).addEntity(RoomUsedRecordVo.class)
				.list();

		return list;
	}
	/*
	 * 用日期查詢
	 */

	@Override
	public List<RoomUsedRecordVo> selectByDate(Date orderStartDate) {
		String sql = "SELECT r.ROOM_ID as roomId, rs.ROOM_NAME as roomName, ru.USER_NAME as userName, ru.ORDER_START_DATE as orderStartDate, ru.ORDER_END_DATE as orderEndDate "
				+ "FROM ROOM r "
				+ "LEFT JOIN (SELECT * FROM ROOM_USED_RECORD WHERE ?1 BETWEEN ORDER_START_DATE AND ORDER_END_DATE) ru "
				+ "ON r.ROOM_ID = ru.ROOM_ID " + "left join ROOM_STYLE rs " + "on r.ROOM_STYLE_ID = rs.ROOM_STYLE_ID";
		System.out.println(sql);
		NativeQuery<Object[]> nativeQuery = session.createSQLQuery(sql).setParameter(1, orderStartDate);
		return objectArray2RoomUsedRecordVo(nativeQuery.list());
	}

	private List<RoomUsedRecordVo> objectArray2RoomUsedRecordVo(List<Object[]> list) {
		List<RoomUsedRecordVo> resultList = new ArrayList<>();
		for (Object[] row : list) {
			RoomUsedRecordVo vo = new RoomUsedRecordVo();
			vo.setRoomId((Integer) row[0]);
			vo.setRoomName((String) row[1]);
			vo.setUserName(row[2] != null ? (String) row[2] : "");
			vo.setOrderStartDate(row[3] != null ? (Date) row[3] : null);
			vo.setOrderEndDate(row[4] != null ? (Date) row[4] : null);
			resultList.add(vo);
		}
		return resultList;
	}

	/*
	 * 用房型查詢
	 */

	@Override
	public List<RoomUsedRecordVo> selectByRoomName(String roomName) {
		List<RoomUsedRecordVo> list = new ArrayList<RoomUsedRecordVo>();

		return null;
	}

	@Override
	public RoomUsedRecordPo update(RoomUsedRecordPo recordPo) {
		
		session.save(recordPo);
		
		return recordPo;
	}

}
