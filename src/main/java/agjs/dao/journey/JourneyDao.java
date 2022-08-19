package agjs.dao.journey;

import java.util.List;

import agjs.bean.journey.JourneyPo;
import agjs.dao.CoreDao;

public interface JourneyDao extends CoreDao<JourneyPo, Integer> {

	List<JourneyPo> selectByTypeId(Integer typeId);

	List<JourneyPo> selectBykeyword(String keyword);
	
	List<Object[]> selectApplyCountByDate(String startDate);

	boolean deleteByIdBatch(Integer[] idArray);
	
	

}
