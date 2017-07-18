package cn.yesway.pms.common.exception;

/**
 * 系统异常基类。
 * 
 * @author XuePeng
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = -6084856616492804571L;

    // 错误原因
    private final Throwable cause;
    // 错误码
    private final String code;
    // 错误信息
    private final String msg;

    /**
     * 构造函数。
     * 
     * @param 错误原因。
     * @param 错误码。
     * @param 错误信息。
     */
    public BaseException(Throwable cause, String code, String msg) {
        super(msg, cause);
        this.cause = cause;
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return 获得错误原因。
     */
    @Override
    public Throwable getCause() {
        return cause;
    }

    /**
     * @return 获得错误码。
     */
    public String getCode() {
        return code;
    }

    /**
     * 获得错误信息。
     */
    public String getMsg() {
        return msg;
    }
}
