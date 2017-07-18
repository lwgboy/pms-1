package cn.yesway.pms.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 项目状态枚举。
 * 
 * @author XuePeng
 */
public enum ProjectStatus {
    /**
     * 准备就绪。
     */
    READY("准备就绪", 0),
    /**
     * 运行中。
     */
    RUNNING("运行中", 1),
    /**
     * 暂停。
     */
    PAUSED("暂停", 2),
    /**
     * 正常结束。
     */
    FINISH("正常结束", 3),
    /**
     * 异常结束。
     */
    FINISH_ERROR("异常结束", 4);

    // 描述
    private final String desc;
    // 枚举值
    private final int value;

    // 枚举的构造函数
    private ProjectStatus(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    /**
     * @return 获得枚举值。
     */
    @JsonValue
    public int getValue() {
        return value;
    }

    /**
     * @return 获得枚举描述。
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 获得枚举项。
     * 
     * @param 枚举值。
     * @return 枚举项。
     */
    @JsonCreator
    public static ProjectStatus getItem(int value) {
        for (ProjectStatus item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }
}
