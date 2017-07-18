package cn.yesway.pms.core.cast.expense.dao;

import java.util.List;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCast;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCastDetail;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCastReportData;

public interface ExpenseCastDao extends BaseDao<ExpenseCast> {

	public void deleteDetailById(Long id);

	public void insertDetail(ExpenseCast expenseCast);

	public List<ExpenseCastDetail> findDetailsById(Long id);
	
	public List<ExpenseCastReportData> findReportById(Long id);

}
