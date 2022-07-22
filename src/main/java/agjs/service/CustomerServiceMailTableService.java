package agjs.service;

import java.sql.Date;
import java.util.List;

import agjs.bean.CustomerServiceMailVO;
import agjs.dao.CustomerServiceMailDaoInterface;
import agjs.dao.CustomerServiceMailJDBCDao;



public class CustomerServiceMailTableService {

	private CustomerServiceMailDaoInterface dao;
	
	public CustomerServiceMailTableService() {
		dao = new CustomerServiceMailJDBCDao();
	}
	
	public CustomerServiceMailVO addMail(Integer faqFormId, String faqTypeName, String userName, 
			String userPhone, String userEmail, String contentText, Date createDate) {

		CustomerServiceMailVO customerServiceMailVO = new CustomerServiceMailVO();

		customerServiceMailVO.setFaqFormId(faqFormId);
		customerServiceMailVO.setFaqTypeName(faqTypeName);
		customerServiceMailVO.setUserName(userName);
		customerServiceMailVO.setUserPhone(userPhone);
		customerServiceMailVO.setUserEmail(userEmail);
		customerServiceMailVO.setContentText(contentText);
		customerServiceMailVO.setCreateDate(createDate);
		dao.insert(customerServiceMailVO);

		return customerServiceMailVO;
	}

	public void deleteMail(Integer faqFormId) {
		dao.delete(faqFormId);
	}

	public List<CustomerServiceMailVO> getAll() {
		return dao.getAll();
	}
}
