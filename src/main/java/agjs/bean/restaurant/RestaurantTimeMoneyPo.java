package agjs.bean.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "REST_TIME_MONEY")
public class RestaurantTimeMoneyPo {

//	MONEY_ID, REST_ID, MONEY, MONEY_CONTENT
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MONEY_ID")
	private Integer moneyId;
	@Column(name = "REST_ID")
	private Integer restId;
	@Column(name = "MONEY")
	private String money;
	@Column(name = "MONEY_CONTENT")
	private String moneyContent;

	@Override
	public String toString() {
		return "RestauranatTimeMoneyPo [moneyId=" + moneyId + ", restId=" + restId + ", money=" + money
				+ ", moneyContent=" + moneyContent + "]";
	}

	public RestaurantTimeMoneyPo() {
		super();
	}

	public Integer getMoneyId() {
		return moneyId;
	}

	public void setMoneyId(Integer moneyId) {
		this.moneyId = moneyId;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMoneyContent() {
		return moneyContent;
	}

	public void setMoneyContent(String moneyContent) {
		this.moneyContent = moneyContent;
	}

	
}
