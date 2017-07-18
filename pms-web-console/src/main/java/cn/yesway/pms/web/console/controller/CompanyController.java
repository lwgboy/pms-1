package cn.yesway.pms.web.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.company.biz.CompanyBiz;

@RestController
@RequestMapping(value = { "/company" })
public class CompanyController {

    @Autowired
    private CompanyBiz companyBiz;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public WebAppResult list() {
        return new WebAppResult(ResultStatus.SUCCESS, "", companyBiz.list());
    }

}
