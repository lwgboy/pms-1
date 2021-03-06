package cn.yesway.pms.web.console.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.report.biz.ProjectCastReportBiz;

@RestController
@RequestMapping(value = { "/report/project/cast" })
public class ProjectCastReportController {

	@Autowired
	private ProjectCastReportBiz projectCastReportBiz;

	@RequestMapping(value="/list", method = RequestMethod.POST)
	public WebAppResult report(@RequestBody Map<String, Object> map) {
		return new WebAppResult(ResultStatus.SUCCESS, "", projectCastReportBiz.selectWorkTime(map));
	}

}
