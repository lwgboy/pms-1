package cn.yesway.pms.core.employee.entity;

import java.util.ArrayList;
import java.util.List;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.enums.Duty;
import cn.yesway.pms.common.enums.Gender;
import cn.yesway.pms.core.company.entity.Company;
import cn.yesway.pms.core.department.entity.Department;
import cn.yesway.pms.core.function.entity.Function;

public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2825783126972854213L;
	private String code;
	private String name;
	private String loginName;
	private String loginPwd;
	private Boolean rememberMe;
	private Gender gender;
	private String email;
	private String phone;
	private Boolean deleted;
	private String description;
	private Boolean manager;
	private String newPwd;
	private Department department = new Department();
	private Company company = new Company();
	private Duty duty;
	private List<Function> functions = new ArrayList<>();

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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Boolean getManager() {
		return manager;
	}

	public void setManager(Boolean manager) {
		this.manager = manager;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Duty getDuty() {
		return duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

}
