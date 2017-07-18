package cn.yesway.pms.core.cast.expense.entity;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.enums.ExpenseType;

public class Expense extends BaseEntity {

	private static final long serialVersionUID = -4879706250224961755L;
	private String code;
	private String name;
	private ExpenseType type;
	private String description;
	private Boolean deleted;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExpenseType getType() {
		return type;
	}

	public void setType(ExpenseType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
