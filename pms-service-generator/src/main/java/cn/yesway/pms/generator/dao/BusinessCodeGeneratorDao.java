package cn.yesway.pms.generator.dao;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.generator.entity.GeneratorConfig;

public interface BusinessCodeGeneratorDao extends BaseDao<GeneratorConfig> {

	GeneratorConfig findBySeed(String seed);

}
