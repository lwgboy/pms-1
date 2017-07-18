package cn.yesway.pms.core.function.entity;

import java.util.ArrayList;
import java.util.List;

import cn.yesway.pms.common.entity.BaseEntity;

public class Function extends BaseEntity {

	private static final long serialVersionUID = -4247671287429747782L;
	private Long parentId;
	private String name;
	private String url;
	private String icon;
	private Short level;
	private String description;
	private Integer orderId;
	private List<Function> children = new ArrayList<>();

	public Function() {
	}

	public Function(Long id) {
		super.setId(id);
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<Function> getChildren() {
		return children;
	}

	public void setChildren(List<Function> children) {
		this.children = children;
	}

}
