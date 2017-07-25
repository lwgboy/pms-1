package cn.yesway.pms.core.cast.employee.biz;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import cn.yesway.pms.core.cast.employee.dao.WorkscheduleMonthDao;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleMonth;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleMonthDetail;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleMonthProjectDetail;
import cn.yesway.pms.core.employee.entity.Employee;
import cn.yesway.pms.core.project.entity.Project;

@Component("workscheduleMonthBiz")
public class WorkscheduleMonthBiz {

	@Autowired
	private WorkscheduleMonthDao workscheduleMonthDao;

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
			Sheet sheet = workbook.getSheetAt(2);

			WorkscheduleMonth wsMonth = new WorkscheduleMonth();

			// 获得日期
			Date date = sheet.getRow(1).getCell(1).getDateCellValue();
			int worktime = (int) sheet.getRow(1).getCell(3).getNumericCellValue();
			wsMonth.setAffiliationDate(date);
			wsMonth.setWorktime(worktime);
			wsMonth.setStatus(ApprovalStatus.EDIT);
			handler(wsMonth, sheet);
			create(wsMonth);
		}
	}

	private void handler(WorkscheduleMonth wsMonth, Sheet sheet) {
		int rowCount = sheet.getLastRowNum();
		for (int i = 7; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			// 员工信息
			WorkscheduleMonthDetail detail = new WorkscheduleMonthDetail();
			String employeeCode = row.getCell(3).getStringCellValue();
			Employee employee = new Employee();
			employee.setCode(employeeCode);
			detail.setEmployee(employee);
			wsMonth.getDetails().add(detail);

			// 加载项目
			int j = 7;
			while (j < 100) {
				if (sheet.getRow(4).getCell(j) == null) {
					break;
				}
				String projectCode = sheet.getRow(4).getCell(j).getStringCellValue();
				if ("".equals(projectCode)) {
					break;
				}
				WorkscheduleMonthProjectDetail wmpd = new WorkscheduleMonthProjectDetail();
				Project project = new Project();
				project.setCode(projectCode);
				wmpd.setProject(project);
				// 计算工时
				BigDecimal worktime = new BigDecimal(0.0);
				if (row.getCell(j) != null) {
					double scale = row.getCell(j).getNumericCellValue();
					double wt = wsMonth.getWorktime() * scale;
					worktime = BigDecimal.valueOf(wt);
					worktime = worktime.setScale(1, BigDecimal.ROUND_HALF_UP);
				}
				wmpd.setWorktime(worktime);
				detail.getDetails().add(wmpd);
				j++;
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean create(WorkscheduleMonth workscheduleMonth) {
		workscheduleMonthDao.insert(workscheduleMonth);
		for (WorkscheduleMonthDetail detail : workscheduleMonth.getDetails()) {
			detail.setWorkscheduleMonthId(workscheduleMonth.getId());
			workscheduleMonthDao.insertDetail(detail);
			workscheduleMonthDao.insertProjectDetail(detail);
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean delete(Long id) {
		workscheduleMonthDao.deleteProjectDetail(id);
		workscheduleMonthDao.deleteDetail(id);
		workscheduleMonthDao.deleteById(id);
		return true;
	}

	public Boolean updateStatus(WorkscheduleMonth workscheduleMonth) {
		workscheduleMonthDao.updateStatus(workscheduleMonth);
		return true;
	}

	public PageBean<WorkscheduleMonth> listByPageAndParam(PageParam page, Map<String, Object> map) {
		return workscheduleMonthDao.listByPageAndParam(page, map);
	}

}
