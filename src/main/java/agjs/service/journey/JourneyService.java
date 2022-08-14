package agjs.service.journey;

import java.util.List;

import agjs.bean.journey.JourneyFrontendVo;
import agjs.bean.journey.JourneyPo;
import agjs.bean.journey.JourneySearchVo;
import agjs.bean.journey.JourneyVo;

public interface JourneyService {

	String insertJourney(JourneyFrontendVo journeyFrontendVo);

	int updateJourney(JourneyFrontendVo journeyFrontendVo);

	List<JourneyVo> searchByTypeId(String[] typeIdStrings);
	
	List<JourneyVo> searchBykeyword(JourneySearchVo journeySearchVo);
	
	boolean deleteByIdBatch(String[] idArray);

	List<JourneyPo> searchApplyCountByDate(java.util.Date startDate);

}