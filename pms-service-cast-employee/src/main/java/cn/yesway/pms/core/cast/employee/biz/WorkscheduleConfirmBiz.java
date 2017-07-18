package cn.yesway.pms.core.cast.employee.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.employee.dao.WorkscheduleConfirmDao;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleConfirm;

@Component("workscheduleConfirmBiz")
public class WorkscheduleConfirmBiz {

    @Autowired
    private WorkscheduleConfirmDao workscheduleConfirmBiz;

    public PageBean<WorkscheduleConfirm> listByPageAndParam(PageParam pageParam, Map<String, Object> map) {
        return workscheduleConfirmBiz.listByPageAndParam(pageParam, map);
    }

    public Boolean updateStatus(Long... ids) {
        if (workscheduleConfirmBiz.updateStatus(ids) > 0) {
            return true;
        }
        return false;
    }
}
