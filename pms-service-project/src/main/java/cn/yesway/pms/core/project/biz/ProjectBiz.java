package cn.yesway.pms.core.project.biz;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.project.dao.ProjectDao;
import cn.yesway.pms.core.project.entity.Project;

@Component("projectBiz")
public class ProjectBiz {

	@Autowired
	private ProjectDao projectDao;

	public Boolean create(Project project) {
		LocalDateTime time = LocalDateTime.now();
		project.setGmtCreate(time);
		project.setGmtModified(time);
		project.setDeleted(false);
		if (projectDao.insert(project) > 0) {
			return true;
		}
		return false;
	}

	public Boolean update(Project project) {
		project.setGmtModified(LocalDateTime.now());
		if (projectDao.update(project) > 0) {
			return true;
		}
		return false;
	}

	public Boolean deleteByIds(Long... ids) {
		if (projectDao.deleteByIds(ids) > 0) {
			return true;
		}
		return false;
	}

	public Project findById(Long id) {
		return projectDao.findById(id);
	}

	public Boolean isExists(Map<String, Object> paramMap) {
		if (projectDao.countByParam(paramMap) > 0) {
			return true;
		}
		return false;
	}

	public PageBean<Project> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap) {
		return projectDao.listByPageAndParam(pageParam, paramMap);
	}

	public List<Project> listByStatus() {
		return projectDao.listByStatus();
	}

	public PageBean<Project> listByEmployeeId(PageParam pageParam, Map<String, Object> paramMap) {
		return projectDao.listByEmployeeId(pageParam, paramMap);
	}

	public List<Project> listByEmployeeId(Map<String, Object> map) {
		return projectDao.listByParam(map);
	}

	public List<Project> listByRunning() {
		return projectDao.listByRunning();
	}

}
