package cn.yesway.pms.core.cast.employee.dao;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.cast.employee.entity.EmployeeSalary;

/**
 * 员工薪资费用功能模块的数据访问类。
 * 
 * @author XuePeng
 */
@Component("employeeSalaryDao")
public class EmployeeSalaryDaoImpl extends BaseDaoImpl<EmployeeSalary> implements EmployeeSalaryDao {

    /**
     * 创建员工薪资明细。
     * 
     * @param 员工薪资明细实体对象。
     * @return 影响的数据库行数。
     */
    @Override
    public int insertDetail(EmployeeSalary employeeSalary) {
        String sqlId = super.getStatement("insertDetail");
        return super.getSessionTemplate().insert(sqlId, employeeSalary);
    }

}
