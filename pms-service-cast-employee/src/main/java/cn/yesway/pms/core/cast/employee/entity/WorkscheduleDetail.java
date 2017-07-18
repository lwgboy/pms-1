package cn.yesway.pms.core.cast.employee.entity;

import cn.yesway.pms.common.entity.BaseEntity;

public class WorkscheduleDetail extends BaseEntity {

    private static final long serialVersionUID = -7779511585712978318L;
    private Long projectId;
    private String projectName;
    private Integer workTime;
    private String description;
    
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
