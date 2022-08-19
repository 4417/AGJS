package agjs.service.impl.journey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyTypePo;
import agjs.dao.journey.JourneyTypeDao;
import agjs.service.journey.JourneyTypeService;

@Service
public class JourneyTypeServiceImpl implements JourneyTypeService {

	@Autowired
	private JourneyTypeDao journeyTypeDao;

	@Override
	@Transactional(readOnly = true)
	public List<JourneyTypePo> getJourneyType() {

		return journeyTypeDao.select();
	}

	@Override
	public JourneyTypePo getJourneyType(Integer id) {

		return null;
	}

}
