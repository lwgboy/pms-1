package cn.yesway.pms.core.cast.expense.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.enums.ApprovalStatus;
import cn.yesway.pms.common.enums.ExpenseType;
import cn.yesway.pms.core.company.entity.Company;
import cn.yesway.pms.core.employee.entity.Employee;

public class ExpenseCast extends BaseEntity {

	private static final long serialVersionUID = 8187901846388999748L;
	private String code;
	private ExpenseType type;
	private String voucherNumber;
	private BigDecimal preTaxCast;
	private BigDecimal tasMoney;
	private BigDecimal afterTaxCast;
	private Date createDate;
	private String description;
	private Company company;
	private ApprovalStatus status;
	private Employee employee;
	private List<ExpenseCastDetail> details = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ExpenseType getType() {
		return type;
	}

	public void setType(ExpenseType type) {
		this.type = type;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public BigDecimal getPreTaxCast() {
		return preTaxCast;
	}

	public void setPreTaxCast(BigDecimal preTaxCast) {
		this.preTaxCast = preTaxCast;
	}

	public BigDecimal getTasMoney() {
		return tasMoney;
	}

	public void setTasMoney(BigDecimal tasMoney) {
		this.tasMoney = tasMoney;
	}

	public BigDecimal getAfterTaxCast() {
		return afterTaxCast;
	}

	public void setAfterTaxCast(BigDecimal afterTaxCast) {
		this.afterTaxCast = afterTaxCast;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<ExpenseCastDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ExpenseCastDetail> details) {
		this.details = details;
	}

}
