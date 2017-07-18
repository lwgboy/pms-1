package cn.yesway.pms.web.console.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.cast.resource.biz.ResourceAmountBiz;
import cn.yesway.pms.core.cast.resource.entity.ResourceAmount;

@RestController
@RequestMapping(value = "/amount/resource")
public class ResourceAmountController {

	@Autowired
	private ResourceAmountBiz resourceAmountBiz;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public WebAppResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		// 获得上传文件夹的路径。
		String folderPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/resource");
		// 获得上传文件的文件名称。
		String fileName = file.getOriginalFilename();
		// 定义上传路径，如果目录不存在则创建
		File output = new File(folderPath, fileName);
		if (!output.exists()) {
			output.mkdirs();
		}
		// 上传文件
		file.transferTo(output);
		resourceAmountBiz.importFile(output.getPath());
		return new WebAppResult(ResultStatus.SUCCESS, "上传成功", null);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public WebAppResult create(@PathVariable Long id) {
		if (resourceAmountBiz.delete(id)) {
			return new WebAppResult(ResultStatus.SUCCESS, "删除成功", null);
		}
		return new WebAppResult(ResultStatus.SUCCESS, "删除失败", null);
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public WebAppResult updateStatus(@RequestBody ResourceAmount resourceAmount) {
		if (resourceAmountBiz.updateStatus(resourceAmount)) {
			return new WebAppResult(ResultStatus.SUCCESS, "更新成功", null);
		}
		return new WebAppResult(ResultStatus.SUCCESS, "更新失败", null);
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public WebAppResult findById(@PathVariable Long id) {
		ResourceAmount resourceAmount = resourceAmountBiz.findById(id);
		return new WebAppResult(ResultStatus.SUCCESS, "", resourceAmount);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public WebAppResult list(@RequestBody Map<String, Object> map) {
		PageParam page = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		PageBean<ResourceAmount> datas = resourceAmountBiz.findListByPageAndParam(page, map);
		return new WebAppResult(ResultStatus.SUCCESS, "", datas);
	}

	@RequestMapping(value = "/isExists", method = RequestMethod.POST)
	public WebAppResult isExists(@RequestBody Map<String, Object> map) {
		if (resourceAmountBiz.isExistsByParam(map)) {
			return new WebAppResult(ResultStatus.FAIL, "该月以为该资源上传过用量明细", null);
		}
		return new WebAppResult(ResultStatus.SUCCESS, "", null);
	}

}
