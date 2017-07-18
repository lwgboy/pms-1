package cn.yesway.pms.core.department.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import cn.yesway.pms.common.entity.BaseEntity;

public class Department extends BaseEntity {

    private static final long serialVersionUID = -2260226312572896020L;
    private Long parentId;
    private String parentName;
    private String name;
    private String description;
    private Boolean deleted;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<Department> children = new ArrayList<>();

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

}
