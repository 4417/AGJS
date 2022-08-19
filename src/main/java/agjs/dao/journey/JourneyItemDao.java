package agjs.dao.journey;

import java.util.List;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemVo;
import agjs.dao.CoreDao;

public interface JourneyItemDao extends CoreDao<JourneyItemPo, Integer> {

	public List<JourneyItemPo> selectBySohId(String sohId);
	
	public List<JourneyItemVo> selectBySohIdJDBC(String sohId);

	public List<JourneyItemVo> selectByDateRange(java.util.Date startDate, java.util.Date endDate);

	public List<JourneyItemPo> selectByJourneyId(Integer journeyId);
	
	public List<JourneyItemPo> selectByTypeId(Integer[] typeId);

	public List<JourneyItemVo> selectByTypeIdJDBC(Integer[] typeId);
	
	public int updateDate(JourneyItemPo beanPo);

}
