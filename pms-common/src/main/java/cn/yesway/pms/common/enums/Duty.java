package cn.yesway.pms.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Duty {

	EMPLOYEE("员工", 0), BM("部门经理", 1), VP("总监", 2), GM("公司高层", 3);

	private final String desc;
	private final int value;

	private Duty(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}

	@JsonValue
	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	@JsonCreator
	public static Duty getItem(int value) {
		for (Duty item : values()) {
			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

}
