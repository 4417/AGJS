package agjs.dao.impl.room;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.dao.room.RoomDao_2;

@Repository
public class RoomDaoImpl_2 implements RoomDao_2 {
	@PersistenceContext
	private Session session;
	
	//訂單修改1：判斷該日期、房型、數量是否符合使用者的修改需求(去掉該日期同一個會員的)
	@Override
	public Integer selectFromDateAndRoomStyle(Date startDate, Date endDate,Integer id, String roomName) {
		String sql="select count(r.ROOM_ID) from ROOM r "
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
		Optional<?> option = session.createSQLQuery(sql)
				.setParameter(1, startDate).setParameter(2, endDate).setParameter(3, id).setParameter(4, roomName)
				.uniqueResultOptional();
		return option.isPresent() ? ((BigInteger) option.get()).intValue() : 0;
	}
	
	//select APPLY_LIMIT from JOURNEY where JOURNEY_NAME like "林間巡禮";
	//訂單修改2：確認行程人數上限
	@Override
	public Integer selectByJourneyName(String name) {
		String hql="select applyLimit from JourneyPo where journeyName like :journeyName";

		return session.createQuery(hql, Integer.class)
				.setParameter("journeyName", name).uniqueResult();
	}
	
	//訂單修改3：確認當天行程目前總人數
	@Override
	public Integer selectByDateAndName(Date startDate,Integer id, String name) {
		String sql="select sum(ji.ADULTS)+sum(ji.CHILDREN) "
				+ "from JOURNEY_ITEM ji where ji.JOURNEY_DATE = ?1 "
				+ "and ji.SALES_ORDER_HEADER_ID<>?2 "
				+ "and ji.JOURNEY_ID = ("
				+ "select j.JOURNEY_ID from JOURNEY j "
				+ "where j.JOURNEY_NAME like ?3 )";
		//若有出現null例外時使用
		Optional<?> option = session.createSQLQuery(sql)
				.setParameter(1, startDate).setParameter(2, id).setParameter(3, name)
				.uniqueResultOptional();
		return option.isPresent() ? ((BigDecimal) option.get()).intValue() : 0;
	}
	
	//訂單修改4：從訂單ID找房間使用紀錄，再刪除
	@Override
	public boolean deleteByHeaderId(Integer id) {
		String hql="from RoomUsedRecordPo where oderHeaderId = :oderHeaderId";

		List<RoomUsedRecordPo> select=session.createQuery(hql, RoomUsedRecordPo.class)
				.setParameter("oderHeaderId", id).list();
		if(select.isEmpty()) {
			
			return false;
		}else {
			for (RoomUsedRecordPo index : select) {
				session.delete(index);
			}
			return true;
		}
	}
	
	//訂單修改5：從訂單ID找房間使用紀錄，沒找到再新增新的紀錄(房號需在service計算並隨機分配)
	@Override
	public boolean insertByHeaderId(RoomUsedRecordPo po) {
		String hql="from RoomUsedRecordPo where oderHeaderId = :oderHeaderId";

		List<RoomUsedRecordPo> select=session.createQuery(hql, RoomUsedRecordPo.class)
				.setParameter("oderHeaderId", po.getOderHeaderId()).list();
			if(select.isEmpty()) {
				session.save(po);
				return true;
			}else {
				return false;
			}
		
	}
	
}
