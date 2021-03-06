package cn.yesway.pms.core.cast.resource.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastReport;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastReportDetail;

@Component("resourceCastReportDao")
public class ResourceCastReportDaoImpl extends BaseDaoImpl<ResourceCastReport> implements ResourceCastReportDao {

	@Override
	public List<ResourceCastReportDetail> listDetail(String affiliationDate) {
		String sqlId = getStatement("listDetail");
		return getSessionTemplate().selectList(sqlId, affiliationDate);
	}

	@Override
	public void updateStatus(Map<String, Object> map) {
		String sqlId = getStatement("updateStatus");
		getSessionTemplate().update(sqlId, map);
	}

	@Override
	public void deleteByReport(String affiliationDate) {
		String sqlId = getStatement("deleteByReport");
		getSessionTemplate().delete(sqlId, affiliationDate);
	}
	
}
