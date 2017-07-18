package cn.yesway.pms.web.console.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.entity.WebConstant;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.employee.entity.Employee;
import cn.yesway.pms.core.function.biz.FunctionBiz;
import cn.yesway.pms.core.function.entity.Function;

@RestController
@RequestMapping(value = { "/function" })
public class FunctionController {

    private static final Log LOGGER = LogFactory.getLog(FunctionController.class);
    @Autowired
    private FunctionBiz functionBiz;

    @RequestMapping(value = "/listByPermission", method = RequestMethod.GET)
    public WebAppResult listByEmployeeId(HttpSession httpSession) {
        Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
        if (employee != null) {
            List<Function> functions = functionBiz.listByPermission(employee.getId());
            LOGGER.debug(functions.size());
            return new WebAppResult(ResultStatus.SUCCESS, "", functions);
        }
        return new WebAppResult(ResultStatus.TIMEOUT, "", null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public WebAppResult list() {
        List<Function> functions = functionBiz.list();
        return new WebAppResult(ResultStatus.SUCCESS, "", functions);
    }

    @RequestMapping(value = "/listByWorkflow", method = RequestMethod.GET)
    public WebAppResult listByWorkflow() {
        List<Function> functions = functionBiz.listByWorkflow();
        return new WebAppResult(ResultStatus.SUCCESS, "", functions);
    }

}
