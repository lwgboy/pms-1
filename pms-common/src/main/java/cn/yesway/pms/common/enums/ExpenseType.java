package cn.yesway.pms.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExpenseType {

	OPERATING("运营费用", 0), RANT("场地费用", 1), ASSET("资产费用", 2);

	private final String desc;
	private final int value;

	private ExpenseType(String desc, int value) {
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
	public static ExpenseType getItem(int value) {
		for (ExpenseType item : values()) {
			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}
}
