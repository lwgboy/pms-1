package cn.yesway.pms.core.cast.resource.entity;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.project.entity.Project;

public class ResourceAmountProjectDetail extends BaseEntity {

	private static final long serialVersionUID = -6496406965182761198L;
	private Project project;
	private Double scale;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

}
