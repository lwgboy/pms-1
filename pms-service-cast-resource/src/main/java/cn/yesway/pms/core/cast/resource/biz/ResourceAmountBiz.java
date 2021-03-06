package cn.yesway.pms.core.cast.resource.biz;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yesway.pms.common.enums.ApprovalStatus;
import cn.yesway.pms.common.exception.EmployeeSalaryExistsException;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.resource.dao.ResourceAmountDao;
import cn.yesway.pms.core.cast.resource.entity.Resource;
import cn.yesway.pms.core.cast.resource.entity.ResourceAmount;
import cn.yesway.pms.core.cast.resource.entity.ResourceAmountDetail;
import cn.yesway.pms.core.cast.resource.entity.ResourceAmountProjectDetail;
import cn.yesway.pms.core.project.entity.Project;

@Component("resourceAmountBiz")
public class ResourceAmountBiz {

	@Autowired
	private ResourceAmountDao resourceAmountDao;

	public void importFile(String filePath) throws IOException {
		// 读取excel文件
		try (InputStream in = new FileInputStream(filePath)) {
			// 根据excel的后缀创建工作簿
			Workbook workbook = null;
			if (filePath.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(in);
			} else if (filePath.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(in);
			}
			// 如果没有工作表则不读取
			if (workbook == null || workbook.getNumberOfSheets() == 0) {
				throw new EmployeeSalaryExistsException(null, "", "上传的excel中没有sheets");
			}
			// 读取第一个工作表
			Sheet sheet = workbook.getSheetAt(0);

			ResourceAmount resourceAmount = new ResourceAmount();
			// 获得日期
			Date date = sheet.getRow(0).getCell(1).getDateCellValue();
			resourceAmount.setAffiliationDate(date);
			resourceAmount.setStatus(ApprovalStatus.EDIT);
			handler(resourceAmount, sheet);
			create(resourceAmount);
		}
	}

	private void handler(ResourceAmount resourceAmount, Sheet sheet) {
		int rowCount = sheet.getLastRowNum();
		for (int i = 2; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			ResourceAmountDetail amountDetail = new ResourceAmountDetail();
			String resourceCode = row.getCell(0).getStringCellValue();
			String resourceName = row.getCell(1).getStringCellValue();
			Resource resource = new Resource();
			resource.setCode(resourceCode);
			resource.setName(resourceName);
			amountDetail.setResource(resource);
			resourceAmount.getDetails().add(amountDetail);
			int j = 2;
			while (j < 100) {
				if (sheet.getRow(0).getCell(j) == null) {
					break;
				}
				String projectCode = sheet.getRow(0).getCell(j).getStringCellValue();
				if ("合计".equals(projectCode)) {
					break;
				}
				if (row.getCell(j) == null) {
					j++;
					continue;
				} else {
					double scale = row.getCell(j).getNumericCellValue();
					if (scale == 0.0) {
						j++;
						continue;
					}
					ResourceAmountProjectDetail projectDetail = new ResourceAmountProjectDetail();
					Project project = new Project();
					project.setCode(projectCode);
					projectDetail.setProject(project);
					projectDetail.setScale(scale);
					amountDetail.getDetails().add(projectDetail);
				}
				j++;
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private Boolean create(ResourceAmount resourceAmount) {
		if (resourceAmountDao.insert(resourceAmount) > 0) {
			for (ResourceAmountDetail detail : resourceAmount.getDetails()) {
				detail.setResourceAmountId(resourceAmount.getId());
				resourceAmountDao.insertDetail(detail);
				resourceAmountDao.insertProjectDetail(detail);
			}
			return true;
		}
		return false;
	}

	public Boolean delete(Long id) {
		resourceAmountDao.deleteProjectDetail(id);
		resourceAmountDao.deleteDetail(id);
		resourceAmountDao.delete(id);
		return true;
	}

	public ResourceAmount findById(Long id) {
		return resourceAmountDao.findById(id);
	}

	public PageBean<ResourceAmount> findListByPageAndParam(PageParam page, Map<String, Object> map) {
		return resourceAmountDao.listByPageAndParam(page, map);
	}

	public Boolean isExistsByParam(Map<String, Object> map) {
		if (resourceAmountDao.countByParam(map) > 0) {
			return true;
		}
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean updateStatus(ResourceAmount resourceAmount) {
		if (resourceAmountDao.updateStatus(resourceAmount) > 0) {
			if (resourceAmount.getStatus() == ApprovalStatus.REPORT) {
				resourceAmountDao.createReport(resourceAmount.getId());
			}
			return true;
		}
		return false;
	}

}
