package agjs.dao.impl.announcement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.sql.Select;
import org.springframework.stereotype.Repository;

import agjs.bean.announcement.AnnouncementPo;
import agjs.dao.announcement.AnnouncementDao;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {
	private DataSource dataSource;

	public AnnouncementDaoImpl() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/AGJS");
	}

	@Override
	public List<AnnouncementPo> selectKeyword(String keyword) {
		String sql = "select * from ANNOUNCEMENT where ANM_TITLE or ANM_CONTENT like ?;";
		AnnouncementPo announcementPo;
		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			System.out.println("here is Dao");
			preparedStatement.setString(1, "%" + keyword + "%");
			ResultSet resultSet = preparedStatement.executeQuery();

			int count = 0;

			while (resultSet.next()) {
				announcementPo = new AnnouncementPo();
				announcementPo.setAnmId(resultSet.getInt(1));
				announcementPo.setAdministratorId(resultSet.getInt(2));
				announcementPo.setAnmOrderId(resultSet.getInt(3));
				announcementPo.setAnmStatus(resultSet.getString(4));
				announcementPo.setAnmTitle(resultSet.getString(5));
				announcementPo.setAnmContent(resultSet.getString(6));
				announcementPo.setAnmTypeId(resultSet.getInt(7));
				announcementPo.setAnmStartDate(resultSet.getDate(8));
				announcementPo.setAnmEndDate(resultSet.getDate(9));

				anmPoList.add(announcementPo);
				count++;
			}

			System.out.println(count + " row(s) query.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> selectStartDate(AnnouncementPo announcementPo) {
//		String sql = "select * from ANNOUNCEMENT where ANM_START_DATE <= ?;";
//		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
//
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//			preparedStatement.setObject(1, announcementPo.getAnmStartDate());
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			int count = 0;
//
//			while (resultSet.next()) {
//				
//				announcementPo.setAnmId(resultSet.getInt(1));
//				announcementPo.setAdministratorId(resultSet.getInt(2));
//				announcementPo.setAnmOrderId(resultSet.getInt(3));
//				announcementPo.setAnmStatusId(resultSet.getInt(4));
//				announcementPo.setAnmTitle(resultSet.getString(5));
//				announcementPo.setAnmContent(resultSet.getString(6));
//				announcementPo.setAnmTypeId(resultSet.getInt(7));
//				announcementPo.setAnmStartDate(resultSet.getDate(8));
//				announcementPo.setAnmEndDate(resultSet.getDate(9));
//
//				anmPoList.add(announcementPo);
//				count++;
//			}
//
//			System.out.println(count + " row(s) query.");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public List<AnnouncementPo> selectStauts(AnnouncementPo announcementPo) {
		String sql = "select * from ANNOUNCEMENT where ANM_STATUS = ?;";
		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setObject(1, announcementPo.getAnmOrderId());
			ResultSet resultSet = preparedStatement.executeQuery();

			int count = 0;

			while (resultSet.next()) {
				announcementPo.setAnmId(resultSet.getInt(1));
				announcementPo.setAdministratorId(resultSet.getInt(2));
				announcementPo.setAnmOrderId(resultSet.getInt(3));
				announcementPo.setAnmStatus(resultSet.getString(4));
				announcementPo.setAnmTitle(resultSet.getString(5));
				announcementPo.setAnmContent(resultSet.getString(6));
				announcementPo.setAnmTypeId(resultSet.getInt(7));
				announcementPo.setAnmStartDate(resultSet.getDate(8));
				announcementPo.setAnmEndDate(resultSet.getDate(9));

				anmPoList.add(announcementPo);
				count++;
			}

			System.out.println(count + " row(s) query.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AnnouncementPo> selectType(AnnouncementPo announcementPo) {
//		String sql = "select * from ANNOUNCEMENT where ANM_TYPE_ID = ?;";
//		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
//
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//			preparedStatement.setInt(1, announcementPo.getAnmTypeId());
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			int count = 0;
//
//			while (resultSet.next()) {
//				announcementPo.setAnmId(resultSet.getInt(1));
//				announcementPo.setAdministratorId(resultSet.getInt(2));
//				announcementPo.setAnmOrderId(resultSet.getInt(3));
//				announcementPo.setAnmStatusId(resultSet.getInt(4));
//				announcementPo.setAnmTitle(resultSet.getString(5));
//				announcementPo.setAnmContent(resultSet.getString(6));
//				announcementPo.setAnmTypeId(resultSet.getInt(7));
//				announcementPo.setAnmStartDate(resultSet.getDate(8));
//				announcementPo.setAnmEndDate(resultSet.getDate(9));
//
//				anmPoList.add(announcementPo);
//				count++;
//			}
//
//			System.out.println(count + " row(s) query.");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public AnnouncementPo insertAnm(AnnouncementPo announcementPo) {
		String sql = "insert into ANNOUNCEMENT(ADMINISTRATOR_ID, ANM_ORDER_ID, ANM_STATUS, ANM_TITLE, ANM_CONTENT, ANM_TYPE_ID, ANM_START_DATE, ANM_END_DATE) values(1, ?, ?, ?, ?, ?, ?, ?);";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, announcementPo.getAnmOrderId());
			preparedStatement.setString(2, announcementPo.getAnmStatus());
			preparedStatement.setString(3, announcementPo.getAnmTitle());
			preparedStatement.setString(4, announcementPo.getAnmContent());
			preparedStatement.setInt(5, announcementPo.getAnmTypeId());
			preparedStatement.setDate(6, announcementPo.getAnmStartDate());
			preparedStatement.setDate(7, announcementPo.getAnmEndDate());
			int count = preparedStatement.executeUpdate();

			System.out.println(count + " row(s) insert.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return announcementPo;
	}

	@Override
	public AnnouncementPo updateAnm(Integer anmId) {
		String sql = "update ANNOUNCEMENT " + "set " + "ANM_ORDER_ID = ?, " + "ANM_TITLE = ?, " + "ANM_CONTENT = ?, "
				+ "ANM_TYPE_ID = ?, " + "ANM_START_DATE = ?, " + "ANM_END_DATE = ? " + "where ANM_ID = ?";
		AnnouncementPo announcementPo = new AnnouncementPo();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, announcementPo.getAnmOrderId());
			preparedStatement.setString(2, announcementPo.getAnmTitle());
			preparedStatement.setString(3, announcementPo.getAnmContent());
			preparedStatement.setInt(4, announcementPo.getAnmTypeId());
			preparedStatement.setObject(5, announcementPo.getAnmStartDate());
			preparedStatement.setObject(6, announcementPo.getAnmEndDate());
			preparedStatement.setInt(7, anmId);
			int count = preparedStatement.executeUpdate();

			System.out.println(count + " row(s) update.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return announcementPo;
	}

	@Override
	public List<AnnouncementPo> delete(AnnouncementPo announcementPo) {
		String sql = "delete from ANNOUNCEMENT where ANM_ID = ?;";
		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, announcementPo.getAnmId());

			int count = preparedStatement.executeUpdate();

			System.out.println(count + " row(s) delete.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> getAnmInfo(AnnouncementPo announcementPo) {
		String sql = "select ANM_ID, ANM_TITLE, ANM_CONTENT, ANNOUNCEMENT.ANM_TYPE_ID, ANM_START_DATE, ANM_END_DATE, ANM_STATUS "
				+ "from ANNOUNCEMENT " 
				+ "join ANNOUNCEMENT_TYPE "
				+ "on ANNOUNCEMENT.ANM_TYPE_ID = ANNOUNCEMENT_TYPE.ANM_TYPE_ID "
				+ "where ANM_TITLE = ? and ANNOUNCEMENT.ANM_TYPE_ID = ? and ANM_START_DATE = ?;";
		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, announcementPo.getAnmTitle());
			preparedStatement.setInt(2, announcementPo.getAnmTypeId());
			preparedStatement.setDate(3, announcementPo.getAnmStartDate());
			ResultSet resultSet = preparedStatement.executeQuery();

			int count = 0;

			while (resultSet.next()) {
				announcementPo = new AnnouncementPo();
				announcementPo.setAnmId(resultSet.getInt(1));
				announcementPo.setAnmTitle(resultSet.getString(2));
				announcementPo.setAnmContent(resultSet.getString(3));
				announcementPo.setAnmTypeId(resultSet.getInt(4));
				announcementPo.setAnmStartDate(resultSet.getDate(5));
				announcementPo.setAnmEndDate(resultSet.getDate(6));
				announcementPo.setAnmStatus(resultSet.getString(7));
				anmPoList.add(announcementPo);
				count++;
			}
			System.out.println(count + " row(s) query.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> allAnm() {
		String sql = "select ANM_ID, ANM_TITLE, ANM_CONTENT, ANNOUNCEMENT.ANM_TYPE_ID, ANM_START_DATE, ANM_END_DATE, ANM_STATUS "
				+ "from ANNOUNCEMENT " 
				+ "join ANNOUNCEMENT_TYPE "
				+ "on ANNOUNCEMENT.ANM_TYPE_ID = ANNOUNCEMENT_TYPE.ANM_TYPE_ID "
				+ "order by ANM_ID;";
		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
		AnnouncementPo announcementPo = new AnnouncementPo();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			int count = 0;

			while (resultSet.next()) {
				announcementPo = new AnnouncementPo();
				announcementPo.setAnmId(resultSet.getInt(1));
				announcementPo.setAnmTitle(resultSet.getString(2));
				announcementPo.setAnmContent(resultSet.getString(3));
				announcementPo.setAnmTypeId(resultSet.getInt(4));
				announcementPo.setAnmStartDate(resultSet.getDate(5));
				announcementPo.setAnmEndDate(resultSet.getDate(6));
				announcementPo.setAnmStatus(resultSet.getString(7));
				anmPoList.add(announcementPo);
				count++;
			}
			System.out.println(count + " row(s) query.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anmPoList;
	}
}
