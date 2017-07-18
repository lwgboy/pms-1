package cn.yesway.pms.core.cast.employee.dao;

import java.util.Map;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleConfirm;

public interface WorkscheduleConfirmDao extends BaseDao<WorkscheduleConfirm> {

    PageBean<WorkscheduleConfirm> listByPageAndParam(PageParam pageParam, Map<String, Object> map);

    int updateStatus(Long... ids);
}
