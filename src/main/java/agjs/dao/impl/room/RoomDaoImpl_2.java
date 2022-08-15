package agjs.dao.impl.room;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.room.RoomUsedRecordPo;
import agjs.dao.room.RoomDao_2;

@Repository
public class RoomDaoImpl_2 implements RoomDao_2 {
	@PersistenceContext
	private Session session;

	// 訂單修改1：判斷該日期、房型、數量是否符合使用者的修改需求(去掉該日期同一個會員的)
	@Override
	public Integer selectFromDateAndRoomStyle(Date startDate, Date endDate, Integer id, String roomName) {
		String sql = "select count(r.ROOM_ID) from ROOM r " + "where r.ROOM_ID not in "
				+ "(select rur.ROOM_ID from ROOM_USED_RECORD rur "
				+ "where (?1 < rur.ORDER_END_DATE) and (?2 > rur.ORDER_START_DATE)"
				+ "and (rur.SALES_ORDER_HEADER_ID<>?3 or rur.SALES_ORDER_HEADER_ID is null)) "
				+ "and r.ROOM_STYLE_ID = (select rs.ROOM_STYLE_ID " + "from ROOM_STYLE rs where rs.ROOM_NAME like ?4)";

//		BigInteger bigInteger = (BigInteger) session.createSQLQuery(sql)
//			.setParameter(1, startDate).setParameter(2, endDate).setParameter(3, name).setParameter(4, roomName).uniqueResult();
//		return  bigInteger.intValue();
		// 若有出現null例外時使用
		Optional<?> option = session.createSQLQuery(sql).setParameter(1, startDate).setParameter(2, endDate)
				.setParameter(3, id).setParameter(4, roomName).uniqueResultOptional();
		return option.isPresent() ? ((BigInteger) option.get()).intValue() : 0;
	}

	// select APPLY_LIMIT from JOURNEY where JOURNEY_NAME like "林間巡禮";
	// 訂單修改2：確認行程人數上限
	@Override
	public Integer selectByJourneyName(String name) {
		String hql = "select applyLimit from JourneyPo where journeyName like :journeyName";

		return session.createQuery(hql, Integer.class).setParameter("journeyName", name).uniqueResult();
	}

	// 訂單修改3：確認當天行程目前總人數
	@Override
	public Integer selectByDateAndName(Date startDate, Integer id, String name) {
		String sql = "select sum(ji.ADULTS)+sum(ji.CHILDREN) " + "from JOURNEY_ITEM ji where ji.JOURNEY_DATE = ?1 "
				+ "and ji.SALES_ORDER_HEADER_ID<>?2 " + "and ji.JOURNEY_ID = (" + "select j.JOURNEY_ID from JOURNEY j "
				+ "where j.JOURNEY_NAME like ?3 )";
		// 若有出現null例外時使用
		Optional<?> option = session.createSQLQuery(sql).setParameter(1, startDate).setParameter(2, id)
				.setParameter(3, name).uniqueResultOptional();
		return option.isPresent() ? ((BigDecimal) option.get()).intValue() : 0;
	}

	// 訂單修改4：從訂單ID找房間使用紀錄，再刪除
	@Override
	public boolean deleteByHeaderId(Integer id) {
		String hql = "from RoomUsedRecordPo where oderHeaderId = :oderHeaderId";

		List<RoomUsedRecordPo> select = session.createQuery(hql, RoomUsedRecordPo.class)
				.setParameter("oderHeaderId", id).list();
		if (select.isEmpty()) {

			return false;
		} else {
			for (RoomUsedRecordPo index : select) {
				session.delete(index);
			}
			return true;
		}
	}

	// 訂單修改5：從訂單ID找房間使用紀錄，沒找到再新增新的紀錄(房號需在service計算並隨機分配)
	@Override
	public boolean insertByHeaderId(List<RoomUsedRecordPo> po) {
		String hql = "from RoomUsedRecordPo where oderHeaderId = :oderHeaderId";
		Integer id = null;
		for (RoomUsedRecordPo index : po) {
			id = index.getOderHeaderId();
		}
		System.out.println("id=" + id);
		List<RoomUsedRecordPo> select = session.createQuery(hql, RoomUsedRecordPo.class)
				.setParameter("oderHeaderId", id).list();
		if (select.isEmpty()) {
			for (RoomUsedRecordPo index : po) {
				session.save(index);
			}
			return true;
		} else {
			return false;
		}

	}

	// 訂單修改6：列出該日期房型的剩餘房號有哪些(去掉該日期同一個會員的)
	@Override
	public List<?> selectForRoomId(Date startDate, Date endDate, Integer id, String roomName) {
		String sql = "select r.ROOM_ID from ROOM r " + "where r.ROOM_ID not in "
				+ "(select rur.ROOM_ID from ROOM_USED_RECORD rur "
				+ "where (?1 < rur.ORDER_END_DATE) and (?2 > rur.ORDER_START_DATE)"
				+ "and (rur.SALES_ORDER_HEADER_ID<>?3 or rur.SALES_ORDER_HEADER_ID is null)) "
				+ "and r.ROOM_STYLE_ID = (select rs.ROOM_STYLE_ID " + "from ROOM_STYLE rs where rs.ROOM_NAME like ?4)";

		return session.createSQLQuery(sql).setParameter(1, startDate).setParameter(2, endDate).setParameter(3, id)
				.setParameter(4, roomName).list();

	}

	// 訂單修改7：從訂單ID找行程明細
	@Override
	public List<JourneyItemPo> selectbySohId(Integer id) {

		String hql = "from JourneyItemPo where sohId = :sohId";

		return session.createQuery(hql, JourneyItemPo.class).setParameter("sohId", id).list();

	}

	// 訂單修改8：從訂單ID找行程明細，並修改行程明細日期
	@Override
	public boolean updateJourneyDate(JourneyItemPo po) {

		String hql = "from JourneyItemPo where sohId = :sohId";
		List<JourneyItemPo> select = session.createQuery(hql, JourneyItemPo.class).setParameter("sohId", po.getSohId())
				.list();
		if (select.isEmpty()) {
			return false;
		} else {
			for (JourneyItemPo index : select) {
				session.merge(po);
			}
			return true;
		}
	}

	@Override
	public List<?> selectForRoomStyleId(Date startDate, Date endDate, Integer roomStyleId, Integer count) {

		String sql = "SELECT ROOM_ID FROM ROOM WHERE ROOM_ID NOT IN (SELECT rur.ROOM_ID FROM ROOM_USED_RECORD rur "
				+ "WHERE ?1 < rur.ORDER_END_DATE AND ?2 > rur.ORDER_START_DATE ) AND ROOM_STYLE_ID = ?3 LIMIT ?4 ;";

		return session.createSQLQuery(sql).setParameter(1, startDate).setParameter(2, endDate)
				.setParameter(3, roomStyleId).setParameter(4, count).list();

	}

}
