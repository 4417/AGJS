package agjs.service;

import java.util.List;

import agjs.bean.JourneyTypePo;
import agjs.bean.JourneyTypeVo;

public interface JourneyTypeService {

	List<JourneyTypeVo> getJourneyType();

	JourneyTypePo getJourneyType(Integer id);

}