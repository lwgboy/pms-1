package test;

import cn.yesway.pms.common.core.secret.ASESecret;

public class Test {

	public static void main(String[] args) {
		String salary = new String(
				ASESecret.decrypt(ASESecret.parseHexStr2Byte("F5B9C7DF7F82947824AB810FB434BB4C"), "Cn>YseWay9%!(0"));
		System.out.println(salary);
	}

}
