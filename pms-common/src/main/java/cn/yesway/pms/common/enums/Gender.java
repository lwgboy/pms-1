package cn.yesway.pms.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 性别枚举。
 * 
 * @author XuePeng
 */
public enum Gender {
    /**
     * 女
     */
    FAMALE("女", 0),
    /**
     * 男
     */
    MALE("男", 1);

    // 描述
    private final String desc;
    // 枚举值
    private final int value;

    // 枚举的构造函数
    private Gender(String desc, int value) {
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
    public static Gender getItem(int value) {
        for (Gender item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
