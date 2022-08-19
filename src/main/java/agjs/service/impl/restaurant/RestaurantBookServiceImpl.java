//package agjs.service.impl.restaurant;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import agjs.bean.announcement.AnnouncementPo;
//import agjs.bean.restaurant.RestaurantBookPo;
//import agjs.dao.restaurant.RestaurantBookDao;
//import agjs.service.restaurant.RestaurantBookService;
//
//@Service
//public class RestaurantBookServiceImpl implements RestaurantBookService{
//
//	@Autowired
//	private RestaurantBookDao restaurantBookDao;
//	
//	@Override
//	public List<RestaurantBookPo> selectBookDate(RestaurantBookPo restaurantBookPo) {
//		return null;
//	}
//	
//	
//	@Override
//	public List<RestaurantBookPo> allRBook(){
//		return null;
//		
//	}
//	@Override
//	public List<RestaurantBookPo> selectType(RestaurantBookPo restaurantBookPo){
//		return null;
//		
//	}
//	@Override
//	public RestaurantBookPo insertBook(RestaurantBookPo restaurantBookPo) {
//		Date restDate = restaurantBookPo.getRestDate();
//		LocalDate today = LocalDate.now();
//		if(restaurantBookPo.getUserName().trim() == "" || restaurantBookPo.getUserName() == null) {
//			System.out.println("請輸入姓名");
//		}
//		
//		if(restaurantBookPo.getRestTel() == "") {
//			System.out.println("請輸入訂位電話");
//		}
//		
//		if(restDate == null) {
//			System.out.println("請選擇公告日期");
//		}
//		
//		//日期
////		String restDateString = restDate.toString();
////		String todayString = today.toString();
////		if(startDateString.equals(todayString)) {
////			restaurantBookPo.setAnmStatus("已上架");
////		}
////		else {
////			restaurantBookPo.setAnmStatus("待上架");
////		}
////
////		String endDateString = endDate.toString();
////		if(endDateString.equals("1970-01-01")) {
////			restaurantBookPo.setAnmEndDate(null);
////		}
//		
//		System.out.println(restaurantBookPo);
//		restaurantBookDao.insertBook(restaurantBookPo);
//		return restaurantBookPo;
//	}
//	@Override
//	public RestaurantBookPo updateBook(RestaurantBookPo restaurantBookPo) {
//		return null;
//	}
//	@Override
//	public List<RestaurantBookPo> getBookInfo(RestaurantBookPo restaurantBookPo){
//		List<RestaurantBookPo> bookPoList = null;
////		Date endDate = restaurantBookPo.getAnmEndDate();
////		String endDateString = endDate.toString();
////		if(endDateString.equals("1970-01-01")) {
////			restaurantBookPo.setAnmEndDate(null);
////		}
//		bookPoList = restaurantBookDao.getBookInfo(restaurantBookPo);
//		return bookPoList;
//	}
//	
//	
//	
//}
