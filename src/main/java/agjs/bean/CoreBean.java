package agjs.bean;

import javax.persistence.Transient;

public class CoreBean {
	@Transient
	private String msg;

	public CoreBean() {
	}

	public CoreBean(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
