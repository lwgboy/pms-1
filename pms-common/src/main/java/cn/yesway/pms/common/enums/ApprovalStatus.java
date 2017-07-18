package cn.yesway.pms.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 审批状态枚举。
 * 
 * @author XuePeng
 */
public enum ApprovalStatus {
    /**
     * 编辑
     */
    EDIT("编辑", 0),
    /**
     * 上报
     */
    REPORT("上报", 1),
    /**
     * 审核
     */
    REVIEW("审核", 2),
    /**
     * 审批
     */
    APPROVAL("审批", 3),
    /**
     * 提交
     */
    SUBMIT("提交", 4);

    // 描述
    private final String desc;
    // 枚举值
    private final int value;

    // 枚举的构造函数
    private ApprovalStatus(String desc, int value) {
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
    public static ApprovalStatus getItem(int value) {
        for (ApprovalStatus item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }
}
