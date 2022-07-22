package agjs.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agjs.bean.RoomStylePo;
import agjs.dao.RoomStyleIn;

@Repository
public class RoomStyleDaoImpl implements RoomStyleIn<RoomStylePo> {
//	@Autowired
//	private  DataSource ds ;
	@Autowired
	private SessionFactory sessionFactory;

//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AGJS");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}


	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	//@Transactional
	public List<RoomStylePo> getAll() {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		Query<RoomStylePo> query = session.createQuery("FROM RoomStylePo", RoomStylePo.class);
		List<RoomStylePo> list = query.list();

		return list;
	}

//	@Override
//	public List<RoomStylePo> getAll() throws SQLException {
//
//		List<RoomStylePo> list = new ArrayList<RoomStylePo>();
//		RoomStylePo roomStyleVo = null;
//		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(GET_ALL);) {
//			System.out.println("連線成功");
//			try (ResultSet rs = pstmt.executeQuery()) {
//				while (rs.next()) {
////					list.add(UserVO);
////					ROOM_STYLE_ID, ROOM_NAME, ROOM_QUANTITY, BED_TYPE, ROOM_TYPE, ORDER_ROOM_PRICE, ROOM_DESCRIPTION
//					roomStyleVo = new RoomStylePo();
//					roomStyleVo.setRoomStyleId(rs.getInt("ROOM_STYLE_ID"));
//					roomStyleVo.setRoomName(rs.getString("ROOM_NAME"));
//					roomStyleVo.setRoomQuantity(rs.getInt("ROOM_QUANTITY"));
//					roomStyleVo.setBedType(rs.getString("BED_TYPE"));
//					roomStyleVo.setRoomType(rs.getString("ROOM_TYPE"));
//					roomStyleVo.setOrderRoomPrice(rs.getInt("ORDER_ROOM_PRICE"));
//					roomStyleVo.setRoomDescription(rs.getString("ROOM_DESCRIPTION"));
//					list.add(roomStyleVo);
//
//				}
//			}
//
//		}
//		return list;
//	}

}
