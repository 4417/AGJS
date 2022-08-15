package agjs.bean.order;

public class ECPayVo {
	// 一定要有的參數：
	// 訂單編號 **不能重複
	private String merchantTradeNo;
	// 訂單生成時間
	private String merchantTradeDate;
	// 訂單總價格
	private String totalAmount;
	// 訂單描述
	private String tradeDesc;
	// 商品名稱
	private String itemName;
	// 返回指定頁面
	private String clientBackURL;
	// 交易成功會發送request到這個url
	private String returnURL;
	private String needExtraPaidInfo="N";

	public ECPayVo() {
	}

	@Override
	public String toString() {
		return "ECPayVo [merchantTradeNo=" + merchantTradeNo + ", merchantTradeDate=" + merchantTradeDate
				+ ", totalAmount=" + totalAmount + ", tradeDesc=" + tradeDesc + ", itemName=" + itemName
				+ ", clientBackURL=" + clientBackURL + ", returnURL=" + returnURL + ", needExtraPaidInfo="
				+ needExtraPaidInfo + "]";
	}

	public String getMerchantTradeNo() {
		return merchantTradeNo;
	}

	public void setMerchantTradeNo(String merchantTradeNo) {
		this.merchantTradeNo = merchantTradeNo;
	}

	public String getMerchantTradeDate() {
		return merchantTradeDate;
	}

	public void setMerchantTradeDate(String merchantTradeDate) {
		this.merchantTradeDate = merchantTradeDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTradeDesc() {
		return tradeDesc;
	}

	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getClientBackURL() {
		return clientBackURL;
	}

	public void setClientBackURL(String clientBackURL) {
		this.clientBackURL = clientBackURL;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

	public String getNeedExtraPaidInfo() {
		return needExtraPaidInfo;
	}

	public void setNeedExtraPaidInfo(String needExtraPaidInfo) {
		this.needExtraPaidInfo = needExtraPaidInfo;
	}

}
