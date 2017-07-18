package cn.yesway.pms.common.exception;

/**
 * 数据库方言设置属性异常。
 * 
 * @author XuePeng
 */
public class DialectSetPropertiesException extends BaseException {

    private static final long serialVersionUID = -5258541358298987209L;

    /**
     * 构造函数。
     * 
     * @param 错误码。
     * @param 错误信息。
     */
    public DialectSetPropertiesException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

}
