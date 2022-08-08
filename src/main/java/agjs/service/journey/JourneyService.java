package agjs.service.journey;

import java.util.List;

import agjs.bean.journey.JourneyFrontendVo;
import agjs.bean.journey.JourneySearchVo;
import agjs.bean.journey.JourneyVo;

public interface JourneyService {

	int insertJourney(JourneyFrontendVo journeyFrontendVo);

	int updateJourney(JourneyFrontendVo journeyFrontendVo);

	List<JourneyVo> searchByTypeId(String[] typeIdStrings);
	
	List<JourneyVo> searchBykeyword(JourneySearchVo journeySearchVo);
	
	boolean deleteByIdBatch(String[] idArray);

}