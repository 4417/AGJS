package agjs.dao.customer;

import java.util.*;

import agjs.bean.customer.CustomerServiceMailVO;

public interface CustomerServiceMailDaoInterface {
	public void insert(CustomerServiceMailVO customerServiceMailVO);
	public void delete(Integer faqFormId);
	public List<CustomerServiceMailVO> getAll();
}
