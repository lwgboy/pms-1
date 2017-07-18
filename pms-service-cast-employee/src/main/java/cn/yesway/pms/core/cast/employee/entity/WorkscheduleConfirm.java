package cn.yesway.pms.core.cast.employee.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WorkscheduleConfirm extends WorkscheduleDetail {

    private static final long serialVersionUID = -8749885314880258047L;
    private Long workscheduleId;
    private Long employeeId;
    private String employeeName;
    private Date createDate;
    private Boolean confirmed;

    public Long getWorkscheduleId() {
        return workscheduleId;
    }

    public void setWorkscheduleId(Long workscheduleId) {
        this.workscheduleId = workscheduleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

}
