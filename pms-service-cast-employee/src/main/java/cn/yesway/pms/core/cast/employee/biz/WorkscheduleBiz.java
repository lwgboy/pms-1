package cn.yesway.pms.core.cast.employee.biz;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yesway.pms.common.enums.ApprovalStatus;
import cn.yesway.pms.common.enums.Duty;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.employee.dao.WorkscheduleDao;
import cn.yesway.pms.core.cast.employee.entity.Workschedule;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleDetail;

@Component("workscheduleBiz")
public class WorkscheduleBiz {

	@Autowired
	private WorkscheduleDao workscheduleDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean create(Workschedule workschedule) {
		// 计算总工时
		Optional<Integer> total = totalWorkTime(workschedule.getDetails());
		workschedule.setTotalWorkTime(total.get());
		workschedule.setStatus(ApprovalStatus.EDIT);
		if (workscheduleDao.insert(workschedule) > 0) {
			workscheduleDao.insertDetail(workschedule);
			return true;
		}
		return false;
	}

	public Boolean update(Workschedule workschedule) {
		// 计算总工时
		Optional<Integer> total = totalWorkTime(workschedule.getDetails());
		workschedule.setTotalWorkTime(total.get());
		if (workscheduleDao.update(workschedule) > 0) {
			return true;
		}
		return false;
	}

	public PageBean<Workschedule> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap) {
		return workscheduleDao.listByPageAndParam(pageParam, paramMap);
	}

	public Workschedule findById(Long id) {
		return workscheduleDao.findById(id);
	}

	public Boolean approvalPass(Workschedule workschedule) {
		// 如果是上报，则根据职位设置上报的类型
		if (workschedule.getStatus() == ApprovalStatus.REPORT) {
			if (workschedule.getEmployee().getDuty() == Duty.BM) {
				workschedule.setStatus(ApprovalStatus.REVIEW);
			} else if (workschedule.getEmployee().getDuty() == Duty.VP) {
				workschedule.setStatus(ApprovalStatus.APPROVAL);
			}
		}
		if (workscheduleDao.updateStatus(workschedule) > 0) {
			return true;
		}
		return false;
	}

	public Boolean approvalFail(Workschedule workschedule) {
		workschedule.setStatus(ApprovalStatus.EDIT);
		if (workscheduleDao.updateStatus(workschedule) > 0) {
			return true;
		}
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateBatchStatus(List<Workschedule> workschedules) {
		for (Workschedule workschedule : workschedules) {
			workscheduleDao.updateStatus(workschedule);
		}
	}

	public Boolean isExistsByEmpIdAndCreateDate(Map<String, Object> map) {
		if (workscheduleDao.countByEmployeIdAndCreateDate(map) > 0) {
			return true;
		}
		return false;
	}

	public PageBean<Workschedule> listByStatus(PageParam pageParam, Map<String, Object> map) {
		return workscheduleDao.findByStatus(pageParam, map);
	}

	public List<Workschedule> listByParam(Map<String, Object> map) {
		return workscheduleDao.listByParam(map);
	}

	private Optional<Integer> totalWorkTime(List<WorkscheduleDetail> details) {
		return details.stream().map(WorkscheduleDetail::getWorkTime).reduce(Integer::sum);
	}
}
