package agjs.dao.journey;

import java.util.List;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemVo;
import agjs.dao.CoreDao;

public interface JourneyItemDao extends CoreDao<JourneyItemPo, Integer> {

	public List<JourneyItemPo> selectBySohId(String sohId);

	public List<JourneyItemPo> selectByDateRange(String startDate, String endDate);

}
