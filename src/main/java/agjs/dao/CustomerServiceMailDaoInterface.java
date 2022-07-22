package agjs.model;

import java.util.*;

import agjs.bean.CustomerServiceMailVO;

public interface CustomerServiceMailDaoInterface {
	public void insert(CustomerServiceMailVO customerServiceMailVO);
	public void delete(Integer faqFormId);
	public List<CustomerServiceMailVO> getAll();
}
