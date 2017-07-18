package cn.yesway.pms.core.department.biz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.department.dao.DepartmentDao;
import cn.yesway.pms.core.department.entity.Department;

@Component("departmentBiz")
public class DepartmentBiz {

	@Autowired
	private DepartmentDao departmentDao;

	public Boolean create(Department department) {
		LocalDateTime time = LocalDateTime.now();
		department.setGmtCreate(time);
		department.setGmtModified(time);
		if (departmentDao.insert(department) > 0) {
			return true;
		}
		return false;
	}

	public Boolean update(Department department) {
		department.setGmtModified(LocalDateTime.now());
		if (departmentDao.update(department) > 0) {
			return true;
		}
		return false;
	}

	public Department findById(Long id) {
		return departmentDao.findById(id);
	}

	public PageBean<Department> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap) {
		return departmentDao.listByPageAndParam(pageParam, paramMap);
	}

	public Boolean deleteByIds(long... ids) {
		if (departmentDao.deleteByIds(ids) == 0) {
			return false;
		}
		return true;
	}

	public Boolean hasChildrenById(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentId", id);
		if (departmentDao.countByParam(paramMap) > 0) {
			return true;
		}
		return false;
	}

	public List<Department> list() {
		List<Department> departments = departmentDao.listByParam(null);
		List<Department> deptTree = new ArrayList<>();
		for (Department department : departments) {
			if (department.getParentId() == 0) {
				deptTree.add(department);
			} else {
				assemble(department, deptTree);
			}
		}
		return deptTree;
	}

	public List<Department> listById(long id) {
		List<Department> departments = departmentDao.listById(id);
		List<Department> deptTree = new ArrayList<>();
		for (Department department : departments) {
			if (department.getParentId() == 0) {
				deptTree.add(department);
			} else {
				assemble(department, deptTree);
			}
		}
		return deptTree;
	}

	private void assemble(Department department, List<Department> deptTree) {
		for (Department treeNode : deptTree) {
			if (treeNode.getId() == department.getParentId()) {
				treeNode.getChildren().add(department);
			} else {
				assemble(department, treeNode.getChildren());
			}
		}
	}

}
