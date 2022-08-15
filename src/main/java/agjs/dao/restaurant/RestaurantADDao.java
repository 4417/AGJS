//package agjs.dao.restaurant;
//
//import java.util.*;
//import java.sql.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import agjs.bean.restaurant.RestaurantADVO;
//
//public class RestaurantADDao implements RestaurantADDao_interface{
//	
//	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
//		private static DataSource ds = null;
//		static {
//			try {
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
////AD_ID, REST_ID, AD_NAME, AD_PIC, AD_INTRO, AD_TIME
//		private static final String INSERT_STMT = 
//			"INSERT INTO rest_ad (restId,adName,adPic,adIntro,adTime) VALUES (?, ?, ?, ?, ?)";
//		private static final String GET_ALL_STMT = 
//			"SELECT adId,restId,adName,adPic,adIntro,adTime FROM rest_ad order by adId";
//		private static final String GET_ONE_STMT = 
//			"SELECT adId,restId,adName,adPic,adIntro,adTime FROM rest_ad where adId = ?";
//		private static final String DELETE = 
//			"DELETE FROM rest_ad where adId = ?";
//		private static final String UPDATE = 
//			"UPDATE rest_ad set adId=?, restId=?, adName=?, adPic=?, adIntro=?, adTime=? where adId = ?";
//
//		@Override
//		public void insert(RestaurantADVO restaurantADVO) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(INSERT_STMT);
//
//				pstmt.setInt(1, restaurantADVO.getRestId());
//				pstmt.setString(2, restaurantADVO.getAdName());
//				pstmt.setBytes(3, restaurantADVO.getAdPic());
//				pstmt.setString(4, restaurantADVO.getAdIntro());
//				pstmt.setDate(5, restaurantADVO.getAdTime());
//
//				pstmt.executeUpdate();
//
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}
//
//		@Override
//		public void update(RestaurantADVO restaurantADVO) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(UPDATE);
//
//				pstmt.setInt(1, restaurantADVO.getRestId());
//				pstmt.setString(2, restaurantADVO.getAdName());
//				pstmt.setBytes(3, restaurantADVO.getAdPic());
//				pstmt.setString(4, restaurantADVO.getAdIntro());
//				pstmt.setDate(5, restaurantADVO.getAdTime());
//				pstmt.setInt(6, restaurantADVO.getAdId());
//			
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}
//
//		@Override
//		public void delete(Integer adId) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(DELETE);
//
//				pstmt.setInt(1, adId);
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}
//
//		@Override
//		public RestaurantADVO findByPrimaryKey(Integer adId) {
//
//			RestaurantADVO restaurantADVO = null;
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ONE_STMT);
//
//				pstmt.setInt(1, adId);
//
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					// restaurantADVO 也稱為 Domain objects
//					restaurantADVO = new RestaurantADVO();
//					restaurantADVO.setAdId(rs.getInt("adid"));
//					restaurantADVO.setRestId(rs.getInt("restid"));
//					restaurantADVO.setAdName(rs.getString("adName"));
//					restaurantADVO.setAdPic(rs.getBytes("adPic"));
//					restaurantADVO.setAdIntro(rs.getString("adIntro"));
//					restaurantADVO.setAdTime(rs.getDate("adTime"));
//				}
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return restaurantADVO;
//		}
//
//		@Override
//		public List<RestaurantADVO> getAll() {
//			List<RestaurantADVO> list = new ArrayList<RestaurantADVO>();
//			RestaurantADVO restaurantADVO = null;
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ALL_STMT);
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					// restaurantADVO 也稱為 Domain objects
//					restaurantADVO = new RestaurantADVO();
//					restaurantADVO.setAdId(rs.getInt("adid"));
//					restaurantADVO.setRestId(rs.getInt("restid"));
//					restaurantADVO.setAdName(rs.getString("adName"));
//					restaurantADVO.setAdPic(rs.getBytes("adPic"));
//					restaurantADVO.setAdIntro(rs.getString("adIntro"));
//					restaurantADVO.setAdTime(rs.getDate("adTime"));
//					list.add(restaurantADVO); // Store the row in the list
//				}
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return list;
//		}
//}
//
