package cn.yesway.pms.core.function.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.core.function.dao.FunctionDao;
import cn.yesway.pms.core.function.entity.Function;

@Component("functionBiz")
public class FunctionBiz {

    @Autowired
    private FunctionDao functionDao;

    public List<Function> list() {
        List<Function> functionTree = new ArrayList<>();
        List<Function> functions = listByParam(null);
        for (Function function : functions) {
            if (function.getLevel() == 1) {
                functionTree.add(function);
            } else {
                assemble(function, functionTree);
            }
        }
        return functionTree;
    }

    public List<Function> listByPermission(Long employeeId) {
        List<Function> functionTree = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);
        List<Function> functions = listByParam(params);
        for (Function function : functions) {
            if (function.getLevel() == 1) {
                functionTree.add(function);
            } else {
                assemble(function, functionTree);
            }
        }
        return functionTree;
    }

    public List<Function> listByWorkflow() {
        Map<String, Object> params = new HashMap<>();
        params.put("type", 1);
        return listByParam(params);
    }


    private List<Function> listByParam(Map<String, Object> params) {
        return functionDao.listByParam(params);
    }

    private void assemble(Function function, List<Function> functionTree) {
        for (Function f : functionTree) {
            if (f.getId() == function.getParentId()) {
                f.getChildren().add(function);
            } else {
                assemble(function, f.getChildren());
            }
        }
    }
    
}
