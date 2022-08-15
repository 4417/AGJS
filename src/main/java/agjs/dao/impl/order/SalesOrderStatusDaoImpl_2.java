package agjs.dao.impl.order;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyPo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemPo;
import agjs.bean.order.SalesOrderStatusPo;
import agjs.bean.room.RoomStylePo;
import agjs.bean.user.UserPo;
import agjs.dao.order.SalesOrderStatusDao_2;

@Repository
public class SalesOrderStatusDaoImpl_2 implements SalesOrderStatusDao_2 {
	@PersistenceContext
	private Session session;

	//select * from SalesOrderStatus where salesOrderStatusId = ?
	@Override
	public SalesOrderStatusPo selectById(Integer id) {
		String hql="from SalesOrderStatusPo where salesOrderStatusId = :salesOrderStatusId";
		return session.createQuery(hql, SalesOrderStatusPo.class)
				.setParameter("salesOrderStatusId", id).uniqueResult();
	}
	
	
	//select * from SalesOrderHeader where userId = ? and salesOrderHeaderId = ?
	//訂單明細1：選取訂單明細中的日期
	@Override
	public SalesOrderHeaderPo selectByUserIdAndHeaderId(Integer id,Integer header) {
		String hql="from SalesOrderHeaderPo where userId =:userId "
				+ "and salesOrderHeaderId =: salesOrderHeaderId";
		return session.createQuery(hql, SalesOrderHeaderPo.class).setParameter("userId", id)
				.setParameter("salesOrderHeaderId", header).uniqueResult();
	}
	
	//訂單明細2：選取訂單明細中的房間明細
	@Override
	public List<Object[]> selectForRoomItem(Integer id,Integer header) {
		String sql="select rs.ROOM_NAME,i.ORDER_ROOM_QUANTITY,i.ORDER_ROOM_PRICE "
				+ "from SALES_ORDER_HEADER h join USER u on h.USER_ID=u.USER_ID "
				+ "join SALES_ORDER_ITEM i on h.SALES_ORDER_HEADER_ID=i.SALES_ORDER_HEADER_ID  "
				+ "join ROOM_STYLE rs on i.ROOM_STYLE_ID=rs.ROOM_STYLE_ID "
				+ "where u.USER_ID= ?1 and h.SALES_ORDER_HEADER_ID= ?2";
		return session.createSQLQuery(sql).setParameter(1, id).setParameter(2, header).list();
	}
	
	//訂單明細3：選取訂單明細中的行程明細
	@Override
	public List<Object[]> selectForJourneyItem(Integer id,Integer header) {
		String sql="select j.JOURNEY_NAME,ji.ADULTS,ji.CHILDREN,"
				+ "j.JOURNEY_PRICE,j.JOURNEY_PRICE_CHILD,ji.JOURNEY_DATE "
				+ "from SALES_ORDER_HEADER h join USER u on h.USER_ID=u.USER_ID  "
				+ "join JOURNEY_ITEM ji on h.SALES_ORDER_HEADER_ID=ji.SALES_ORDER_HEADER_ID "
				+ "join JOURNEY j on ji.JOURNEY_ID=j.JOURNEY_ID  "
				+ "where u.USER_ID= ?1 and h.SALES_ORDER_HEADER_ID= ?2";
		return session.createSQLQuery(sql).setParameter(1, id).setParameter(2, header).list();
	}
}
