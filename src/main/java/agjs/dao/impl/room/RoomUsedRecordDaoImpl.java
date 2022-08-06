package agjs.dao.impl.room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.dao.room.RoomUsedRecordDao;

@Repository
public class RoomUsedRecordDaoImpl implements RoomUsedRecordDao<RoomUsedRecordPo> {
	@PersistenceContext
	private Session session;

	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<RoomUsedRecordPo> getAll() {
		List<RoomUsedRecordPo> list = new ArrayList<RoomUsedRecordPo>();
		try {
			Query<RoomUsedRecordPo> query = session.createQuery("FROM RoomUsedRecordPo", RoomUsedRecordPo.class);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
