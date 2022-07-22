package agjs.dao;

import java.util.List;

import agjs.bean.AnnouncementPo;

public interface AnnouncementDao {
	List<AnnouncementPo> selectKeyword();
	List<AnnouncementPo> selectStartDate();
	List<AnnouncementPo> selectEndDate();
	List<AnnouncementPo> selectType();
	List<AnnouncementPo> insertAnm();
	List<AnnouncementPo> updateAnm();
	List<AnnouncementPo> deleteAnm();
}
