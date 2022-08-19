package agjs.dao.restaurant;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import agjs.bean.restaurant.RestaurantBookVO;

public class RestaurantBookDaoNew implements RestaurantBookDao_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO REST_BOOK (SALES_ORDER_HEADER_ID, REST_ID, REST_DATE, USER_NAME, REST_NUM, REST_TEL, REST_NOTE) VALUES (?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = 
//			"SELECT REST_BOOK_ID, SALES_ORDER_HEADER_ID, REST_ID, REST_DATE, USER_NAME, REST_NUM, REST_TEL, REST_NOTE FROM REST_BOOK order by REST_BOOK_ID";
//	private static final String GET_ONE_STMT = 
//			"SELECT REST_BOOK_ID, SALES_ORDER_HEADER_ID, REST_ID, REST_DATE, USER_NAME, REST_NUM, REST_TEL, REST_NOTE FROM REST_BOOK where REST_BOOK_ID = ?";
//
//	private static final String DELETE = 
//			"DELETE FROM REST_BOOK where REST_BOOK_ID = ?";
//	private static final String UPDATE = 
//			"UPDATE REST_BOOK set REST_BOOK_ID = ?, SALES_ORDER_HEADER_ID = ?, REST_ID = ?, REST_DATE = ?, USER_NAME = ?, REST_NUM = ?, REST_TEL = ?, REST_NOTE = ? where REST_BOOK_ID = ?";

	@Override
	public void insert(RestaurantBookVO restaurantBookVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, restaurantBookVO.getREST_BOOK_ID());
			pstmt.setInt(1, restaurantBookVO.getSALES_ORDER_HEADER_ID());
			pstmt.setInt(2, restaurantBookVO.getREST_ID());
			pstmt.setDate(3, restaurantBookVO.getREST_DATE());
			pstmt.setString(4, restaurantBookVO.getUSER_NAME());
			pstmt.setInt(5, restaurantBookVO.getREST_NUM());
			pstmt.setString(6, restaurantBookVO.getREST_TEL());
			pstmt.setString(7, restaurantBookVO.getREST_NOTE());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
//	public void update(RestaurantBookVO restaurantBookVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			
//			pstmt.setInt(1, restaurantBookVO.getOrderserialnumber());
//			pstmt.setInt(2, restaurantBookVO.getItemserialnumber());
//			pstmt.setInt(3, restaurantBookVO.getOrderdetailprice());
//			pstmt.setInt(4, restaurantBookVO.getOrderdetailquantity());
//			pstmt.setString(5, restaurantBookVO.getRefundreason());
//			pstmt.setInt(6, restaurantBookVO.getOrderdetailstatus());
//			pstmt.setInt(7, restaurantBookVO.getOrderdetailserialnumber());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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

//	@Override
//	public void delete(Integer orderdetailserialnumber) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, orderdetailserialnumber);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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

//	@Override
//	public RestaurantBookVO findByOrderDetailPK(Integer orderdetailserialnumber) {
//
//		RestaurantBookVO restaurantBookVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, orderdetailserialnumber);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// restaurantBookVO 也稱為 Domain objects
//				restaurantBookVO = new RestaurantBookVO();
//				restaurantBookVO.setOrderdetailserialnumber(rs.getInt("order_detail_serial_number"));
//				restaurantBookVO.setOrderserialnumber(rs.getInt("order_serial_number"));
//				restaurantBookVO.setItemserialnumber(rs.getInt("item_serial_number"));
//				restaurantBookVO.setOrderdetailprice(rs.getInt("order_detail_price"));
//				restaurantBookVO.setOrderdetailquantity(rs.getInt("order_detail_quantity"));
//				restaurantBookVO.setRefundreason(rs.getString("refund_reason"));
//				restaurantBookVO.setOrderdetailstatus(rs.getInt("order_detail_status"));
//
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return restaurantBookVO;
//	}

//	@Override
//	public List<RestaurantBookVO> getAllOrderDetail() {
//		List<RestaurantBookVO> list = new ArrayList<RestaurantBookVO>();
//		RestaurantBookVO restaurantBookVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ItemName_Poto_ByOrderDetail);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// restaurantBookVO 也稱為 Domain objects
//				restaurantBookVO = new RestaurantBookVO();
//				restaurantBookVO.setOrderdetailserialnumber(rs.getInt("order_detail_serial_number"));
//				restaurantBookVO.setOrderserialnumber(rs.getInt("order_serial_number"));
//				restaurantBookVO.setItemserialnumber(rs.getInt("item_serial_number"));
//				restaurantBookVO.setOrderdetailprice(rs.getInt("order_detail_price"));
//				restaurantBookVO.setOrderdetailquantity(rs.getInt("order_detail_quantity"));
//				restaurantBookVO.setRefundreason(rs.getString("refund_reason"));
//				restaurantBookVO.setOrderdetailstatus(rs.getInt("order_detail_status"));
//				restaurantBookVO.setItemname(rs.getString("item_name"));
//				restaurantBookVO.setItemphoto(rs.getBytes("item_photo"));
//				list.add(restaurantBookVO); // Store the row in the list
//				
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return list;
//	}	
}
