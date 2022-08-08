package agjs.dao.impl.room;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import agjs.bean.room.RoomStylePo;
import agjs.dao.room.RoomStyleDao;

@Repository
public class RoomStyleDaoImpl implements RoomStyleDao<RoomStylePo> {
	@PersistenceContext
	private Session session;

	/**
	 * 取得全部
	 * 
	 * @throws SQLException
	 */
	// @Transactional
	public List<RoomStylePo> getAll() {
		Query<RoomStylePo> query = session.createQuery("FROM RoomStylePo", RoomStylePo.class);
		List<RoomStylePo> list = query.list();
		return list;
	}

	/**
	 * 新增RoomStyle資料，並回傳id
	 **/
	@Override
	public Integer add(RoomStylePo roomStylePo) {
		session.save(roomStylePo);
		return roomStylePo.getRoomStyleId();
	}

	/**
	 * 取得RoomStyleId
	 **/
	@Override
	public RoomStylePo getId(Integer id) {
		RoomStylePo getId = session.get(RoomStylePo.class, id);
		return getId;
	}
	/**
	 * 刪除
	 * */
	@Override
	public void delete(Integer roomStyleId) {
		
		// 從RoomStylePo這張表格，去搜尋roomStyleId
		RoomStylePo roomStylePo = session.get(RoomStylePo.class, roomStyleId);
		// 再將相對應的roomStyleId刪除
		session.delete(roomStylePo);

	}

}
