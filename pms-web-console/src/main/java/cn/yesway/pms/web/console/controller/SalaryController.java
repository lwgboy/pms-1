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
import cn.yesway.pms.core.cast.employee.biz.EmployeeSalaryBiz;
import cn.yesway.pms.core.cast.employee.entity.EmployeeSalary;

@RestController
@RequestMapping("/cast/employee")
public class SalaryController {

	@Autowired
	private EmployeeSalaryBiz employeeSalaryBiz;

	@RequestMapping(value = "/salary/upload", method = RequestMethod.POST)
	public WebAppResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		// 获得上传文件夹的路径。
		String folderPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/salary");
		// 获得上传文件的文件名称。
		String fileName = file.getOriginalFilename();
		// 定义上传路径，如果目录不存在则创建
		File output = new File(folderPath, fileName);
		if (!output.exists()) {
			output.mkdirs();
		}
		// 上传文件
		file.transferTo(output);
		// 读取薪资表
		employeeSalaryBiz.importSalaryFile(output.getPath());
		return new WebAppResult(ResultStatus.SUCCESS, "上传成功", null);
	}

	@RequestMapping(value = "/salary/list", method = RequestMethod.POST)
	public WebAppResult list(@RequestBody Map<String, Object> map) {
		// 获得分页信息
		PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		PageBean<EmployeeSalary> data = employeeSalaryBiz.findListByPageAndParam(pageParam, map);
		return new WebAppResult(ResultStatus.SUCCESS, "", data);
	}

	@RequestMapping(value = "salary/delete/{id}", method = RequestMethod.GET)
	public WebAppResult delete(@PathVariable long id) {
		// 薪资表在提交的状态下不可以删除。
		if (!employeeSalaryBiz.isSubmit(id)) {
			employeeSalaryBiz.deleteById(id);
			return new WebAppResult(ResultStatus.SUCCESS, "删除成功", null);
		}
		return new WebAppResult(ResultStatus.FAIL, "已提交的薪资表不能删除", null);

	}

	@RequestMapping(value = "salary/update", method = RequestMethod.POST)
	public WebAppResult submit(@RequestBody EmployeeSalary employeeSalary) {
		// 薪资表在提交的状态下不可以删除。
		if (employeeSalaryBiz.update(employeeSalary)) {
			return new WebAppResult(ResultStatus.SUCCESS, "提交成功", null);
		}
		return new WebAppResult(ResultStatus.FAIL, "提交成功", null);
	}

}
