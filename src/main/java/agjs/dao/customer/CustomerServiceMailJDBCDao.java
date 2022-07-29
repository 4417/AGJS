package agjs.dao.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agjs.bean.customer.CustomerServiceMailVO;

public class CustomerServiceMailJDBCDao implements CustomerServiceMailDaoInterface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/AGJS?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, customerServiceMailVO.getFaqTypeName());
			pstmt.setString(2, customerServiceMailVO.getUserName());
			pstmt.setString(3, customerServiceMailVO.getUserPhone());
			pstmt.setString(4, customerServiceMailVO.getUserEmail());
			pstmt.setString(5, customerServiceMailVO.getContentText());
			pstmt.setDate(6, customerServiceMailVO.getCreateDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, faqformid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public List<CustomerServiceMailVO> getAll() {
		List<CustomerServiceMailVO> list = new ArrayList<CustomerServiceMailVO>();
		CustomerServiceMailVO customerServiceMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// customerServiceMailVO 也稱為 Domain objects
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	//測試用
//	public static void main(String[] args) {
//
//		CustomerServiceMailJDBCDao dao = new CustomerServiceMailJDBCDao();
//
//		// 新增
//		CustomerServiceMailVO customerServiceMailVO1 = new CustomerServiceMailVO();
//		customerServiceMailVO1.setFaqTypeName("訂房諮詢");
//		customerServiceMailVO1.setUserName("林建生");
//		customerServiceMailVO1.setUserPhone("08000920000");
//		customerServiceMailVO1.setUserEmail("Adam0800@gmail.com");
//		customerServiceMailVO1.setContentText("您好我希望訂6月23的房間");
//		customerServiceMailVO1.setCreateDate(java.sql.Date.valueOf("2022-06-22"));
//		dao.insert(customerServiceMailVO1);
//
//		// 刪除
//		dao.delete(5);
//
//		// 查詢
//		List<CustomerServiceMailVO> list = dao.getAll();
//		for (CustomerServiceMailVO aCustomerServiceMail : list) {
//			System.out.print(aCustomerServiceMail.getFaqFormId() + ",");
//			System.out.print(aCustomerServiceMail.getFaqTypeName() + ",");
//			System.out.print(aCustomerServiceMail.getUserName() + ",");
//			System.out.print(aCustomerServiceMail.getUserPhone() + ",");
//			System.out.print(aCustomerServiceMail.getUserEmail() + ",");
//			System.out.print(aCustomerServiceMail.getContentText() + ",");
//			System.out.print(aCustomerServiceMail.getCreateDate());
//			System.out.println();
//		}
//	}
}
