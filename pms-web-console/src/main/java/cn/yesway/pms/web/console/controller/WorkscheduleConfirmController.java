package cn.yesway.pms.web.console.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.entity.WebConstant;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.cast.employee.biz.WorkscheduleConfirmBiz;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleConfirm;
import cn.yesway.pms.core.employee.entity.Employee;

@RestController
@RequestMapping("/cast/employee/workSchedule/confirm")
public class WorkscheduleConfirmController {

    @Autowired
    private WorkscheduleConfirmBiz workscheduleConfirmBiz;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public WebAppResult list(@RequestBody Map<String, Object> map, HttpSession httpSession) {
        Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
        if (employee != null) {
            map.put("employeeId", employee.getId());
            PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
                    Integer.parseInt(map.get("numPerPage").toString()));
            PageBean<WorkscheduleConfirm> data = workscheduleConfirmBiz.listByPageAndParam(pageParam, map);
            return new WebAppResult(ResultStatus.SUCCESS, "", data);
        }
        return new WebAppResult(ResultStatus.TIMEOUT, "", null);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WebAppResult update(@RequestBody Long[] ids) {
        if (workscheduleConfirmBiz.updateStatus(ids)) {
            return new WebAppResult(ResultStatus.SUCCESS, "", null);
        }
        return new WebAppResult(ResultStatus.FAIL, "", null);
    }
    
}
