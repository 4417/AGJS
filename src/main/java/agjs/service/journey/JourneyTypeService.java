package agjs.service.journey;

import java.util.List;

import agjs.bean.journey.JourneyTypePo;

public interface JourneyTypeService {

	List<JourneyTypePo> getJourneyType();

	JourneyTypePo getJourneyType(Integer id);

}