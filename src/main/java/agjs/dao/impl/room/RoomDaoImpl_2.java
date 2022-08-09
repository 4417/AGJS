package agjs.dao.impl.room;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDaoImpl_2 {
	@PersistenceContext
	private Session session;
	
	//訂單修改1：判斷該日期、房型、數量是否符合使用者的修改需求
	@Override
	public Integer  selectFromDateAndRoomStyle(Integer id,Integer header) {
		String sql="select rs.ROOM_NAME,i.ORDER_ROOM_QUANTITY,i.ORDER_ROOM_PRICE "
				+ "from SALES_ORDER_HEADER h join USER u on h.USER_ID=u.USER_ID "
				+ "join SALES_ORDER_ITEM i on h.SALES_ORDER_HEADER_ID=i.SALES_ORDER_HEADER_ID  "
				+ "join ROOM_STYLE rs on i.ROOM_STYLE_ID=rs.ROOM_STYLE_ID "
				+ "where u.USER_ID= ?1 and h.SALES_ORDER_HEADER_ID= ?2";
		return session.createSQLQuery(sql).setParameter(1, id).setParameter(2, header).list();
	}
	
}
