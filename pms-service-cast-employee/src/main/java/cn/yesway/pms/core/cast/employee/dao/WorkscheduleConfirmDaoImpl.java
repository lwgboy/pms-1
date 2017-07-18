package cn.yesway.pms.core.cast.employee.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleConfirm;

@Component("workscheduleConfirmDao")
public class WorkscheduleConfirmDaoImpl extends BaseDaoImpl<WorkscheduleConfirm> implements WorkscheduleConfirmDao {

    @Override
    public PageBean<WorkscheduleConfirm> listByPageAndParam(PageParam pageParam, Map<String, Object> map) {
        return super.listByPageAndParam(pageParam, map, "listByPageAndParam");
    }

    @Override
    public int updateStatus(Long... ids) {
        return super.getSessionTemplate().update(getStatement("updateStatus"), ids);
    }

}
