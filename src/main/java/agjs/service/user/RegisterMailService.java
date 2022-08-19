package agjs.service.user;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.user.UserPo;

public interface RegisterMailService {

	void sendMail(UserPo user);

	UserPo verifyJedis(UserPo user);

	void sendActivateMail(UserPo user, SalesOrderHeaderPo salesOrderHeaderPo) throws Exception;

	void sendOrderSuccessMail(UserPo user, SalesOrderHeaderPo salesOrderHeaderPo) throws Exception;

}