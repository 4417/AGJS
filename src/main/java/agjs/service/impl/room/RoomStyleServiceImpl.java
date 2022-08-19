package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.room.RoomInformationFacilitiesId;
import agjs.bean.room.RoomInformationFacilitiesPo;
import agjs.bean.room.RoomPhotoPo;
import agjs.bean.room.RoomStylePo;
import agjs.dao.CoreDao;
import agjs.dao.room.RoomInformationFacilitiesDao;
import agjs.dao.room.RoomPhotoDao;
import agjs.dao.room.RoomStyleDao;
import agjs.service.room.RoomStyleService;

@Service
public class RoomStyleServiceImpl implements RoomStyleService<RoomStylePo> {
	@Autowired
	private RoomStyleDao<RoomStylePo> roomStyleDao;
	@Autowired
	private RoomInformationFacilitiesDao roomInformationFacilitiesDao;
	@Autowired
	private RoomPhotoDao roomPhotoDao;
	

	@Override
	@Transactional
	public List<RoomStylePo> getAll() {
		List<RoomStylePo> list = new ArrayList<RoomStylePo>();
		try {
			list = roomStyleDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	@Override
	public Integer addRoomStyle(RoomStylePo roomStylePo, List<Integer> roomFacilitiesIdList) {
		// 新增主要table
		Integer id = roomStyleDao.add(roomStylePo);
		System.out.println(id);
		// 新增相關FK的檔案(兩個表格的id，雙主鍵)
		for (Integer facilitiesId : roomFacilitiesIdList) {
			RoomInformationFacilitiesId roomInformationFacilitiesId = new RoomInformationFacilitiesId();
			roomInformationFacilitiesId.setRoomFacilitiesId(facilitiesId);
			roomInformationFacilitiesId.setRoomStyleId(id);

			RoomInformationFacilitiesPo roomInformationFacilitiesPo = new RoomInformationFacilitiesPo();
			roomInformationFacilitiesPo.setId(roomInformationFacilitiesId);

			roomInformationFacilitiesDao.add(roomInformationFacilitiesPo);
		}
		return id;
	}

	// 取得RoomStylePo中的Id
	@Override
	public RoomStylePo getById(Integer id) {
		return roomStyleDao.getId(id);
	}

	// 刪除ROOM_INFORMATION_FACILITIES與ROOM_STYLE資料
	@Override
	@Transactional
	public void delete(Integer[] roomStyleIds) {

		// 預設結果為刪除失敗
		if (roomStyleIds != null) {
			for (Integer id : roomStyleIds) {
				List<RoomInformationFacilitiesPo> list = roomInformationFacilitiesDao.findByRoomStyleId(id);
				List<RoomPhotoPo> photoList = roomPhotoDao.selectByRoomStyleId(id);
				for (RoomInformationFacilitiesPo po : list) {
					roomInformationFacilitiesDao.delete(po);
				}
				for (RoomPhotoPo photoPo : photoList) {
					roomPhotoDao.deleteById(photoPo);
				}
				roomStyleDao.delete(id);
			}
		}
	}

	// 尋找roomStyleId
	@Override
	public List<RoomInformationFacilitiesPo> findFacilitiesByRoomStyleId(Integer roomStyleId) {
		List<RoomInformationFacilitiesPo> list = roomInformationFacilitiesDao.findByRoomStyleId(roomStyleId);
		return list;
	}
	// 修改roomStylePo跟roomFacilitiesId

	@Override
	@Transactional
	public RoomStylePo updateRoomStyle(RoomStylePo roomStylePo, List<Integer> roomFacilitiesIdList) {
		RoomStylePo result = null;
		Integer id = roomStylePo.getRoomStyleId();

		// 先刪除已有的設備id
		if (id != null) {
			// 先找出在RoomInformationFacilitiesPo的id
			List<RoomInformationFacilitiesPo> findIds = roomInformationFacilitiesDao.findByRoomStyleId(id);
			System.out.println(findIds);
			// 將找到的id刪除
			for (RoomInformationFacilitiesPo po : findIds) {
				roomInformationFacilitiesDao.delete(po);
			}
		}
		// 新增複合主鍵table
		// 新增主要table

		if (id != null) {
			result = roomStyleDao.update(roomStylePo.getRoomStyleId(), roomStylePo.getRoomName(),
					roomStylePo.getBedType(), roomStylePo.getOrderRoomPrice(), roomStylePo.getRoomDescription(),
					roomStylePo.getRoomQuantity(), roomStylePo.getRoomType());
		}
		for (Integer facilitiesId : roomFacilitiesIdList) {
			RoomInformationFacilitiesId roomInformationFacilitiesId = new RoomInformationFacilitiesId();
			roomInformationFacilitiesId.setRoomFacilitiesId(facilitiesId);
			roomInformationFacilitiesId.setRoomStyleId(id);

			RoomInformationFacilitiesPo roomInformationFacilitiesPo = new RoomInformationFacilitiesPo();
			roomInformationFacilitiesPo.setId(roomInformationFacilitiesId);

			roomInformationFacilitiesDao.add(roomInformationFacilitiesPo);
		}
		return result;

	}

	@Override
	public void addPhoto(RoomPhotoPo photo) {
		roomPhotoDao.insert(photo);
	}

	@Override
	public List<RoomPhotoPo> getPhotosByRoomstyleId(Integer roomStyleId) {
		List<RoomPhotoPo> list = roomPhotoDao.selectByRoomStyleId(roomStyleId);
		return list;
	}

	@Override
	public RoomPhotoPo getPhotosByRroomPhotoId(Integer roomPhotoId) {
		RoomPhotoPo photo = roomPhotoDao.select(roomPhotoId);
		return photo;
	}

}
