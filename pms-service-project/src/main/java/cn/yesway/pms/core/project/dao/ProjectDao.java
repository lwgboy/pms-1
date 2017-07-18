package cn.yesway.pms.core.project.dao;

import java.util.List;
import java.util.Map;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.project.entity.Project;

public interface ProjectDao extends BaseDao<Project> {

    public int deleteByIds(Long... ids);

    List<Project> listByStatus();

    PageBean<Project> listByEmployeeId(PageParam pageParam, Map<String, Object> paramMap);

    List<Project> listByRunning();
    
}
