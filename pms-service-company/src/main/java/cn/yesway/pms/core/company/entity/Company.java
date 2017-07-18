package cn.yesway.pms.core.company.entity;

import cn.yesway.pms.common.entity.BaseEntity;

/**
 * 公司(缴费主体)的实体对象。
 * 
 * @author XuePeng
 */
public class Company extends BaseEntity {

	private static final long serialVersionUID = 7639961614471891765L;
	// 公司名称
	private String name;
	// 备注
	private String description;
	// 是否删除
	private Boolean deleted;

	/**
	 * @return 返回公司名称。
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置公司名称。
	 * 
	 * @param 公司名称。
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回备注。
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置备注。
	 * 
	 * @param 备注。
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return 返回是否删除。
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * 设置是否删除。
	 * 
	 * @param 是否删除。
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
