package cn.yesway.pms.core.cast.resource.dao;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.cast.resource.entity.Resource;

public interface ResourceDao extends BaseDao<Resource> {

    int deleteById(Long... ids);

}
