package cn.yesway.pms.core.cast.resource.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.cast.resource.entity.ResourceCast;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastDetail;

@Component("resourceCastDao")
public class ResourceCastDaoImpl extends BaseDaoImpl<ResourceCast> implements ResourceCastDao {

	@Override
	public void createDetails(ResourceCast resourceCast) {
		String sqlId = super.getStatement("createDetails");
		super.getSessionTemplate().insert(sqlId, resourceCast);
	}

	@Override
	public void deleteDetailById(Long id) {
		String sqlId = super.getStatement("deleteDetailById");
		super.getSessionTemplate().delete(sqlId, id);
	}

	@Override
	public List<ResourceCastDetail> findDetailsById(Long id) {
		String sqlId = super.getStatement("findDetailsById");
		return super.getSessionTemplate().selectList(sqlId, id);
	}

	@Override
	public void createReport(Long id) {
		String sqlId = super.getStatement("createReport");
		super.getSessionTemplate().insert(sqlId, id);
	}

}
