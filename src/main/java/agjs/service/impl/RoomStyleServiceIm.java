package agjs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.RoomStylePo;
import agjs.dao.RoomStyleIn;
import agjs.dao.impl.RoomStyleDaoImpl;

@Service
public class RoomStyleServiceIm implements RoomStyleIn<RoomStylePo> {

	private final RoomStyleDaoImpl roomStyleDao;

	@Autowired
	public RoomStyleServiceIm(RoomStyleDaoImpl roomStyleDao) {
		this.roomStyleDao = roomStyleDao;
	}

	@Override
	public List<RoomStylePo> getAll() {
		List<RoomStylePo> list = new ArrayList<RoomStylePo>();
		try {
			list = roomStyleDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
