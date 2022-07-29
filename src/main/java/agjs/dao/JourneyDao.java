package agjs.dao;

import java.util.List;

import agjs.bean.journey.JourneyPo;

public interface JourneyDao extends CoreDao<JourneyPo, Integer> {

	List<JourneyPo> selectByTypeId(Integer typeId);

	List<JourneyPo> selectBykeyword(String keyword);

}
