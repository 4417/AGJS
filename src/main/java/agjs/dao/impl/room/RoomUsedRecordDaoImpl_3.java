package agjs.dao.impl.room;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomUsedRecordVo;
import agjs.dao.room.RoomUsedRecordDao_2;

@Repository
public class RoomUsedRecordDaoImpl_3 implements RoomUsedRecordDao_2<RoomUsedRecordVo> {
	@PersistenceContext
	private Session session;

	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	
	@Override
	public List<Object[]> getNameAndStyleId() {
		String sql = "select rs.ROOM_NAME, rs.ROOM_STYLE_ID, r.ROOM_ID from ROOM_STYLE rs join ROOM r on r.ROOM_STYLE_ID=rs.ROOM_STYLE_ID;";
		
		return session.createSQLQuery(sql).list();
		
	}
	
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
		return list;
	}

	@Override
	public boolean delete(Integer sohid) {
		
		if(sohid != null) {
			
			String hql = "from ROOM_USED_RECORD "
					+ "where SALES_ORDER_HEADER_ID = :id";
			
			List<RoomUsedRecordPo> selectList = session.createQuery(hql, RoomUsedRecordPo.class).setParameter("id", sohid).list();
			if(!selectList.isEmpty()) {
				
				for (RoomUsedRecordPo i : selectList) {
					session.delete(i);
				}
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public boolean insertByHeaderId(RoomUsedRecordPo po) {
		String hql="from RoomUsedRecordPo where oderHeaderId = :sohid";

		List<RoomUsedRecordPo> select=session.createQuery(hql, RoomUsedRecordPo.class)
				.setParameter("sohid", po.getOderHeaderId()).list();
			if(select.isEmpty()) {
				session.save(po);
				return true;
			}else {
				return false;
			}
		
	}
	
	@Override
	public List<Object[]> selectEmptyRoomList(Date startDate, Date endDate,Integer id, String roomName) {
		String sql="select r.ROOM_ID from ROOM r "
				+ "where r.ROOM_ID not in "
				+ "(select rur.ROOM_ID from ROOM_USED_RECORD rur "
				+ "where (?1 < rur.ORDER_END_DATE) and (?2 > rur.ORDER_START_DATE)"
				+ "and (rur.SALES_ORDER_HEADER_ID<>?3 or rur.SALES_ORDER_HEADER_ID is null)) "
				+ "and r.ROOM_STYLE_ID = (select rs.ROOM_STYLE_ID "
				+ "from ROOM_STYLE rs where rs.ROOM_NAME like ?4)";
		

//		BigInteger bigInteger = (BigInteger) session.createSQLQuery(sql)
//			.setParameter(1, startDate).setParameter(2, endDate).setParameter(3, name).setParameter(4, roomName).uniqueResult();
//		return  bigInteger.intValue();
		//若有出現null例外時使用
		List<Object[]> emptyList = session.createSQLQuery(sql)
				.setParameter(1, startDate).setParameter(2, endDate).setParameter(3, id).setParameter(4, roomName).list();
		return emptyList;
	}
}
