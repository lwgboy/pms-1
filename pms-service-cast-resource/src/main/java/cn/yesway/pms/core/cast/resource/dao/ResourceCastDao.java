package cn.yesway.pms.core.cast.resource.dao;

import java.util.List;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.cast.resource.entity.ResourceCast;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastDetail;

public interface ResourceCastDao extends BaseDao<ResourceCast> {

	void createDetails(ResourceCast resourceCast);

	void deleteDetailById(Long id);

	List<ResourceCastDetail> findDetailsById(Long id);

	void createReport(Long id);

}
