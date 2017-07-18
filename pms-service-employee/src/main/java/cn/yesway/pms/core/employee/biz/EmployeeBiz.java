package cn.yesway.pms.core.employee.biz;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yesway.pms.common.enums.Duty;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.employee.dao.EmployeeDao;
import cn.yesway.pms.core.employee.entity.Employee;
import cn.yesway.pms.core.function.entity.Function;

@Component("employeeBiz")
public class EmployeeBiz {

	private static final String SALT = "Cn>YseWay9%!(0";
	@Autowired
	private EmployeeDao employeeDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean create(Employee employee) {
		employee.setLoginPwd(new Md5Hash("123456", SALT).toString());
		employee.setDeleted(false);
		LocalDateTime time = LocalDateTime.now();
		employee.setGmtCreate(time);
		employee.setGmtModified(time);
		if (employeeDao.insert(employee) > 0) {
			// 添加上报工时表权限
			employee.getFunctions().add(new Function(9L));
			employee.getFunctions().add(new Function(15L));
			if (employee.getDuty() == Duty.BM) { // 添加审核工时表权限
				employee.getFunctions().add(new Function(20L));
			}
			if (employee.getDuty() == Duty.VP) { // 添加审批工时表权限
				employee.getFunctions().add(new Function(21L));
			}
			employeeDao.insertDefaultPermission(employee);
			return true;
		}
		return false;
	}

	public Boolean update(Employee employee) {
		employee.setGmtModified(LocalDateTime.now());
		if (employeeDao.update(employee) > 0) {
			return true;
		}
		return false;
	}

	public Boolean deleteByIds(long... ids) {
		int result = employeeDao.deleteByIds(ids);
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Boolean resumedByIds(long... ids) {
		int result = employeeDao.resumedByIds(ids);
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Employee findById(Long id) {
		return employeeDao.findById(id);
	}

	public PageBean<Employee> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap) {
		return employeeDao.listByPageAndParam(pageParam, paramMap);
	}

	public List<Employee> listByParam(Map<String, Object> paramMap) {
		return employeeDao.listByParam(paramMap);
	}

	public Employee login(String loginName, String loginPwd) {
		Map<String, Object> params = new HashMap<>();
		params.put("loginName", loginName);
		params.put("loginPwd", new Md5Hash(loginPwd, SALT).toString());
		return employeeDao.findByLoginNameAndLoginPwd(params);
	}

	public Boolean resetPassword(Long id) {
		if (employeeDao.resetPassword(id) > 0) {
			return true;
		}
		return false;
	}

	public Boolean validateOldPassword(Employee employee) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", employee.getId());
		params.put("loginPwd", new Md5Hash(employee.getLoginPwd(), SALT).toString());
		if (employeeDao.findByIdAndLoginPwd(params) > 0) {
			return true;
		}
		return false;
	}

	public Boolean updatePassword(Employee employee) {
		String password = new Md5Hash(employee.getNewPwd(), SALT).toString();
		employee.setLoginPwd(password);
		if (employeeDao.updatePassword(employee) > 0) {
			return true;
		}
		return false;
	}

	public Boolean isExists(Map<String, Object> paramMap) {
		if (employeeDao.countByParam(paramMap) > 0) {
			return false;
		}
		return true;
	}

	public Boolean hasEmployeeByDeptId(Long departmentId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("departmentId", departmentId.toString());
		if (employeeDao.countByParam(paramMap) > 0) {
			return true;
		}
		return false;
	}
	
	public List<Employee> listByDeptId(Long departmentId) {
		return employeeDao.listByDepartmentId(departmentId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveFunctionPermission(Map<String, Object> paramMap) {
		employeeDao.deletePermissionById(Long.parseLong(paramMap.get("employeeId").toString()));
		@SuppressWarnings("unchecked")
		List<Long> functionIds = (List<Long>) paramMap.get("functionIds");
		if (!functionIds.isEmpty()) {
			employeeDao.insertPermission(paramMap);
		}
	}

	public List<Long> listFunctionPermissionById(Long id) {
		return employeeDao.findPermissionById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveProjectPermission(Map<String, Object> paramMap) {
		employeeDao.deleteProjectManagerByProjectId(Long.parseLong(paramMap.get("projectId").toString()));
		employeeDao.insertProjectManager(paramMap);
	}

	public Long findProjectManagerByProjectId(Long projectId) {
		return employeeDao.findProjectManagerByProjectId(projectId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveWorkFlowPermission(Map<String, Object> paramMap) {
		employeeDao.deleteWorkFlowPermissionById(paramMap);
		@SuppressWarnings("unchecked")
		List<Long> employeeIds = (List<Long>) paramMap.get("employeeIds");
		if (!employeeIds.isEmpty()) {
			employeeDao.insertWorkFlowPermission(paramMap);
		}
	}

	public List<Map<String, Object>> listWorkFlowPermissionById(Map<String, Object> paramMap) {
		return employeeDao.findWorkFlowPermissionById(paramMap);
	}

}
