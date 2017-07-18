package cn.yesway.pms.core.cast.employee.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.employee.entity.Workschedule;

@Component("workscheduleDao")
public class WorkscheduleDaoImpl extends BaseDaoImpl<Workschedule> implements WorkscheduleDao {

    @Override
    public void insertDetail(Workschedule workschedule) {
        String sqlId = super.getStatement("insertDetail");
        super.getSessionTemplate().insert(sqlId, workschedule);
    }

    @Override
    public int updateStatus(Workschedule workschedule) {
        String sqlId = super.getStatement("updateStatus");
        return super.getSessionTemplate().update(sqlId, workschedule);
    }

    @Override
    public int countByEmployeIdAndCreateDate(Map<String, Object> map) {
        String sqlId = super.getStatement("countByEmployeIdAndCreateDate");
        return super.getSessionTemplate().selectOne(sqlId, map);
    }

    @Override
    public PageBean<Workschedule> findByStatus(PageParam pageParam, Map<String, Object> map) {
        return super.listByPageAndParam(pageParam, map, "findByStatus");
    }
    
    @Override
    public List<Workschedule> listByParam(Map<String, Object> map) {
    	return super.listByParam(map);
    }

}
