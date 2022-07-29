package agjs.service.journey;

import java.util.List;

import agjs.bean.journey.JourneyFrontendVo;
import agjs.bean.journey.JourneyPo;
import agjs.bean.journey.JourneySearchVo;
import agjs.bean.journey.JourneyVo;

public interface JourneyService {

	int insertJourney(JourneyFrontendVo journeyFrontendVo);

	int updateJourney(JourneyFrontendVo journeyFrontendVo);

	List<JourneyPo> searchByTypeId(JourneySearchVo journeySearchVo);
	
	List<JourneyVo> searchBykeyword(JourneySearchVo journeySearchVo);

}