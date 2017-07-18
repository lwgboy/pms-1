package cn.yesway.pms.core.cast.resource.entity;

import java.util.ArrayList;
import java.util.List;

import cn.yesway.pms.common.entity.BaseEntity;

public class ResourceAmountDetail extends BaseEntity {

	private static final long serialVersionUID = 435983762111288133L;

	private Long resourceAmountId;
	private Resource resource;
	private List<ResourceAmountProjectDetail> details = new ArrayList<>();

	public Long getResourceAmountId() {
		return resourceAmountId;
	}

	public void setResourceAmountId(Long resourceAmountId) {
		this.resourceAmountId = resourceAmountId;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<ResourceAmountProjectDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ResourceAmountProjectDetail> details) {
		this.details = details;
	}

}
