package agjs.service.impl.journey;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import agjs.bean.journey.JourneyFrontendVo;
import agjs.bean.journey.JourneyPo;
import agjs.bean.journey.JourneySearchVo;
import agjs.bean.journey.JourneyTypePo;
import agjs.bean.journey.JourneyVo;
import agjs.dao.journey.JourneyDao;
import agjs.dao.journey.JourneyItemDao;
import agjs.dao.journey.JourneyTypeDao;
import agjs.service.journey.JourneyService;

@Service
public class JourneyServiceImpl implements JourneyService {

	@Autowired
	private JourneyItemDao journeyItemDao;
	@Autowired
	private JourneyDao journeyDao;
	@Autowired
	private JourneyTypeDao journeyTypeDao;

	private List<JourneyPo> journeyPoList = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// 新增行程
	@Override
	@Transactional
	public String insertJourney(JourneyFrontendVo journeyFrontendVo) {

		JourneyPo journeyPo = new JourneyPo();
		Integer id = null;

		id = journeyTypeDao.selectIdByName(journeyFrontendVo.getJourneyTypeName());

		if (id != null) {
			journeyPo.setTypeId(id);
			System.out.println("get type id=" + id);
		} else {
			return null;
		}

		switch (journeyFrontendVo.getLaunched()) {
		case "上架":
			journeyPo.setLaunched(true);
			break;
		case "下架":
			journeyPo.setLaunched(false);
			break;
		default:
			return null;
		}

		journeyPo.setJourneyName(journeyFrontendVo.getJourneyName());
		journeyPo.setApplyLimit(journeyFrontendVo.getApplyLimit());
		journeyPo.setJourneyInfo(journeyFrontendVo.getJourneyInfo());
		journeyPo.setJourneyPicture(journeyFrontendVo.getJourneyPicture());
		journeyPo.setJourneyPrice(journeyFrontendVo.getJourneyPrice());
		journeyPo.setJourneyPriceChild(journeyFrontendVo.getJourneyPriceChild());

		return journeyDao.insert(journeyPo).toString();

	}

	// 行程種類ID 搜尋行程
	@Override
	@Transactional(readOnly = true)
	public List<JourneyVo> searchByTypeId(String[] typeIdStrings) {

		System.out.println("searchByTypeId");
		List<JourneyVo> journeyVoList = new ArrayList<JourneyVo>();
		ObjectMapper mapper = new ObjectMapper();

		if (typeIdStrings.length != 0) {
			for (int i = 0; i < typeIdStrings.length; i++) {

				if ("".equals(typeIdStrings[i])) {
					return null;
				} else {
					System.out.println(Integer.parseInt(typeIdStrings[i]));
					List<JourneyPo> result = journeyDao.selectByTypeId(Integer.parseInt(typeIdStrings[i]));

					for (JourneyPo po : result) {
						System.out.println(po);
						JourneyVo vo = new JourneyVo();
						vo.setJourneyId(po.getJourneyId().toString());
						vo.setJourneyName(po.getJourneyName());
						JourneyTypePo typePo = journeyTypeDao.select(po.getTypeId());
						vo.setJourneyTypeName(typePo.getTypeName());
						vo.setJourneyPrice(po.getJourneyPrice().toString());
						vo.setJourneyPriceChild(po.getJourneyPriceChild().toString());
						vo.setApplyLimit(po.getApplyLimit().toString());
						String decoded = mapper.convertValue(po.getJourneyPicture(), String.class);
						vo.setJourneyPicture(decoded);
						vo.setInfo(po.getJourneyInfo());
						vo.setLaunched(po.isLaunched());
						journeyVoList.add(vo);
					}
				}
			}

		} else {
			return null;
		}

		return journeyVoList;
	}

	// 取得 行程類型 map<id,類型名稱>
	private Map<Integer, String> getTypeMap() {

		Map<Integer, String> typeMap = new HashMap<Integer, String>();

		List<JourneyTypePo> resultType = journeyTypeDao.select();
		for (JourneyTypePo po : resultType) {
			typeMap.put(po.getTypeId(), po.getTypeName());
		}

		return typeMap;
	}

