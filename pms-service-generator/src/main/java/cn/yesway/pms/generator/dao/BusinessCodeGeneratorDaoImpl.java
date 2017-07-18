package cn.yesway.pms.generator.dao;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.generator.entity.GeneratorConfig;

@Component("businessCodeGeneratorDao")
public class BusinessCodeGeneratorDaoImpl extends BaseDaoImpl<GeneratorConfig> implements BusinessCodeGeneratorDao {

	@Override
	public GeneratorConfig findBySeed(String seed) {
		String sqlId = getStatement("findBySeed");
		return getSessionTemplate().selectOne(sqlId, seed);
	}

}
