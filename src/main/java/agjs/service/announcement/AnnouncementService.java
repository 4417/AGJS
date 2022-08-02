package agjs.service.announcement;

import java.util.List;

import agjs.bean.announcement.AnnouncementFilterVo;
import agjs.bean.announcement.AnnouncementPo;


public interface AnnouncementService {
	List<AnnouncementPo> allAnm();
	List<AnnouncementPo> searchKeyword(AnnouncementFilterVo announcementFilterVo);
	List<AnnouncementPo> filter(AnnouncementFilterVo announcementFilterVo);
	AnnouncementPo insertAnm(AnnouncementPo announcementPo);
	AnnouncementPo updateAnm(AnnouncementPo announcementPo);
	List<AnnouncementPo> delete(AnnouncementPo announcementPo);
	List<AnnouncementPo> getAnmInfo(AnnouncementPo announcementPo);
}
