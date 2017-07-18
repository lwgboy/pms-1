package cn.yesway.pms.core.report.entity;

import cn.yesway.pms.core.project.entity.Project;

public class ProjectWorkscheduleReport extends WorkscheduleReport {

	private static final long serialVersionUID = -8981416113207647922L;
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
