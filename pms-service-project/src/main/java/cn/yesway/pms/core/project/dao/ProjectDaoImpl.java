package cn.yesway.pms.core.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.project.entity.Project;

@Component("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {

    @Override
    public int deleteByIds(Long... ids) {
        String sqlId = getStatement("deleteByIds");
        return getSessionTemplate().update(sqlId, ids);
    }

    @Override
    public List<Project> listByStatus() {
        String sqlId = getStatement("listByStatus");
        return getSessionTemplate().selectList(sqlId);
    }

    @Override
    public PageBean<Project> listByEmployeeId(PageParam pageParam, Map<String, Object> paramMap) {
        return listByPageAndParam(pageParam, paramMap, "listByEmployeeId");
    }

    @Override
    public List<Project> listByRunning() {
        String sqlId = getStatement("listByRunning");
        return getSessionTemplate().selectList(sqlId);
    }
    
}
