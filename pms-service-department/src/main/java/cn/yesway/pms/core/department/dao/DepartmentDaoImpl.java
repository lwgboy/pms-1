package cn.yesway.pms.core.department.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.department.entity.Department;

@Component("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

    @Override
    public int deleteByIds(long... ids) {
        String sqlId = getStatement("deleteByIds");
        return getSessionTemplate().update(sqlId, ids);
    }
    
    @Override
    public List<Department> listById(long id) {
    	String sqlId = getStatement("listById");
    	return getSessionTemplate().selectList(sqlId, id);
    }
    
}
