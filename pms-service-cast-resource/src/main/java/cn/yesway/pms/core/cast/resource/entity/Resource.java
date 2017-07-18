package cn.yesway.pms.core.cast.resource.entity;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.project.entity.Project;

public class Resource extends BaseEntity {
	private static final long serialVersionUID = 303514216912880290L;
	private String code;
	private String name;
	private String dependency;
	private String operators;
	private String type;
	private String subType;
	private String useType;
	private String chargeType;
	private String chargeStandards;
	private String chargeFrequency;
	private String chargeMode;
	private String chargeCode;
	private Long paySubject;
	private String accountingType;
	private String accountingRemark;
	private String unit;
	private String description;
	private Boolean deleted;
	private Project project;

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

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	public String getOperators() {
		return operators;
	}

	public void setOperators(String operators) {
		this.operators = operators;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeStandards() {
		return chargeStandards;
	}

	public void setChargeStandards(String chargeStandards) {
		this.chargeStandards = chargeStandards;
	}

	public String getChargeFrequency() {
		return chargeFrequency;
	}

	public void setChargeFrequency(String chargeFrequency) {
		this.chargeFrequency = chargeFrequency;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public Long getPaySubject() {
		return paySubject;
	}

	public void setPaySubject(Long paySubject) {
		this.paySubject = paySubject;
	}

	public String getAccountingType() {
		return accountingType;
	}

	public void setAccountingType(String accountingType) {
		this.accountingType = accountingType;
	}

	public String getAccountingRemark() {
		return accountingRemark;
	}

	public void setAccountingRemark(String accountingRemark) {
		this.accountingRemark = accountingRemark;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
