package cn.yesway.pms.web.console.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.cast.resource.biz.ResourceCastBiz;
import cn.yesway.pms.core.cast.resource.entity.ResourceCast;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastReport;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastReportDetail;

@RestController
@RequestMapping(value = "/cast/resource")
public class ResourceCastController {

	@Autowired
	private ResourceCastBiz resourceCastBiz;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public WebAppResult create(@RequestBody ResourceCast resourceCast) {
		if (resourceCastBiz.create(resourceCast)) {
			return new WebAppResult(ResultStatus.SUCCESS, "保存成功", null);
		}
		return new WebAppResult(ResultStatus.FAIL, "保存失败", null);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebAppResult update(@RequestBody ResourceCast resourceCast) {
		if (resourceCastBiz.update(resourceCast)) {
			return new WebAppResult(ResultStatus.SUCCESS, "编辑成功", null);
		}
		return new WebAppResult(ResultStatus.FAIL, "编辑失败", null);
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public WebAppResult updateStatus(@RequestBody ResourceCast resourceCast) {
		if (resourceCastBiz.updateStatus(resourceCast)) {
			return new WebAppResult(ResultStatus.SUCCESS, "编辑成功", null);
		}
		return new WebAppResult(ResultStatus.FAIL, "编辑失败", null);
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public WebAppResult findById(@PathVariable Long id) {
		ResourceCast resourceCast = resourceCastBiz.findById(id);
		return new WebAppResult(ResultStatus.SUCCESS, "", resourceCast);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public WebAppResult list(@RequestBody Map<String, Object> map) {
		PageParam page = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		PageBean<ResourceCast> datas = resourceCastBiz.listByPageAndParam(page, map);
		return new WebAppResult(ResultStatus.SUCCESS, "", datas);
	}

	@RequestMapping(value = "/review/list", method = RequestMethod.POST)
	public WebAppResult reviewList(@RequestBody Map<String, Object> map) {
		PageParam page = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		PageBean<ResourceCastReport> datas = resourceCastBiz.reviewList(page, map);
		return new WebAppResult(ResultStatus.SUCCESS, "", datas);
	}

	@RequestMapping(value = "/isExists", method = RequestMethod.POST)
	public WebAppResult isExists(@RequestBody Map<String, Object> map) {
		if (resourceCastBiz.isExists(map)) {
			return new WebAppResult(ResultStatus.FAIL, "该月以为该资源上传过费用明细", null);
		}
		return new WebAppResult(ResultStatus.SUCCESS, "", null);
	}

	@RequestMapping(value = "/detail/{affiliationDate}", method = RequestMethod.GET)
	public WebAppResult listDetail(@PathVariable String affiliationDate) {
		List<ResourceCastReportDetail> datas = resourceCastBiz.listDetail(affiliationDate + "-01");
		return new WebAppResult(ResultStatus.SUCCESS, "", datas);
	}

	@RequestMapping(value = "/updateAllStatus", method = RequestMethod.POST)
	public WebAppResult updateAllStatus(@RequestBody Map<String, Object> map) {
		if (resourceCastBiz.updateStatus(map)) {
			return new WebAppResult(ResultStatus.SUCCESS, "编辑成功", null);
		}
		return new WebAppResult(ResultStatus.FAIL, "编辑失败", null);
	}
}
