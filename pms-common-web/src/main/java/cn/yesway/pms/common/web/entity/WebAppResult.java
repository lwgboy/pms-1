package cn.yesway.pms.common.web.entity;

import cn.yesway.pms.common.web.enums.ResultStatus;

/**
 * Web应用的响应数据实体对象。<br>
 * 包含了与前端约定的属性。
 * 
 * @author XuePeng
 */
public class WebAppResult {

    // 响应状态
    // 不是http的响应状态，是业务系统的响应状态
    // 业务通过返回Success
    // 业务不通过（例如，登录失败等）返回Fail
    private final ResultStatus status;

    // 发送给前端的信息
    private final String msg;
    // 发送给前端的json数据
    private final Object data;

    /**
     * 构造函数。
     * 
     * @param 响应状态
     * @param 信息
     * @param 数据
     */
    public WebAppResult(ResultStatus status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @return 获得响应状态。
     */
    public ResultStatus getStatus() {
        return status;
    }

    /**
     * @return 获得响应信息。
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @return 获得响应数据。
     */
    public Object getData() {
        return data;
    }

}
