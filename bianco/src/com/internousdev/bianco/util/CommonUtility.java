package com.internousdev.bianco.util;

public class CommonUtility {
	/**
	 * 仮ユーザーID(16桁のランダムな数字)を作成するためのメソッド
	 * @return 仮ユーザーID
	 */
	public String getRamdomValue() {
		String value="";
		StringBuilder stringBuilder = new StringBuilder(value);
		double d;

		for(int i=1; i<=16; i++) {
			d=Math.random() * 10;
			stringBuilder.append((int)Math.floor(d));
		}

		value = stringBuilder.toString();

		return value;
	}

	/**
	 * パスワードを隠すためのメソッド
	 * @param password パスワード
	 * @return 2文字目から16文字目までが*で隠されたパスワード
	 */
	public String concealPassword(String password) {
		StringBuilder stringBuilder = new StringBuilder(password.substring(0, 1));

		String concealPassword = stringBuilder.append("***************").toString();

		return concealPassword;
	}
}
