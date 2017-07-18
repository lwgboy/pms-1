package cn.yesway.pms.core.employee.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.employee.dao.EmployeeDao;
import cn.yesway.pms.core.employee.entity.Employee;

@Component("employeeDao")
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

	@Override
	public int insertDefaultPermission(Employee employee) {
		return getSessionTemplate().insert(getStatement("insertDefaultPermission"), employee);
	}

	@Override
	public int deleteByIds(long... ids) {
		String sqlId = getStatement("deleteByIds");
		return getSessionTemplate().update(sqlId, ids);
	}

	@Override
	public int resumedByIds(long... ids) {
		String sqlId = getStatement("resumedByIds");
		return getSessionTemplate().update(sqlId, ids);
	}

	@Override
	public Employee findByLoginNameAndLoginPwd(Map<String, Object> paramMap) {
		String sqlId = getStatement("findByLoginNameAndLoginPwd");
		return getSessionTemplate().selectOne(sqlId, paramMap);
	}

	@Override
	public int resetPassword(Long id) {
		String sqlId = getStatement("resetPassword");
		return getSessionTemplate().update(sqlId, id);
	}

	@Override
	public int findByIdAndLoginPwd(Map<String, Object> paramMap) {
		String sqlId = getStatement("findByIdAndLoginPwd");
		return getSessionTemplate().selectOne(sqlId, paramMap);
	}

	@Override
	public int updatePassword(Employee employee) {
		String sqlId = getStatement("updatePassword");
		return getSessionTemplate().update(sqlId, employee);
	}

	@Override
	public List<Employee> listByDepartmentId(Long departmentId) {
		String sqlId = getStatement("listByDepartmentId");
		return getSessionTemplate().selectList(sqlId, departmentId);
	}

	@Override
	public void deletePermissionById(Long id) {
		super.getSessionTemplate().delete(getStatement("deletePermissionById"), id);
	}

	@Override
	public void insertPermission(Map<String, Object> paramMap) {
		super.getSessionTemplate().insert(getStatement("insertPermission"), paramMap);
	}

	@Override
	public List<Long> findPermissionById(Long id) {
		return super.getSessionTemplate().selectList(getStatement("findPermissionById"), id);
	}

	@Override
	public void deleteProjectManagerByProjectId(Long projectId) {
		super.getSessionTemplate().delete(getStatement("deleteProjectManagerByProjectId"), projectId);
	}

	@Override
	public void insertProjectManager(Map<String, Object> paramMap) {
		super.getSessionTemplate().insert(getStatement("insertProjectManager"), paramMap);
	}

	@Override
	public Long findProjectManagerByProjectId(Long projectId) {
		return super.getSessionTemplate().selectOne(getStatement("findProjectManagerByProjectId"), projectId);
	}

	@Override
	public void deleteWorkFlowPermissionById(Map<String, Object> paramMap) {
		super.getSessionTemplate().delete(getStatement("deleteWorkFlowPermissionById"), paramMap);
	}

	@Override
	public void insertWorkFlowPermission(Map<String, Object> paramMap) {
		super.getSessionTemplate().insert(getStatement("insertWorkFlowPermission"), paramMap);
	}

	@Override
	public List<Map<String, Object>> findWorkFlowPermissionById(Map<String, Object> paramMap) {
		return super.getSessionTemplate().selectList(getStatement("findWorkFlowPermissionById"), paramMap);
	}

}
