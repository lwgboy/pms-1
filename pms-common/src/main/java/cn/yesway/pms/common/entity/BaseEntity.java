package cn.yesway.pms.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体对象的基类，定义了主键，创建时间，修改时间。
 * 
 * @author XuePeng
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -7823935293750004789L;

    // 主键
    private Long id;
    // 创建时间
    private LocalDateTime gmtCreate;
    // 修改时间
    private LocalDateTime gmtModified;

    /**
     * @return 获得主键。
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键。
     * 
     * @param 主键。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 获得实体的创建时间。
     */
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置实体的创建时间。
     * 
     * @param 实体的创建时间。
     */
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return 获得实体的修改时间。
     */
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置实体的修改时间。
     * 
     * @param 实体的修改时间。
     */
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

}
