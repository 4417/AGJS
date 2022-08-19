package agjs.service.announcement;

import java.util.List;

import agjs.bean.announcement.AnnouncementCountVo;
import agjs.bean.announcement.AnnouncementFilterVo;
import agjs.bean.announcement.AnnouncementPo;
import agjs.bean.announcement.AnnouncementVo;


public interface AnnouncementService {
	List<AnnouncementPo> allAnm();
	List<AnnouncementPo> searchKeyword(AnnouncementFilterVo announcementFilterVo);
	List<AnnouncementPo> filter(AnnouncementFilterVo announcementFilterVo);
	AnnouncementVo insertAnm(AnnouncementVo announcementVo);
	AnnouncementPo updateAnm(AnnouncementVo announcementVo);
	List<AnnouncementPo> delete(AnnouncementPo announcementPo);
	List<AnnouncementPo> getAnmInfo(AnnouncementVo announcementVo);
	List<AnnouncementPo> publishedAnm(AnnouncementCountVo announcementCountVo);
}
