package cn.yesway.pms.web.console.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.cast.expense.biz.ExpenseBiz;
import cn.yesway.pms.core.cast.expense.entity.Expense;

@RestController
@RequestMapping(value = { "/expense" })
public class ExpenseController {

    @Autowired
    private ExpenseBiz expenseBiz;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public WebAppResult list(@RequestBody Map<String, Object> map) {
        PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
                Integer.parseInt(map.get("numPerPage").toString()));
        WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", expenseBiz.listByPageAndParam(pageParam, map));
        return result;
    }

    @RequestMapping(value = "/list/param", method = RequestMethod.POST)
    public WebAppResult listByParam(@RequestBody Map<String, Object> map) {
        List<Expense> datas = expenseBiz.listByParam(map);
        return new WebAppResult(ResultStatus.SUCCESS, "", datas);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public WebAppResult create(@RequestBody Expense expense) {
        long id = expenseBiz.create(expense);
        WebAppResult result;
        if (id > 0) {
            result = new WebAppResult(ResultStatus.SUCCESS, "创建成功。", id);
        } else {
            result = new WebAppResult(ResultStatus.FAIL, "创建失败。", null);
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebAppResult findById(@PathVariable long id) {
        Expense expense = expenseBiz.findById(id);
        WebAppResult result;
        if (expense != null) {
            result = new WebAppResult(ResultStatus.SUCCESS, "", expense);
        } else {
            result = new WebAppResult(ResultStatus.FAIL, "没有查找相关信息。", null);
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public WebAppResult update(@RequestBody Expense expense) {
        WebAppResult result;
        if (expenseBiz.update(expense)) {
            result = new WebAppResult(ResultStatus.SUCCESS, "修改成功。", expense.getId());
        } else {
            result = new WebAppResult(ResultStatus.FAIL, "修改失败。", null);
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public WebAppResult delete(@RequestBody Long[] ids) {
        WebAppResult result;
        if (expenseBiz.deleteByIds(ids)) {
            result = new WebAppResult(ResultStatus.SUCCESS, "删除成功。", null);
        } else {
            result = new WebAppResult(ResultStatus.FAIL, "删除失败。", null);
        }
        return result;
    }

    @RequestMapping(value = "isExistsCode", method = RequestMethod.POST)
    public WebAppResult isExistsCode(@RequestBody Map<String, Object> params) {
        WebAppResult result;
        if (!expenseBiz.isExists(params)) {

            result = new WebAppResult(ResultStatus.SUCCESS, "", true);
        } else {

            result = new WebAppResult(ResultStatus.FAIL, "该编号已存在", false);
        }
        return result;
    }

}
