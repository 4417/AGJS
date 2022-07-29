package agjs.dao.customer;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import agjs.bean.customer.CustomerServiceMailVO;

public class CustomerServiceMailJNDIDao implements CustomerServiceMailDaoInterface{

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

	private static final String INSERT_STMT = "INSERT INTO cs_mail (faq_type_name,user_name,user_phone,user_email,content_text,create_date) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT faq_form_id,faq_type_name,user_name,user_phone,user_email,content_text,create_date FROM cs_mail order by faq_form_id";
//	private static final String GET_ONE_STMT = 
//		"SELECT faq_form_id,faq_type_name,user_name,user_phone,user_email,content_text,create_date FROM cs_mail where faq_form_id = ?";
	private static final String DELETE = "DELETE FROM cs_mail where faq_form_id = ?";
	
	@Override
	public void insert(CustomerServiceMailVO customerServiceMailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, customerServiceMailVO.getFaqTypeName());
			pstmt.setString(2, customerServiceMailVO.getUserName());
			pstmt.setString(3, customerServiceMailVO.getUserPhone());
			pstmt.setString(4, customerServiceMailVO.getUserEmail());
			pstmt.setString(5, customerServiceMailVO.getContentText());
			pstmt.setDate(6, customerServiceMailVO.getCreateDate());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	
	@Override
	public void delete(Integer faqformid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, faqformid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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


	@Override
	public List<CustomerServiceMailVO> getAll() {
		List<CustomerServiceMailVO> list = new ArrayList<CustomerServiceMailVO>();
		CustomerServiceMailVO customerServiceMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				customerServiceMailVO = new CustomerServiceMailVO();
				customerServiceMailVO.setFaqFormId(rs.getInt("faq_form_id"));
				customerServiceMailVO.setFaqTypeName(rs.getString("faq_type_name"));
				customerServiceMailVO.setUserName(rs.getString("user_name"));
				customerServiceMailVO.setUserPhone(rs.getString("user_phone"));
				customerServiceMailVO.setUserEmail(rs.getString("user_email"));
				customerServiceMailVO.setContentText(rs.getString("content_text"));
				customerServiceMailVO.setCreateDate(rs.getDate("create_date"));
				list.add(customerServiceMailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
}
