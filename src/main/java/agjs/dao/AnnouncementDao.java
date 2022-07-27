package agjs.dao;

import java.util.List;

import agjs.bean.AnnouncementPo;

public interface AnnouncementDao {
	List<AnnouncementPo> selectKeyword(String keyword);
	List<AnnouncementPo> selectStartDate(AnnouncementPo announcementPo);
	List<AnnouncementPo> selectStauts(AnnouncementPo announcementPo);
	List<AnnouncementPo> selectType(AnnouncementPo announcementPo);
	AnnouncementPo insertAnm(AnnouncementPo announcementPo);
	AnnouncementPo updateAnm(Integer anmId);
	List<AnnouncementPo> deleteAnm(Integer anmId);
	List<AnnouncementPo> getAnmInfo(AnnouncementPo announcementPo);
}
