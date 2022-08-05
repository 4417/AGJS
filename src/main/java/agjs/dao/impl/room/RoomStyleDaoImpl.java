package agjs.dao.impl.room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<RoomStylePo> list = new ArrayList<RoomStylePo>();
		try {

			Query<RoomStylePo> query = session.createQuery("FROM RoomStylePo", RoomStylePo.class);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 */
	@Override
	public void delete(Integer roomStyleId) {

		// 從RoomStylePo這張表格，去搜尋roomStyleId
		RoomStylePo roomStylePo = session.get(RoomStylePo.class, roomStyleId);
		// 再將相對應的roomStyleId刪除
		session.delete(roomStylePo);

	}
	//修改
	@Override
	public RoomStylePo update(Integer id,String roomName, String bedType, Integer orderRoomPrice, String roomDescription,
			Integer roomQuantity, String roomType) {
		//如果id不是空值(id確認是存在)
		if(id!=null) {
			//找尋在RoomStylePo裡面的此id
			RoomStylePo temp = session.get(RoomStylePo.class, id);
			//有找到此id的話，就將資料塞進去
			if(temp!=null) {
				temp.setRoomName(roomName);
				temp.setBedType(bedType);
				temp.setOrderRoomPrice(orderRoomPrice);
				temp.setRoomDescription(roomDescription);
				temp.setRoomQuantity(roomQuantity);
				temp.setRoomType(roomType);
				return temp;
			}
		}
		return null;
	}

}
