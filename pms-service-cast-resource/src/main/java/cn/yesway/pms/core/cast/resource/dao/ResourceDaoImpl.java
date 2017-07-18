package cn.yesway.pms.core.cast.resource.dao;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.cast.resource.entity.Resource;

@Component("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

    @Override
    public int deleteById(Long... ids) {
        String sqlId = getStatement("deleteById");
        return getSessionTemplate().update(sqlId, ids);
    }

}
