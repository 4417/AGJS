//package agjs.service.restaurant;
//
//import java.sql.Blob;
//import java.util.List;
//
//import agjs.bean.restaurant.RestaurantADVO;
//import agjs.dao.restaurant.RestaurantADDao;
//import agjs.dao.restaurant.RestaurantADDao_interface;
//
//public class RestaurantADService {
//	private RestaurantADDao_interface dao;
//
//	public RestaurantADService() {
//		dao = new RestaurantADDao();
//	}
//
//	public RestaurantADVO addAD(Integer adId, Integer restId, String adName,
//			byte[] adPic, String adIntro, java.sql.Date adTime) {
//
//		RestaurantADVO restaurantADVO = new RestaurantADVO();
//
//		restaurantADVO.setRestId(restId);
//		restaurantADVO.setAdName(adName);
//		restaurantADVO.setAdPic(adPic);
//		restaurantADVO.setAdIntro(adIntro);
//		restaurantADVO.setAdTime(adTime);
//		dao.insert(restaurantADVO);
//
//		return restaurantADVO;
//	}
//
//	public RestaurantADVO updateAd(Integer adId, Integer restId, String adName,
//			byte[] adPic, String adIntro, java.sql.Date adTime) {
//
//		RestaurantADVO restaurantADVO = new RestaurantADVO();
//
//		restaurantADVO.setAdId(adId);
//		restaurantADVO.setRestId(restId);
//		restaurantADVO.setAdName(adName);
//		restaurantADVO.setAdPic(adPic);
//		restaurantADVO.setAdIntro(adIntro);
//		restaurantADVO.setAdTime(adTime);
//		dao.update(restaurantADVO);
//
//		return restaurantADVO;
//	}
//
//	public void deleteAD(Integer adId) {
//		dao.delete(adId);
//	}
//
//	public RestaurantADVO getOneAd(Integer adId) {
//		return dao.findByPrimaryKey(adId);
//	}
//
//	public List<RestaurantADVO> getAll() {
//		return dao.getAll();
//	}
//}
