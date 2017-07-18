package cn.yesway.pms.common.web.enums;

/**
 * Web应用的响应状态
 * 
 * @author XuePeng
 */
public enum ResultStatus {
    /**
     * 调用成功，并且业务执行成功。
     */
    SUCCESS,
    /**
     * 调用失败或异常。
     */
    ERROR,
    /**
     * 调用成功，单业务执行失败。
     */
    FAIL,
    /**
     * 登录超时。
     */
    TIMEOUT
}
