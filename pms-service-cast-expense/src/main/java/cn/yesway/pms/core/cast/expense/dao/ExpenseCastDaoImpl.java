package cn.yesway.pms.core.cast.expense.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCast;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCastDetail;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCastReportData;

@Component("expenseCastDao")
public class ExpenseCastDaoImpl extends BaseDaoImpl<ExpenseCast> implements ExpenseCastDao {

	@Override
	public void deleteDetailById(Long id) {
		String sqlId = getStatement("deleteDetailById");
		getSessionTemplate().delete(sqlId, id);
	}

	@Override
	public void insertDetail(ExpenseCast expenseCast) {
		String sqlId = getStatement("insertDetail");
		getSessionTemplate().insert(sqlId, expenseCast);
	}

	@Override
	public List<ExpenseCastDetail> findDetailsById(Long id) {
		String sqlId = getStatement("findDetailsById");
		return getSessionTemplate().selectList(sqlId, id);
	}

	@Override
	public List<ExpenseCastReportData> findReportById(Long id) {
		String sqlId = getStatement("findReportById");
		return getSessionTemplate().selectList(sqlId, id);
	}

}