	// 關鍵字 搜尋行程名稱
	@Override
	@Transactional(readOnly = true)
	public List<JourneyVo> searchBykeyword(JourneySearchVo journeySearchVo) {

		System.out.println("searchBykeyword");
		System.out.println(journeySearchVo.getSearchKeyword());
		journeyPoList = new ArrayList<JourneyPo>();
		List<JourneyVo> journeyVoList = new ArrayList<JourneyVo>();
		ObjectMapper mapper = new ObjectMapper();

		if ("".equals(journeySearchVo.getSearchKeyword())) {
			journeyPoList = journeyDao.select();
		} else {
			journeyPoList = journeyDao.selectBykeyword(journeySearchVo.getSearchKeyword());
		}

		for (JourneyPo po : journeyPoList) {

			System.out.println(po);
			JourneyVo vo = new JourneyVo();
			vo.setJourneyId(po.getJourneyId().toString());
			vo.setJourneyName(po.getJourneyName());
			JourneyTypePo typePo = journeyTypeDao.select(po.getTypeId());
			vo.setJourneyTypeName(typePo.getTypeName());
			vo.setJourneyPrice(po.getJourneyPrice().toString());
			vo.setJourneyPriceChild(po.getJourneyPriceChild().toString());
			vo.setApplyLimit(po.getApplyLimit().toString());
			String decoded = mapper.convertValue(po.getJourneyPicture(), String.class);
			vo.setJourneyPicture(decoded);
			vo.setInfo(po.getJourneyInfo());
			vo.setLaunched(po.isLaunched());
			journeyVoList.add(vo);

		}
		return journeyVoList;
	}

	@Override
	@Transactional
	public int updateJourney(JourneyFrontendVo journeyFrontendVo) {

		int typeId = journeyTypeDao.selectIdByName(journeyFrontendVo.getJourneyTypeName());
		// mapping
		JourneyPo po = new JourneyPo();
		po.setJourneyId(Integer.parseInt(journeyFrontendVo.getJourneyId()));
		po.setJourneyName(journeyFrontendVo.getJourneyName());
		po.setTypeId(typeId);
		po.setJourneyPrice(journeyFrontendVo.getJourneyPrice());
		po.setJourneyPriceChild(journeyFrontendVo.getJourneyPriceChild());
		po.setApplyLimit(journeyFrontendVo.getApplyLimit());

		if ("上架".equals(journeyFrontendVo.getLaunched())) {
			po.setLaunched(true);
		} else if ("下架".equals(journeyFrontendVo.getLaunched())) {
			po.setLaunched(false);
		}

		po.setJourneyPicture(journeyFrontendVo.getJourneyPicture());
		po.setJourneyInfo(journeyFrontendVo.getJourneyInfo());

		return journeyDao.update(po);
	}

	@Override
	@Transactional
	public boolean deleteByIdBatch(String[] idArray) {

		Integer[] idList;

		if (idArray.length != 0) {
			idList = new Integer[idArray.length];
		} else {
			return false;
		}

		try {
			for (int i = 0; i < idArray.length; i++) {
				if ("".equals(idArray[i])) {
					return false;
				} else {
					idList[i] = Integer.parseInt(idArray[i]);
					System.out.println(idList[i]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return journeyDao.deleteByIdBatch(idList);
	}

	// 日期 搜尋 當日行程資料 包含人數統計
	@Override
	@Transactional(readOnly = true)
	public List<JourneyPo> searchApplyCountByDate(Date startDate) {

		List<Object[]> objectList = journeyDao.selectApplyCountByDate(sdf.format(startDate));
		List<JourneyPo> journeyPoList = new ArrayList<JourneyPo>();

		if (objectList.size() != 0) {
			for (Object[] objects : objectList) {
				JourneyPo po = new JourneyPo();

				po.setJourneyId((Integer) objects[0]);
				po.setJourneyName((String) objects[1]);
				po.setTypeId((Integer) objects[2]);
				po.setJourneyPrice((Integer) objects[3]);
				po.setJourneyPriceChild((Integer) objects[4]);
				po.setApplyLimit((Integer) objects[5]);
				po.setJourneyPicture((byte[]) objects[6]);
				po.setJourneyInfo((String) objects[7]);
				Byte byt = (byte) objects[8];
				int launched = byt.intValue();
				if (launched == 1) {
					po.setLaunched(true);
				} else if (launched == 0) {
					po.setLaunched(false);
				} else {
					return null;
				}
				if (objects[9] == null) {
					po.setPeopleCount(0);
				} else {
					BigDecimal bd = (BigDecimal) objects[9];
					po.setPeopleCount(bd.intValue());
				}
				if (objects[10] == null) {
					po.setDateString("");
				} else {
					po.setDateString(sdf.format((Date) objects[10]));
				}
				po.setTypeName((String) objects[11]);
				po.setRest(po.getApplyLimit() - po.getPeopleCount());
				System.out.println("po=" + po);
				journeyPoList.add(po);
			}
		} else {
			return null;
		}
		return journeyPoList;
	}

}
