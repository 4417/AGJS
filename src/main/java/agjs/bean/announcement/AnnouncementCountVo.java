package agjs.bean.announcement;

import org.springframework.stereotype.Repository;

@Repository
public class AnnouncementCountVo {
	
	Integer count;
	
	public AnnouncementCountVo() {
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "AnnouncementCountVo [count=" + count + "]";
	}

}
