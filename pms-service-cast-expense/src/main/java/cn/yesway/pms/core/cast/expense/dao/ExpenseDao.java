package cn.yesway.pms.core.cast.expense.dao;


import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.cast.expense.entity.Expense;

public interface ExpenseDao extends BaseDao<Expense> {

    public int deleteByIds(Long... ids);
    
}
