package cn.yesway.pms.common.exception;

/**
 * 要上传的薪资表已经存在的异常。
 * 
 * @author XuePeng
 */
public class EmployeeSalaryExistsException extends BaseException {

    private static final long serialVersionUID = 7710459871804728073L;

    /**
     * 构造函数。
     * 
     * @param 错误码。
     * @param 错误信息。
     */
    public EmployeeSalaryExistsException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

}
