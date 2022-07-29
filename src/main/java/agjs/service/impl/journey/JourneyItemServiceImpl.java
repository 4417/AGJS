package agjs.service.impl.journey;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemVo;
import agjs.bean.journey.JourneyTypePo;
import agjs.dao.journey.JourneyItemDao;
import agjs.dao.journey.JourneyTypeDao;
import agjs.service.journey.JourneyItemService;

@Service
public class JourneyItemServiceImpl implements JourneyItemService {

	@Autowired
	private JourneyItemDao journeyItemDao;
	@Autowired
	private JourneyTypeDao journeyTypeDao;

	private JourneyItemVo journeyItemVo = null;

	@Override
	@Transactional(readOnly = true)
	public List<JourneyItemVo> searchJourneyItemBySohId(String sohId) {

		List<JourneyItemVo> journeyItemVoList = new ArrayList<JourneyItemVo>();
		List<JourneyTypePo> journeyTypePoList = journeyTypeDao.select();

		if ("".equals(sohId)) {
			List<JourneyItemPo> poList = journeyItemDao.select();
		} else {
			List<JourneyItemPo> poList = journeyItemDao.selectBySohId(sohId);
		}

		return journeyItemVoList;

	}

	@Override
	@Transactional(readOnly = true)
	public List<JourneyItemVo> searchJourneyItemByDateRange(java.util.Date startDate, java.util.Date endDate) {

//		List<JourneyItemVo> journeyItemVoList = new ArrayList<JourneyItemVo>();
//		journeyItemVoList = journeyItemDao.selectBySohId(sohId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<JourneyItemPo> poList = journeyItemDao.selectByDateRange(sdf.format(startDate), sdf.format(endDate));
		List<JourneyItemVo> journeyItemVoList = new ArrayList<JourneyItemVo>();

//		List<JourneyTypePo> journeyTypePoList = journeyTypeDao.select();

		for (JourneyItemPo po : poList) {

			JourneyItemVo vo = new JourneyItemVo();

		}

		return journeyItemVoList;

	}

}
