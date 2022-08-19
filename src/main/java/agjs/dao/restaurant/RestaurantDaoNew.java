package agjs.dao.restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import agjs.bean.restaurant.RestaurantVo;

public class RestaurantDaoNew implements RestaurantDao_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String UPDATE = "UPDATE REST_LIST set REST_NAME=?, REST_PIC=?, REST_FLOOR=?, REST_TIME=?, REST_INTRO=?, INTRO_TIME=? where REST_ID = ?";
	private static final String INSERT_STMT = "INSERT INTO REST_LIST (REST_NAME, REST_PIC, REST_FLOOR, REST_TIME, REST_INTRO, INTRO_TIME) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM REST_LIST where REST_ID = ?";

	@Override
	public void update(RestaurantVo restaurantVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restaurantVo.getREST_NAME());
			pstmt.setBlob(2, restaurantVo.getREST_PIC());
			pstmt.setString(3, restaurantVo.getREST_FLOOR());
			pstmt.setString(4, restaurantVo.getREST_TIME());
			pstmt.setString(5, restaurantVo.getREST_INTRO());
			pstmt.setDate(6, restaurantVo.getINTRO_TIME());
			pstmt.setInt(7, restaurantVo.getREST_ID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//	@Override
//	public void insert(RestaurantVo restaurantVo) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
////				pstmt.setInt(1, restaurantVo.getREST_ID());
//			pstmt.setString(1, restaurantVo.getREST_NAME());
//			pstmt.setBlob(2, restaurantVo.getREST_PIC());
//			pstmt.setString(3, restaurantVo.getREST_FLOOR());
//			pstmt.setString(4, restaurantVo.getREST_TIME());
//			pstmt.setString(5, restaurantVo.getREST_INTRO());
//			pstmt.setDate(6, restaurantVo.getINTRO_TIME());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void delete(Integer REST_ID) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, REST_ID);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

}
