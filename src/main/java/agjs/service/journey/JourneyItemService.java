package agjs.service.journey;

import java.util.List;

import agjs.bean.journey.JourneyItemSelectVo;
import agjs.bean.journey.JourneyItemVo;

public interface JourneyItemService {

	List<JourneyItemVo> searchJourneyItemBySohId(String sohId);

	List<JourneyItemVo> searchJourneyItemByDateRange(java.util.Date startDate, java.util.Date endDate);

	List<JourneyItemVo> searchJourneyItemByTypeId(String[] typeIdStrings);

	int updateJourneyItemDate(JourneyItemSelectVo journeyItemSelectVo);

}