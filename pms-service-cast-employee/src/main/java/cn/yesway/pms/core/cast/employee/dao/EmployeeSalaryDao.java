package cn.yesway.pms.core.cast.employee.dao;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.cast.employee.entity.EmployeeSalary;

public interface EmployeeSalaryDao extends BaseDao<EmployeeSalary> {

    int insertDetail(EmployeeSalary employeeSalary);
    
}
