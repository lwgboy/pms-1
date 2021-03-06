package cn.yesway.pms.core.cast.employee.biz;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import cn.yesway.pms.common.core.secret.ASESecret;
import cn.yesway.pms.common.enums.ApprovalStatus;
import cn.yesway.pms.common.exception.EmployeeSalaryExistsException;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.employee.dao.EmployeeSalaryDao;
import cn.yesway.pms.core.cast.employee.entity.EmployeeSalary;
import cn.yesway.pms.core.cast.employee.entity.EmployeeSalaryDetail;

@Component("employeeSalaryBiz")
public class EmployeeSalaryBiz {

	@Autowired
	private EmployeeSalaryDao employeeSalaryDao;
	private static final String SALT = "Cn>YseWay9%!(0";

	public void importSalaryFile(String filePath) throws IOException {
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
			// 获得薪资表的日期
			Date date = sheet.getRow(0).getCell(1).getDateCellValue();
			if (isExists(date)) {
				throw new EmployeeSalaryExistsException(null, "", "当月的薪资表在系统中已存在");
			}
			int workDay = (int) sheet.getRow(0).getCell(3).getNumericCellValue();
			// 创建员工薪资表
			EmployeeSalary employeeSalary = builder(sheet, date, workDay);
			insert(employeeSalary);
		}
	}

	private Boolean isExists(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		Map<String, Object> map = new HashMap<>();
		map.put("year", year);
		map.put("month", month + 1);
		if (employeeSalaryDao.countByParam(map) > 0) {
			return true;
		}
		return false;
	}

	public PageBean<EmployeeSalary> findListByPageAndParam(PageParam pageParam, Map<String, Object> paramMap) {
		return employeeSalaryDao.listByPageAndParam(pageParam, paramMap);
	}

	public Boolean isSubmit(Long id) {
		EmployeeSalary employeeSalary = employeeSalaryDao.findById(id);
		if (employeeSalary.getStatus() == ApprovalStatus.REPORT) {
			return true;
		}
		return false;
	}

	public void deleteById(Long id) {
		employeeSalaryDao.deleteById(id);
	}

	public Boolean update(EmployeeSalary employeeSalary) {
		if (employeeSalaryDao.update(employeeSalary) > 0) {
			return true;
		}
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean insert(EmployeeSalary employeeSalary) {
		// 写入数据库
		if (employeeSalaryDao.insert(employeeSalary) > 0) {
			employeeSalaryDao.insertDetail(employeeSalary);
			return true;
		}
		return false;
	}

	private EmployeeSalary builder(Sheet sheet, Date date, int workDay) {
		// 创建员工薪资实体对象
		EmployeeSalary employeeSalary = new EmployeeSalary();
		// 设置上传日期
		employeeSalary.setCreateDate(date);
		employeeSalary.setWorkDay(workDay);
		// 设置状态为编辑
		employeeSalary.setStatus(ApprovalStatus.EDIT);
		// 创建薪资明细。
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {
			BigDecimal monthly_salary = new BigDecimal(0.0);
			BigDecimal social_security = new BigDecimal(0.0);
			BigDecimal labor_union_salary = new BigDecimal(0.0);
			BigDecimal hourly_salary = new BigDecimal(0.0);
			BigDecimal compensation_salary = new BigDecimal(0.0);
			Row row = sheet.getRow(i);
			if (row != null) {
				// 员工编号
				EmployeeSalaryDetail detail = new EmployeeSalaryDetail();
				if (row.getCell(0) != null) {
					String code = row.getCell(0).getStringCellValue();
					detail.setEmployeeCode(code);
				} else {
					throw new EmployeeSalaryExistsException(null, "", "第" + (i + 1) + "行的员工编号不能为空");
				}
				// 月薪
				double salary_m;
				if (row.getCell(2) == null) {
					salary_m = 0.0;
				} else {
					salary_m = row.getCell(2).getNumericCellValue();
				}
				monthly_salary = BigDecimal.valueOf(salary_m);
				detail.setMonthlySalary(ASESecret.parseByte2HexStr(ASESecret.encrypt(monthly_salary.toString(), SALT)));
				// 保险
				double salary_s;
				if (row.getCell(3) == null) {
					salary_s = 0.0;
				} else {
					salary_s = row.getCell(3).getNumericCellValue();
				}
				social_security = BigDecimal.valueOf(salary_s);
				detail.setSocialSecurity(
						ASESecret.parseByte2HexStr(ASESecret.encrypt(social_security.toString(), SALT)));
				// 公会费
				double labor_union_s = salary_m * 0.02;
				labor_union_salary = BigDecimal.valueOf(labor_union_s);
				detail.setLaborUnionSalary(
						ASESecret.parseByte2HexStr(ASESecret.encrypt(labor_union_salary.toString(), SALT)));
				// 时薪 = 应发工资 + 保险 + 公会费 / 当月工资天数 / 8
				hourly_salary = BigDecimal.valueOf((salary_m + salary_s + labor_union_s) / workDay / 8);
				detail.setHourlySalary(ASESecret.parseByte2HexStr(ASESecret.encrypt(hourly_salary.toString(), SALT)));
				// 离职补偿
				double compensation_s;
				if (row.getCell(4) != null) {
					compensation_s = row.getCell(4).getNumericCellValue();
				} else {
					compensation_s = 0.0;
				}
				compensation_salary = BigDecimal.valueOf(compensation_s);
				detail.setCompensationSalary(
						ASESecret.parseByte2HexStr(ASESecret.encrypt(compensation_salary.toString(), SALT)));
				// 添加明细到薪资表中
				employeeSalary.getDetails().add(detail);
				// 计算总费用
				employeeSalary.setSalary(employeeSalary.getSalary().add(monthly_salary));
				employeeSalary.setSocialSecurity(employeeSalary.getSocialSecurity().add(social_security));
				employeeSalary.setCompensationSalary(employeeSalary.getCompensationSalary().add(compensation_salary));
				employeeSalary.setLaborUnionSalary(employeeSalary.getLaborUnionSalary().add(labor_union_salary));
			}
		}
		return employeeSalary;
	}

}
