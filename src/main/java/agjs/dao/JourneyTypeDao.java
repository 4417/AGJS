package agjs.dao;

import agjs.bean.journey.JourneyTypePo;

public interface JourneyTypeDao extends CoreDao<JourneyTypePo, Integer>{
	
	public int selectIdByName(String typeName);


}