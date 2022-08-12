package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import agjs.bean.room.RoomCardVo;
import agjs.bean.room.RoomPhotoPo;
import agjs.bean.room.RoomStatusVo;
import agjs.bean.room.RoomStylePo;
import agjs.dao.room.RoomPhotoDao;
import agjs.dao.room.RoomStatusDao;
import agjs.dao.room.RoomStyleDao;
import agjs.service.room.RoomStatusService;

@Service
public class RoomStatusServiceImpl implements RoomStatusService {

	@Autowired
	private RoomStatusDao roomStatusDao;
	@Autowired
	private RoomStyleDao<RoomStylePo> roomStyleDao;
	@Autowired
	private RoomPhotoDao roomPhotoDao;

	// 日期區間 搜尋空房id
	@Override
	@Transactional(readOnly = true)
	public RoomStatusVo searchEmptyRoomByDateRange(RoomStatusVo roomStatusVo) {

		List<Integer> result = roomStatusDao.selectEmptyRoomByDateRange(roomStatusVo.getStartDate(),
				roomStatusVo.getEndDate());
		RoomStatusVo vo = new RoomStatusVo();

		if (result.isEmpty()) {
			return null;
		} else {
//			List<String> idStrings = new ArrayList<String>();
			Set<String> idStrings = new HashSet<String>();
			for (Integer id : result) {
				System.out.println(id);
				idStrings.add(id.toString());
			}
			vo.setEmptyRoomStyleId(idStrings);
			return vo;
		}
	}

	// 房型style id 搜尋 RoomCardVo
	@Override
	@Transactional(readOnly = true)
	public List<RoomCardVo> searchRoomCardByEmptyRoomTypeId(String[] styleIdStrings) {

		List<RoomCardVo> roomCardVoList = new ArrayList<RoomCardVo>();
		ObjectMapper mapper = new ObjectMapper();

		if (styleIdStrings.length != 0) {
			for (int i = 0; i < styleIdStrings.length; i++) {
				if ("".equals(styleIdStrings[i])) {
					return null;
				} else {

					System.out.println(Integer.parseInt(styleIdStrings[i]));
					RoomStylePo stylePo = roomStyleDao.getId(Integer.parseInt(styleIdStrings[i]));
					RoomCardVo vo = new RoomCardVo();
					if(stylePo != null) {
						vo.setRoomName(stylePo.getRoomName());
						vo.setRoomQuantity(stylePo.getRoomQuantity().toString());
						vo.setBedType(stylePo.getBedType());
						vo.setRoomType(stylePo.getRoomType());
						vo.setRoomStyleId(stylePo.getRoomStyleId().toString());
						vo.setOrderRoomPrice(stylePo.getOrderRoomPrice().toString());
						vo.setRoomDescription(stylePo.getRoomDescription());
					}else {
						return null;
					}
					
					List<RoomPhotoPo> photoPo = roomPhotoDao.selectByRoomStyleId(Integer.parseInt(styleIdStrings[i]));
					System.out.println(photoPo.size());
					if (photoPo.size() == 0) {
						System.out.println("no photo");
					} else {
//						for (RoomPhotoPo po : photoPo) {
//						}
						RoomPhotoPo po = photoPo.get(0);
						vo.setRoomPhoto(mapper.convertValue(vo.getRoomPhoto(), String.class));
					}
					System.out.println(vo);
					roomCardVoList.add(vo);
				}
			}
			return roomCardVoList;
		} else {
			return null;
		}
	}

}
