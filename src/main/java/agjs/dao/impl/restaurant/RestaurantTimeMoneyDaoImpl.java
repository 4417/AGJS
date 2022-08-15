//package agjs.dao.impl.restaurant;
//
//import java.util.List;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.persistence.PersistenceContext;
//import javax.sql.DataSource;
//
//import org.hibernate.Session;
//import org.springframework.stereotype.Repository;
//
//import agjs.bean.restaurant.RestaurantTimeMoneyPo;
//import agjs.dao.restaurant.RestaurantTimeMoneyDao;
//
//@Repository
//public class RestaurantTimeMoneyDaoImpl implements RestaurantTimeMoneyDao {
//	
//	@PersistenceContext
//	private Session session;
//	
//	private DataSource dataSource;
//
//	public RestaurantTimeMoneyDaoImpl() throws NamingException {
//		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/AGJS");
//	}
//	
//	
//	@Override
//	public List<RestaurantTimeMoneyPo> allTMoney() {
//		return null;
//	};
//	
//	@Override
//	public List<RestaurantTimeMoneyPo> selectType(RestaurantTimeMoneyPo restaurantTimeMoneyPo){
//		return null;
//	};
//	@Override
//	public RestaurantTimeMoneyPo insertMoney(RestaurantTimeMoneyPo restaurantTimeMoneyPo){
////		String sql = "insert into REST_BOOK(REST_BOOK_ID, SALES_ORDER_HEADER_ID, REST_ID, REST_DATE, USER_NAME, REST_NUM, REST_TEL, REST_NOTE) values(1, ?, ?, ?, ?, ?, ?, ?);";
////
////		try (Connection connection = dataSource.getConnection();
////				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
////
////			preparedStatement.setInt(1, restaurantBookPo.getSalesOrderHeaderId());
////			preparedStatement.setInt(2, restaurantBookPo.getRestId());
////			preparedStatement.setDate(3, restaurantBookPo.getRestDate());
////			preparedStatement.setString(4, restaurantBookPo.getUserName());
////			preparedStatement.setInt(5, restaurantBookPo.getRestNum());
////			preparedStatement.setString(6, restaurantBookPo.getRestTel());
////			preparedStatement.setString(7, restaurantBookPo.getRestNote());
////			int count = preparedStatement.executeUpdate();
////
////			System.out.println(count + " row(s) insert.");
////
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//		return restaurantTimeMoneyPo;
//	};
//	
//	@Override
//	public RestaurantTimeMoneyPo updateMoney(Integer moneyId) {
//		return null;
//	};
//	
//	@Override
//	public List<RestaurantTimeMoneyPo> getTimeInfo(RestaurantTimeMoneyPo restaurantTimeMoneyPo){
////		String sql = "select ANM_ID, ANM_TITLE, ANM_CONTENT, ANNOUNCEMENT.ANM_TYPE_ID, ANM_START_DATE, ANM_END_DATE, ANM_STATUS "
////				+ "from ANNOUNCEMENT " 
////				+ "join ANNOUNCEMENT_TYPE "
////				+ "on ANNOUNCEMENT.ANM_TYPE_ID = ANNOUNCEMENT_TYPE.ANM_TYPE_ID "
////				+ "where ANM_TITLE = ? and ANNOUNCEMENT.ANM_TYPE_ID = ? and ANM_START_DATE = ?;";
////		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
////		try (Connection connection = dataSource.getConnection();
////				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
////
////			preparedStatement.setString(1, announcementPo.getAnmTitle());
////			preparedStatement.setInt(2, announcementPo.getAnmTypeId());
////			preparedStatement.setDate(3, announcementPo.getAnmStartDate());
////			ResultSet resultSet = preparedStatement.executeQuery();
////
////			int count = 0;
////
////			while (resultSet.next()) {
////				announcementPo = new AnnouncementPo();
////				announcementPo.setAnmId(resultSet.getInt(1));
////				announcementPo.setAnmTitle(resultSet.getString(2));
////				announcementPo.setAnmContent(resultSet.getString(3));
////				announcementPo.setAnmTypeId(resultSet.getInt(4));
////				announcementPo.setAnmStartDate(resultSet.getDate(5));
////				announcementPo.setAnmEndDate(resultSet.getDate(6));
////				announcementPo.setAnmStatus(resultSet.getString(7));
////				anmPoList.add(announcementPo);
////				count++;
////			}
////			System.out.println(count + " row(s) query.");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return anmPoList;
//		return null;
//	};
//}
