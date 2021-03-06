package cn.yesway.pms.core.cast.resource.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.cast.resource.entity.ResourceAmount;
import cn.yesway.pms.core.cast.resource.entity.ResourceAmountDetail;

@Component("resourceAmountDao")
public class ResourceAmountDaoImpl extends BaseDaoImpl<ResourceAmount> implements ResourceAmountDao {

	@Override
	public int insertDetail(ResourceAmountDetail resourceAmountDetail) {
		String sqlId = super.getStatement("insertDetail");
		return super.getSessionTemplate().insert(sqlId, resourceAmountDetail);
	}

	@Override
	public int insertProjectDetail(ResourceAmountDetail resourceAmountDetail) {
		String sqlId = super.getStatement("insertProjectDetail");
		return super.getSessionTemplate().insert(sqlId, resourceAmountDetail);
	}

	@Override
	public int deleteProjectDetail(Long id) {
		String sqlId = super.getStatement("deleteProjectDetail");
		return super.getSessionTemplate().delete(sqlId, id);
	}

	@Override
	public int deleteDetail(Long id) {
		String sqlId = super.getStatement("deleteDetail");
		return super.getSessionTemplate().delete(sqlId, id);
	}

	@Override
	public int delete(Long id) {
		String sqlId = super.getStatement("delete");
		return super.getSessionTemplate().delete(sqlId, id);
	}

	@Override
	public int updateStatus(ResourceAmount resourceAmount) {
		String sqlId = super.getStatement("updateStatus");
		return super.getSessionTemplate().update(sqlId, resourceAmount);
	}

	@Override
	public List<ResourceAmountDetail> listByResourceIdAndCreateDate(Map<String, Object> map) {
		String sqlId = super.getStatement("listByResourceIdAndCreateDate");
		return super.getSessionTemplate().selectList(sqlId, map);
	}
	
	@Override
	public void createReport(Long id) {
		String sqlId = super.getStatement("createReport");
		super.getSessionTemplate().insert(sqlId, id);
	}
}
