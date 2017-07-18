package cn.yesway.pms.web.console.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.employee.biz.EmployeeBiz;

@RestController
@RequestMapping(value = { "/permission" })
public class PermissionController {

    @Autowired
    private EmployeeBiz employeeBiz;

    @RequestMapping(value = "function/{id}", method = RequestMethod.GET)
    public WebAppResult findByEmployeeId(@PathVariable long id) {
        // 根据用户主键获得其权限
        List<Long> functionIds = employeeBiz.listFunctionPermissionById(id);
        return new WebAppResult(ResultStatus.SUCCESS, "", functionIds);
    }

    @RequestMapping(value = "function/save", method = RequestMethod.POST)
    public WebAppResult savePermission(@RequestBody Map<String, Object> map) {
        employeeBiz.saveFunctionPermission(map);
        return new WebAppResult(ResultStatus.SUCCESS, "功能权限已保存", null);
    }

    @RequestMapping(value = "workflow/list", method = RequestMethod.POST)
    public WebAppResult findWorkFlowByEmployeeId(@RequestBody Map<String, Object> map) {
        List<Map<String, Object>> datas = employeeBiz.listWorkFlowPermissionById(map);
        return new WebAppResult(ResultStatus.SUCCESS, "", datas);
    }

    @RequestMapping(value = "workflow/save", method = RequestMethod.POST)
    public WebAppResult saveWorkFlowPermission(@RequestBody Map<String, Object> map) {
        employeeBiz.saveWorkFlowPermission(map);
        return new WebAppResult(ResultStatus.SUCCESS, "审批权限已保存", null);
    }

    @RequestMapping(value = "project/{id}", method = RequestMethod.GET)
    public WebAppResult findProjectManagerByProjectId(@PathVariable long id) {
        Long empId = employeeBiz.findProjectManagerByProjectId(id);
        return new WebAppResult(ResultStatus.SUCCESS, "", empId);
    }

    @RequestMapping(value = "project/save", method = RequestMethod.POST)
    public WebAppResult saveProjectPermission(@RequestBody Map<String, Object> map) {
        employeeBiz.saveProjectPermission(map);
        return new WebAppResult(ResultStatus.SUCCESS, "项目经理设置成功", null);
    }

}
