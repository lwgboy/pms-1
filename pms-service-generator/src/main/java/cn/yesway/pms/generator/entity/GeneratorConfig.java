package cn.yesway.pms.generator.entity;

import java.time.LocalDate;

import cn.yesway.pms.common.entity.BaseEntity;

public class GeneratorConfig extends BaseEntity {

	private static final long serialVersionUID = 5007167219157477148L;

	private String seed;
	private String prefix;
	private String formatter;
	private Integer digit;
	private Integer number;
	private LocalDate date;

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public Integer getDigit() {
		return digit;
	}

	public void setDigit(Integer digit) {
		this.digit = digit;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
