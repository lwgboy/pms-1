package cn.yesway.pms.core.cast.employee.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.project.entity.Project;

public class WorkscheduleMonthProjectDetail extends BaseEntity {

	private static final long serialVersionUID = 3598690891518294045L;
	private Project project;
	private BigDecimal worktime;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BigDecimal getWorktime() {
		return worktime;
	}

	public void setWorktime(BigDecimal worktime) {
		this.worktime = worktime;
	}

}
