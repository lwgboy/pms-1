package cn.yesway.pms.core.cast.expense.dao;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.cast.expense.entity.Expense;

@Component("expenseDao")
public class ExpenseDaoImpl extends BaseDaoImpl<Expense> implements ExpenseDao {

	@Override
	public int deleteByIds(Long... ids) {		
		String sqlId = getStatement("deleteByIds");
        return super.getSessionTemplate().update(sqlId, ids);
	}

}
