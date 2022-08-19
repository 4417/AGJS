package agjs.service.restaurant;

import java.sql.Blob;
import java.util.List;

import javax.servlet.http.Part;

import agjs.bean.restaurant.RestaurantADVO;
import agjs.dao.restaurant.RestaurantADDao;
import agjs.dao.restaurant.RestaurantADDao_interface;

public class RestaurantADService {
	private RestaurantADDao_interface dao;

	public RestaurantADService() {
		dao = new RestaurantADDao();
	}

	public RestaurantADVO addAD(Integer REST_ID, String AD_NAME,
			Blob AD_PIC, String AD_INTRO, String AD_TIME) {

		RestaurantADVO restaurantADVO = new RestaurantADVO();

		restaurantADVO.setRestId(REST_ID);
		restaurantADVO.setAdName(AD_NAME);
		restaurantADVO.setAdPic(AD_PIC);
		restaurantADVO.setAdIntro(AD_INTRO);
		restaurantADVO.setAdTime(AD_TIME);
		dao.insert(restaurantADVO);

		return restaurantADVO;
	}
	
	public void deleteAD(Integer adId) {
		dao.delete(adId);
	}

//	public RestaurantADVO updateAd(Integer adId, Integer REST_ID, String AD_NAME,
//			Blob AD_PIC, String AD_INTRO, String AD_TIME) {
//
//		RestaurantADVO restaurantADVO = new RestaurantADVO();
//
//		restaurantADVO.setAdId(adId);
//		restaurantADVO.setRestId(REST_ID);
//		restaurantADVO.setAdName(AD_NAME);
//		restaurantADVO.setAdPic(AD_PIC);
//		restaurantADVO.setAdIntro(AD_INTRO);
//		restaurantADVO.setAdTime(AD_TIME);
//		dao.update(restaurantADVO);
//
//		return restaurantADVO;
//	}
//
	
//
//	public RestaurantADVO getOneAd(Integer adId) {
//		return dao.findByPrimaryKey(adId);
//	}
//
//	public List<RestaurantADVO> getAll() {
//		return dao.getAll();
//	}
}
