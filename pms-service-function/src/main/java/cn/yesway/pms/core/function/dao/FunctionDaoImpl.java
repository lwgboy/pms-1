package cn.yesway.pms.core.function.dao;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.function.entity.Function;

@Component("functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao {

}
