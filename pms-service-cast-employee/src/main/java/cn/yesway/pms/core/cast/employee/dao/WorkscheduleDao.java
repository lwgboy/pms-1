package cn.yesway.pms.core.cast.employee.dao;

import java.util.List;
import java.util.Map;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.employee.entity.Workschedule;

public interface WorkscheduleDao extends BaseDao<Workschedule> {

    void insertDetail(Workschedule workschedule);

    int updateStatus(Workschedule workschedule);

    int countByEmployeIdAndCreateDate(Map<String, Object> map);

    PageBean<Workschedule> findByStatus(PageParam pageParam, Map<String, Object> map);
    
    List<Workschedule> listByParam(Map<String, Object> map);
    
}
