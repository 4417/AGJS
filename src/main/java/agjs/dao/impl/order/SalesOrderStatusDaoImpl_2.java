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
	
	
	//訂單明細，join 6張表後根據會員ID查詢訂單明細資料
	@Override
	public List<Object[]> selectByUserIdAndHeaderId(Integer id) {
		String sql="select * from SALES_ORDER_HEADER h "
				+ "join USER u on h.USER_ID=u.USER_ID "
				+ "join SALES_ORDER_ITEM i on h.SALES_ORDER_HEADER_ID=i.SALES_ORDER_HEADER_ID "
				+ "join JOURNEY_ITEM ji on h.SALES_ORDER_HEADER_ID=ji.SALES_ORDER_HEADER_ID "
				+ "join JOURNEY j on ji.JOURNEY_ID=j.JOURNEY_ID "
				+ "join ROOM_STYLE rs on i.ROOM_STYLE_ID=rs.ROOM_STYLE_ID "
				+ "where u.USER_ID= ?1";
		return session.createSQLQuery(sql).addEntity(SalesOrderHeaderPo.class).addEntity(UserPo.class).addEntity(SalesOrderItemPo.class).addEntity(JourneyItemPo.class)
				.addEntity(JourneyPo.class).addEntity(RoomStylePo.class).setParameter(1, id).list();
	}
}
