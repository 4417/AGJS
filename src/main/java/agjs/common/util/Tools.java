package agjs.common.util;

import java.util.Random;

public class Tools {

	// 亂數產生
	public String genRandomNum(int len) {

		String randomNum = "";
		Random random = new Random();
		System.out.println("產生長度" + len + "亂數");

		for (int i = 0; i < len; i++) {
			Integer code = random.nextInt(10);
			randomNum += code.toString();
		}
		System.out.println(randomNum);
		return randomNum;
	}

	public String genAccount(int len) throws Exception {

		String reg = "^[0-9a-zA-Z]{4,25}$";
		String accountString = genAuthCode(len);

		while (true) {
			if (accountString.matches(reg)) {
				break;
			} else {
				accountString = genAuthCode(len);
			}
		}
		System.out.println("account:" + accountString);
		return accountString;
	}

	public String genPassword(int len) throws Exception {

		String pwd_reg = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{4,25}$";
		String passwordString = genAuthCode(len);

		while (true) {
			if (passwordString.matches(pwd_reg)) {
				break;
			} else {
				passwordString = genAuthCode(len);
			}
		}
		System.out.println("password:" + passwordString);
		return passwordString;
	}

	// 亂數驗證碼產生
	public String genAuthCode(int len) {

		String AuthCode = "";
		Random random = new Random();
		System.out.println("產生長度" + len + "亂數");

		for (int i = 0; i < len; i++) {
			int code = random.nextInt(3);
			AuthCode += genRandomAuthCode(code);
		}

		return AuthCode;
	}

	private String genRandomAuthCode(int code) {

		Random random = new Random();
		String string = "";

		switch (code) {
		case 0: {
			return string += (char) (random.nextInt(26) + 65);
		}
		case 1: {
			return string += (char) (random.nextInt(26) + 97);
		}
		case 2: {
			return String.valueOf(random.nextInt(10));
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + code);
		}
	}
}
