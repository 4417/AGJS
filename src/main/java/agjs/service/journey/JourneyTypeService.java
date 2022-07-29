package agjs.service.journey;

import java.util.List;

import agjs.bean.journey.JourneyTypePo;
import agjs.bean.journey.JourneyTypeVo;

public interface JourneyTypeService {

	List<JourneyTypeVo> getJourneyType();

	JourneyTypePo getJourneyType(Integer id);

}