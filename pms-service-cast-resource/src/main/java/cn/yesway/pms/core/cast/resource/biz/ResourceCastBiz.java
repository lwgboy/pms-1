package cn.yesway.pms.core.cast.resource.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yesway.pms.common.enums.ApprovalStatus;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.resource.dao.ResourceCastDao;
import cn.yesway.pms.core.cast.resource.dao.ResourceCastReportDao;
import cn.yesway.pms.core.cast.resource.entity.ResourceCast;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastReport;
import cn.yesway.pms.core.cast.resource.entity.ResourceCastReportDetail;

@Component("resourceCastBiz")
public class ResourceCastBiz {

	@Autowired
	private ResourceCastDao resourceCastDao;
	@Autowired
	private ResourceCastReportDao resourceCastReportDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean create(ResourceCast resourceCast) {
		resourceCastDao.insert(resourceCast);
		resourceCastDao.createDetails(resourceCast);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean update(ResourceCast resourceCast) {
		resourceCastDao.deleteDetailById(resourceCast.getId());
		resourceCastDao.createDetails(resourceCast);
		resourceCastDao.update(resourceCast);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean updateStatus(ResourceCast resourceCast) {
		if (resourceCastDao.update(resourceCast) > 0) {
			if (resourceCast.getStatus() == ApprovalStatus.REPORT) {
				resourceCastDao.createReport(resourceCast.getId());
			}
			return true;
		}
		return false;
	}

	public ResourceCast findById(Long id) {
		return resourceCastDao.findById(id);
	}

	public PageBean<ResourceCast> listByPageAndParam(PageParam page, Map<String, Object> map) {
		return resourceCastDao.listByPageAndParam(page, map);
	}

	public PageBean<ResourceCastReport> reviewList(PageParam page, Map<String, Object> map) {
		return resourceCastReportDao.listByPageAndParam(page, map);
	}

	public Boolean isExists(Map<String, Object> map) {
		if (resourceCastDao.countByParam(map) > 0) {
			return true;
		}
		return false;
	}

	public List<ResourceCastReportDetail> listDetail(String affiliationDate) {
		return resourceCastReportDao.listDetail(affiliationDate);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean updateStatus(Map<String, Object> map) {
		resourceCastReportDao.updateStatus(map);
		if ("0".equals(map.get("status"))) {
			resourceCastReportDao.deleteByReport(map.get("affiliationDate").toString());
		}
		return true;
	}

}
