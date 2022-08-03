package agjs.service.impl.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.dao.journey.JourneyItemDao;
import agjs.dao.journey.JourneyTypeDao;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.dao.order.SalesOrderItemDao;
import agjs.service.order.SalesOrderHeaderService;

@Service
public class SalesOrderHeaderServiceImpl implements SalesOrderHeaderService {
	@Autowired
	private SalesOrderHeaderDao dao;
//	@Autowired
//	private SalesOrderItemDao salesOrderItemDao;
//	@Autowired
//	private JourneyItemDao journeyItemDao;
//	@Autowired
//	private JourneyTypeDao journeyTypeDao;


	@Transactional
	public SalesOrderHeaderPo create(SalesOrderHeaderPo SalesOrderHeader) {
//		//if login = true &&  (userPo.姓名 + userPo.身分證字號不重複)
//
//			//else create user account 
//		
//		//綁定訂單(how to 綁)
//		
//		//create SO header
//		
//		//create SO items
//		
//		//create Journey
//		
//		//串綠界API
//		
//		//新增客房使用紀錄
	
		return null;
	}
	
	
	@Override
	public List<SalesOrderHeaderPo> getAll() {
		return dao.getAll();
	}

//查詢一筆訂單底下的所有行程與訂房明細
	//回傳物件要包更大包的物件嗎??
	@Override
	public List<SalesOrderHeaderPo> selectById(Integer id) {
		
		//List<SalesOrderHeaderPo> 
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
//各種查詢

	//訂單起始日期查詢，可供飯店後台管理使用
	@Override
	public List<SalesOrderHeaderPo> selecctByOrderStartDate(Date date){

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("The date that I get(in service impl):" + formatDate.format(date));
		List<SalesOrderHeaderPo> list = dao.selectByStartDate(formatDate.format(date));
		return list;
	}
	
	
	//查詢使用者的所有訂單
	@Override
	public List<SalesOrderHeaderPo> selectByUserId(Integer userId){
		return dao.selectByUserId(userId);
	}
	
}
