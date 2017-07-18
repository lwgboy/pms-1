package cn.yesway.pms.core.cast.expense.biz;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.expense.dao.ExpenseDao;
import cn.yesway.pms.core.cast.expense.entity.Expense;

@Component("expenseBiz")
public class ExpenseBiz {

    @Autowired
    private ExpenseDao expenseDao;

    public long create(Expense expense) {

        LocalDateTime time = LocalDateTime.now();
        expense.setGmtCreate(time);
        expense.setGmtModified(time);
        expense.setDeleted(false);
        return expenseDao.insert(expense);
    }

    public Boolean update(Expense expense) {
        expense.setGmtModified(LocalDateTime.now());
        int result = expenseDao.update(expense);
        if (result == 0) {
            return false;
        }
        return true;
    }

    public Boolean deleteByIds(Long... ids) {
        int result = expenseDao.deleteByIds(ids);
        if (result == 0) {
            return false;
        }
        return true;
    }

    public Expense findById(Long id) {
        return expenseDao.findById(id);
    }

    public Boolean isExists(Map<String, Object> paramMap) {
        if (expenseDao.countByParam(paramMap) > 0) {
            return true;
        }
        return false;
    }

    public PageBean<Expense> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap) {
        return expenseDao.listByPageAndParam(pageParam, paramMap);
    }

    public List<Expense> listByParam(Map<String, Object> map) {
        return expenseDao.listByParam(map);
    }
    
}
