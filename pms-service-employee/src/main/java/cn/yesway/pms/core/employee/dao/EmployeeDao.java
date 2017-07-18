package cn.yesway.pms.core.employee.dao;

import java.util.List;
import java.util.Map;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.employee.entity.Employee;

public interface EmployeeDao extends BaseDao<Employee> {

	int insertDefaultPermission(Employee employee);
	
    int deleteByIds(long... ids);

    int resumedByIds(long... ids);

    Employee findByLoginNameAndLoginPwd(Map<String, Object> paramMap);

    int resetPassword(Long id);

    int findByIdAndLoginPwd(Map<String, Object> paramMap);

    int updatePassword(Employee employee);
    
    List<Employee> listByDepartmentId(Long departmentId);

    void deletePermissionById(Long id);

    void insertPermission(Map<String, Object> paramMap);

    List<Long> findPermissionById(Long id);

    void deleteProjectManagerByProjectId(Long project);

    void insertProjectManager(Map<String, Object> paramMap);

    Long findProjectManagerByProjectId(Long projectId);

    void deleteWorkFlowPermissionById(Map<String, Object> paramMap);

    void insertWorkFlowPermission(Map<String, Object> paramMap);

    List<Map<String, Object>> findWorkFlowPermissionById(Map<String, Object> paramMap);

}
