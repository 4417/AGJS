package agjs.dao.journey;

import java.util.List;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyPo;

public interface JourneyItemDao_2 {

	public List<JourneyItemPo> selectBySohId(String sohId);

	JourneyPo selectByJourneyId(Integer id);

}
