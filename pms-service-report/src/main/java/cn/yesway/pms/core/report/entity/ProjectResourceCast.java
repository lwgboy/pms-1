package cn.yesway.pms.core.report.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.project.entity.Project;

public class ProjectResourceCast extends BaseEntity {

	private static final long serialVersionUID = -7099487309351176398L;
	private Project project;
	private BigDecimal cast;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BigDecimal getCast() {
		return cast;
	}

	public void setCast(BigDecimal cast) {
		this.cast = cast;
	}

}
