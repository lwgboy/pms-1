package cn.yesway.pms.core.department.dao;

import java.util.List;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.department.entity.Department;

public interface DepartmentDao extends BaseDao<Department> {

    int deleteByIds(long... ids);

    List<Department> listById(long id);
    
}
