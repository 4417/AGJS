package agjs.dao;

import java.util.List;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemVo;

public interface JourneyItemDao extends CoreDao<JourneyItemPo, Integer> {

	public List<JourneyItemPo> selectBySohId(String sohId);

	public List<JourneyItemPo> selectByDateRange(String startDate, String endDate);

}
