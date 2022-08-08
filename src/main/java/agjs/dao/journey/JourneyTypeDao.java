package agjs.dao.journey;

import agjs.bean.journey.JourneyTypePo;
import agjs.dao.CoreDao;

public interface JourneyTypeDao extends CoreDao<JourneyTypePo, Integer>{
	
	public int selectIdByName(String typeName);


}