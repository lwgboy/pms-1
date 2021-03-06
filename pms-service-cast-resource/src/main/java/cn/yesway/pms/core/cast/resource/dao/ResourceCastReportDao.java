package cn.yesway.pms.core.cast.resource.dao;

import java.util.List;
import java.util.Map;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastReport;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastReportDetail;

public interface ResourceCastReportDao extends BaseDao<ResourceCastReport> {

	List<ResourceCastReportDetail> listDetail(String affiliationDate);

	void updateStatus(Map<String, Object> map);

	void deleteByReport(String affiliationDate);

}
