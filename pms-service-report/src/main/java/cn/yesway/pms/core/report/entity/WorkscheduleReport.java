package cn.yesway.pms.core.report.entity;

import cn.yesway.pms.common.entity.BaseEntity;

public class WorkscheduleReport extends BaseEntity {

	private static final long serialVersionUID = -3254720895484411639L;
	private int workTime;

	public int getWorkTime() {
		return workTime;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

}
