package agjs.dao.impl.announcement;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.sql.Select;
import org.springframework.stereotype.Repository;

import agjs.bean.announcement.AnnouncementCountVo;
import agjs.bean.announcement.AnnouncementFilterVo;
import agjs.bean.announcement.AnnouncementPo;
import agjs.bean.journey.JourneyPo;
import agjs.dao.announcement.AnnouncementDao;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {
	private DataSource dataSource;

	@PersistenceContext
	private Session session;

	public AnnouncementDaoImpl() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/AGJS");
	}

	@Override
	public List<AnnouncementPo> searchKeyword(String keyword) {

//		String hql = "from AnnouncementPo where anmTitle like :keyword or anmContent like :keyword";

		return session.createQuery("from AnnouncementPo where anmTitle like :keyword or anmContent like :keyword", AnnouncementPo.class).setParameter("keyword", "%" + keyword + "%").list();
	}

	@Override
	public AnnouncementPo insertAnm(AnnouncementPo announcementPo) {
		
		if (announcementPo != null) {
			Serializable pk = session.save(announcementPo);
			System.out.println(pk);
		}
		return announcementPo;
		
//		String sql = "insert into ANNOUNCEMENT(ADMINISTRATOR_ID, ANM_ORDER_ID, ANM_STATUS, ANM_TITLE, ANM_CONTENT, ANM_TYPE_ID, ANM_START_DATE, ANM_END_DATE) values(1, ?, ?, ?, ?, ?, ?, ?);";
//
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//			preparedStatement.setInt(1, announcementPo.getAnmOrderId());
//			preparedStatement.setString(2, announcementPo.getAnmStatus());
//			preparedStatement.setString(3, announcementPo.getAnmTitle());
//			preparedStatement.setString(4, announcementPo.getAnmContent());
//			preparedStatement.setInt(5, announcementPo.getAnmTypeId());
//			preparedStatement.setDate(6, announcementPo.getAnmStartDate());
//			preparedStatement.setDate(7, announcementPo.getAnmEndDate());
//			int count = preparedStatement.executeUpdate();
//
//			System.out.println(count + " row(s) insert.");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return announcementPo;
	}

	@Override
	public AnnouncementPo updateAnm(AnnouncementPo announcementPo) {
		
		if(announcementPo != null && announcementPo.getAnmId() != null) {
			AnnouncementPo temp = session.get(AnnouncementPo.class, announcementPo.getAnmId());
			if(temp != null) {
				announcementPo.setAdministratorId(1);
				return (AnnouncementPo) session.merge(announcementPo);
			}
		}
		
		return null;
		
//		String sql = "update ANNOUNCEMENT "
//				+ "set ANM_ORDER_ID = ?, ANM_TITLE = ?, ANM_CONTENT = ?, ANM_TYPE_ID = ?, ANM_START_DATE = ?, ANM_END_DATE = ?, ANM_STATUS = ? "
//				+ "where ANM_ID = ?";
//
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//			preparedStatement.setInt(1, announcementPo.getAnmOrderId());
//			preparedStatement.setString(2, announcementPo.getAnmTitle());
//			preparedStatement.setString(3, announcementPo.getAnmContent());
//			preparedStatement.setInt(4, announcementPo.getAnmTypeId());
//			preparedStatement.setObject(5, announcementPo.getAnmStartDate());
//			preparedStatement.setObject(6, announcementPo.getAnmEndDate());
//			preparedStatement.setString(7, announcementPo.getAnmStatus());
//			preparedStatement.setInt(8, announcementPo.getAnmId());
//			int count = preparedStatement.executeUpdate();
//
//			System.out.println(count + " row(s) update.");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return announcementPo;
	}

	@Override
	public List<AnnouncementPo> delete(AnnouncementPo announcementPo) {
		if(announcementPo.getAnmId() != null) {
			AnnouncementPo temp = session.get(AnnouncementPo.class, announcementPo.getAnmId());
			session.delete(temp);
		}
		
		return null;
		
//		String sql = "delete from ANNOUNCEMENT where ANM_ID = ?;";
//		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
//
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//			preparedStatement.setInt(1, announcementPo.getAnmId());
//
//			int count = preparedStatement.executeUpdate();
//
//			System.out.println(count + " row(s) delete.");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> getAnmInfo(AnnouncementPo announcementPo) {
		
		Query<AnnouncementPo> query = session.createQuery("from AnnouncementPo where anmTitle = :title and anmTypeId = :type and anmStartDate = :startDate", AnnouncementPo.class);
		query = query.setParameter("title", announcementPo.getAnmTitle());
		query = query.setParameter("type", announcementPo.getAnmTypeId());
		query = query.setParameter("startDate", announcementPo.getAnmStartDate());
		
		List<AnnouncementPo> anmPoList = query.list();
		
		return anmPoList;
		
//		String sql = "select ANM_ID, ANM_ORDER_ID, ANM_TITLE, ANM_CONTENT, ANNOUNCEMENT.ANM_TYPE_ID, ANM_START_DATE, ANM_END_DATE, ANM_STATUS "
//				+ "from ANNOUNCEMENT " + "join ANNOUNCEMENT_TYPE "
//				+ "on ANNOUNCEMENT.ANM_TYPE_ID = ANNOUNCEMENT_TYPE.ANM_TYPE_ID "
//				+ "where ANM_TITLE = ? and ANNOUNCEMENT.ANM_TYPE_ID = ? and ANM_START_DATE = ?;";
//		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//			preparedStatement.setString(1, announcementPo.getAnmTitle());
//			preparedStatement.setInt(2, announcementPo.getAnmTypeId());
//			preparedStatement.setDate(3, announcementPo.getAnmStartDate());
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			int count = 0;
//
//			while (resultSet.next()) {
//				announcementPo = new AnnouncementPo();
//				announcementPo.setAnmId(resultSet.getInt(1));
//				announcementPo.setAnmOrderId(resultSet.getInt(2));
//				announcementPo.setAnmTitle(resultSet.getString(3));
//				announcementPo.setAnmContent(resultSet.getString(4));
//				announcementPo.setAnmTypeId(resultSet.getInt(5));
//				announcementPo.setAnmStartDate(resultSet.getDate(6));
//				announcementPo.setAnmEndDate(resultSet.getDate(7));
//				announcementPo.setAnmStatus(resultSet.getString(8));
//				anmPoList.add(announcementPo);
//				count++;
//			}
//			System.out.println(count + " row(s) query.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> allAnm() {
		
		return session.createQuery("from AnnouncementPo order by anmId desc", AnnouncementPo.class).list();
		
//		String sql = "select ANM_ID, ANM_TITLE, ANM_CONTENT, ANNOUNCEMENT.ANM_TYPE_ID, ANM_START_DATE, ANM_END_DATE, ANM_STATUS "
//				+ "from ANNOUNCEMENT " + "join ANNOUNCEMENT_TYPE "
//				+ "on ANNOUNCEMENT.ANM_TYPE_ID = ANNOUNCEMENT_TYPE.ANM_TYPE_ID " + "order by ANM_ID desc;";
//		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
//		AnnouncementPo announcementPo = new AnnouncementPo();
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			int count = 0;
//
//			while (resultSet.next()) {
//				announcementPo = new AnnouncementPo();
//				announcementPo.setAnmId(resultSet.getInt(1));
//				announcementPo.setAnmTitle(resultSet.getString(2));
//				announcementPo.setAnmContent(resultSet.getString(3));
//				announcementPo.setAnmTypeId(resultSet.getInt(4));
//				announcementPo.setAnmStartDate(resultSet.getDate(5));
//				announcementPo.setAnmEndDate(resultSet.getDate(6));
//				announcementPo.setAnmStatus(resultSet.getString(7));
//				anmPoList.add(announcementPo);
//				count++;
//			}
//			System.out.println(count + " row(s) query.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> filter(AnnouncementFilterVo announcementFilterVo) {

		System.out.println("-------------Dao Start-------------");
		String hql = "from AnnouncementPo where 1=1";
		if (announcementFilterVo.getAnmStartDate() != null) {
			hql += "and anmStartDate >= :startDate and anmStartDate <= current_date()";
		}
		if (announcementFilterVo.getAnmStatus() != null) {
			hql += "and anmStatus in (:anmStatus)";
		}
		if (announcementFilterVo.getAnmTypeId() != null) {
			hql += "and anmTypeId in :type";
		}

		Query<AnnouncementPo> query = session.createQuery(hql, AnnouncementPo.class);
		
		if (announcementFilterVo.getAnmStartDate() != null) {
			query = query.setParameter("startDate", announcementFilterVo.getAnmStartDate());
		}
		if (announcementFilterVo.getAnmStatus() != null) {
			List<String> anmStatusList = new ArrayList<>();
			for (String anmStatus : announcementFilterVo.getAnmStatus()) {
				anmStatusList.add(anmStatus);
			}
			query = query.setParameter("anmStatus", anmStatusList);
		}
		if (announcementFilterVo.getAnmTypeId() != null) {
			List<Integer> anmTypeIdList = new ArrayList<>();
			for (Integer anmTypeId : announcementFilterVo.getAnmTypeId()) {
				anmTypeIdList.add(anmTypeId);
			}
			query = query.setParameter("type", anmTypeIdList);
		}
		List<AnnouncementPo> anmPoList = query.list();
		System.out.println("-------------Dao End-------------");
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> publishedAnm(AnnouncementCountVo announcementCountVo) {
		System.out.println("-------------Dao Start-------------");
		Query<AnnouncementPo> query = session.createQuery("from AnnouncementPo where anmStatus = '已上架' order by anmOrderId asc, anmStartDate desc, anmId desc", AnnouncementPo.class);
		query.setFirstResult(announcementCountVo.getCount() * 10);			
		query.setMaxResults(10);
		List<AnnouncementPo> anmPoList = query.list();
		System.out.println("-------------Dao End-------------");
		return anmPoList;
	}

}
