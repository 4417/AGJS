package agjs.service.impl.journey;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemSelectVo;
import agjs.bean.journey.JourneyItemVo;
import agjs.bean.journey.JourneyPo;
import agjs.dao.journey.JourneyDao;
import agjs.dao.journey.JourneyItemDao;
import agjs.service.journey.JourneyItemService;

@Service
public class JourneyItemServiceImpl implements JourneyItemService {

	@Autowired
	private JourneyItemDao journeyItemDao;
	@Autowired
	private JourneyDao journeyDao;

	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// sohId搜行程訂單
	@Override
	@Transactional(readOnly = true)
	public List<JourneyItemVo> searchJourneyItemBySohId(String sohId) {

		if ("".equals(sohId)) {
			List<JourneyItemPo> poList = journeyItemDao.select();
			return ConvertVoList(poList);
		} else if (sohId.matches("[0-9]{1,8}")) {
			return journeyItemDao.selectBySohIdJDBC(sohId);
		} else {
			return null;
		}

	}

	private List<JourneyItemVo> ConvertVoList(List<JourneyItemPo> polist) {

		List<JourneyItemVo> journeyItemVoList = new ArrayList<JourneyItemVo>();
		for (JourneyItemPo po : polist) {
			JourneyItemVo vo = new JourneyItemVo();
			vo.setJourneyItemId(po.getJourneyItemId());
			vo.setSohId(po.getSohId());
			vo.setJourneyName(journeyDao.select(po.getJourneyId()).getJourneyName());
			vo.setAdults(po.getAdults());
			vo.setChildren(po.getChildren());
			vo.setDateString(sdf.format(po.getJourneyDate()));
			journeyItemVoList.add(vo);
		}
		return journeyItemVoList;
	}

	// 日期範圍搜行程訂單
	@Override
	@Transactional(readOnly = true)
	public List<JourneyItemVo> searchJourneyItemByDateRange(java.util.Date startDate, java.util.Date endDate) {

		List<JourneyItemVo> journeyItemVoList = journeyItemDao.selectByDateRange(startDate, endDate);

		for (int i = 0; i < journeyItemVoList.size(); i++) {
			journeyItemVoList.get(i).setDateString(sdf.format(journeyItemVoList.get(i).getJourneyDate()));
			System.out.println(journeyItemVoList.get(i));
		}
		return journeyItemVoList;
	}

	// 行程類別搜行程訂單
	@Override
	@Transactional(readOnly = true)
	public List<JourneyItemVo> searchJourneyItemByTypeId(String[] typeIdStrings) {

		Integer[] idList;
		List<JourneyItemVo> journeyItemVoList = null;
		Map<Integer, String> nameMap = new HashMap<Integer, String>();
		journeyItemVoList = new ArrayList<JourneyItemVo>();

		if (typeIdStrings.length != 0) {

			for (int i = 0; i < typeIdStrings.length; i++) {

				if ("".equals(typeIdStrings[i])) {
					return null;
				} else {
					List<JourneyPo> result = journeyDao.selectByTypeId(Integer.parseInt(typeIdStrings[i]));
					for (JourneyPo po : result) {
						nameMap.put(po.getJourneyId(), po.getJourneyName());
						List<JourneyItemPo> resultItemPo = journeyItemDao.selectByJourneyId(po.getJourneyId());
						for (JourneyItemPo po2 : resultItemPo) {
							JourneyItemVo vo = new JourneyItemVo();
							vo.setJourneyItemId(po2.getJourneyItemId());
							vo.setSohId(po2.getSohId());
							vo.setJourneyName(nameMap.get(po2.getJourneyId()));
							vo.setAdults(po2.getAdults());
							vo.setChildren(po2.getChildren());
							vo.setDateString(sdf.format(po2.getJourneyDate()));
							journeyItemVoList.add(vo);
						}
					}
				}
			}
		} else {
			return null;
		}
		if (typeIdStrings.length != 0) {
			idList = new Integer[typeIdStrings.length];
			for (int i = 0; i < typeIdStrings.length; i++) {
				if ("".equals(typeIdStrings[i])) {
					return null;
				} else {
					idList[i] = Integer.parseInt(typeIdStrings[i]);
				}
			}
		} else {
			return null;
		}
// ============================= JDBC =================================
//		try {
//
//			journeyItemVoList = new ArrayList<JourneyItemVo>();
//			journeyItemVoList = journeyItemDao.selectByTypeIdJDBC(idList);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
		return journeyItemVoList;
	}

	// 更新行程訂單 日期
	@Override
	@Transactional
	public int updateJourneyItemDate(JourneyItemSelectVo journeyItemSelectVo) {

		JourneyItemPo po = new JourneyItemPo();

		if ("".equals(journeyItemSelectVo.getJourneyItemId())) {
			return -1;
		} else if (journeyItemSelectVo.getJourneyItemId().matches("[0-9]{1,15}")) {

			po.setJourneyItemId(Integer.parseInt(journeyItemSelectVo.getJourneyItemId()));
			po.setJourneyDate(journeyItemSelectVo.getUpdateDate());
			return journeyItemDao.updateDate(po);
		} else {
			return -1;
		}
	}

}
