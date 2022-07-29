package agjs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import agjs.bean.journey.JourneyFrontendVo;
import agjs.bean.journey.JourneyPo;
import agjs.bean.journey.JourneySearchVo;
import agjs.bean.journey.JourneyVo;
import agjs.dao.JourneyDao;
import agjs.dao.JourneyItemDao;
import agjs.dao.JourneyTypeDao;
import agjs.dao.impl.JourneyTypeDaoImpl;
import agjs.service.JourneyService;

@Service
public class JourneyServiceImpl implements JourneyService {

	@Autowired
	private JourneyItemDao journeyItemDao;
	@Autowired
	private JourneyDao journeyDao;
	@Autowired
	private JourneyTypeDao journeyTypeDao;

	private List<JourneyPo> journeyPoList = null;

	public static void main(String[] args) {
		System.out.println("main");
		JourneyTypeDaoImpl journeyTypeDao = new JourneyTypeDaoImpl();
		int id = journeyTypeDao.selectIdByName("");

	}

	@Override
	@Transactional
	public int insertJourney(JourneyFrontendVo journeyFrontendVo) {

		JourneyPo journeyPo = new JourneyPo();
		Integer id = null;
		try {
			id = journeyTypeDao.selectIdByName(journeyFrontendVo.getJourneyName());
			if (id != null) {
				journeyPo.setTypeId(id);
				System.out.println("get type id=" + id);
			} else {
				return -1;
			}

			switch (journeyFrontendVo.getLaunched()) {
			case "上架":
				journeyPo.setLaunched(true);
				break;
			case "下架":
				journeyPo.setLaunched(false);
				break;
			default:
				return -1;
			}

			journeyPo.setJourneyName(journeyFrontendVo.getJourneyName());
			journeyPo.setApplyLimit(journeyFrontendVo.getApplyLimit());
			journeyPo.setJourneyInfo(journeyFrontendVo.getJourneyInfo());
			journeyPo.setJourneyPicture(journeyFrontendVo.getJourneyPicture());
			journeyPo.setJourneyPrice(journeyFrontendVo.getJourneyPrice());
			journeyPo.setJourneyPriceChild(journeyFrontendVo.getJourneyPriceChild());

			journeyDao.insert(journeyPo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return 1;
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<JourneyPo> searchByTypeId(JourneySearchVo journeySearchVo) {

		int id = journeyTypeDao.selectIdByName(journeySearchVo.getTypeName());

		return journeyDao.selectByTypeId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<JourneyVo> searchBykeyword(JourneySearchVo journeySearchVo) {

		System.out.println(journeySearchVo.getSearchKeyword());
		journeyPoList = new ArrayList<JourneyPo>();
		List<JourneyVo> journeyVoList = new ArrayList<JourneyVo>();
		ObjectMapper mapper = new ObjectMapper();

		if ("".equals(journeySearchVo.getSearchKeyword())) {
			journeyPoList = journeyDao.select();
		} else {
			journeyPoList = journeyDao.select();
		}

		for (JourneyPo po : journeyPoList) {

			System.out.println(po);
			JourneyVo vo = new JourneyVo();
			vo.setJourneyId(po.getJourneyId().toString());
			vo.setJourneyName(po.getJourneyName());
			vo.setJourneyTypeName(po.getTypeId().toString());
			vo.setJourneyPrice(po.getJourneyPrice().toString());
			vo.setJourneyPriceChild(po.getJourneyPriceChild().toString());
			vo.setApplyLimit(po.getApplyLimit().toString());
			String decoded = mapper.convertValue(po.getJourneyPicture(), String.class);
			System.out.println(decoded);
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

		journeyDao.update(po);
		return 0;
	}

}
