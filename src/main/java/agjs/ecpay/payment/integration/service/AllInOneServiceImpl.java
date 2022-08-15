package agjs.ecpay.payment.integration.service;

import agjs.bean.order.ECPayVo;
import agjs.ecpay.payment.integration.AllInOne;
import agjs.ecpay.payment.integration.domain.AioCheckOutALL;

public class AllInOneServiceImpl implements AllInOneService {

	private AllInOne allInOne;
	private AioCheckOutALL aioCheckOutALL;

	public String payment(ECPayVo ecPayVo) {

		// 綠界
		// 時間
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		String orderDate = sdf.format(new Date());

		allInOne = new AllInOne("");

		aioCheckOutALL = new AioCheckOutALL();
		// 一定要有的參數：
		// 訂單編號 **不能重複
		aioCheckOutALL.setMerchantTradeNo(ecPayVo.getMerchantTradeNo());
		// 訂單生成時間
		aioCheckOutALL.setMerchantTradeDate(ecPayVo.getMerchantTradeDate());
		// 訂單總價格
		aioCheckOutALL.setTotalAmount(ecPayVo.getTotalAmount());
		// 訂單描述
		aioCheckOutALL.setTradeDesc(ecPayVo.getTradeDesc());
		// 商品名稱
		aioCheckOutALL.setItemName(ecPayVo.getItemName());
		// 返回指定頁面
		aioCheckOutALL.setClientBackURL(ecPayVo.getClientBackURL());
		// 交易成功會發送request到這個url
		aioCheckOutALL.setReturnURL(ecPayVo.getReturnURL());
		aioCheckOutALL.setNeedExtraPaidInfo("N");

		return allInOne.aioCheckOut(aioCheckOutALL, null);

	}

}
