package cn.yesway.pms.core.cast.expense.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExpenseCastReportProject implements Serializable {

	private static final long serialVersionUID = -7564694085221964947L;
	private String name;
	private BigDecimal[] casts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal[] getCasts() {
		return casts;
	}

	public void setCasts(BigDecimal[] casts) {
		this.casts = casts;
	}

}
