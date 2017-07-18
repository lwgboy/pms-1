package cn.yesway.pms.core.cast.resource.exception;

import cn.yesway.pms.common.exception.BaseException;

public class UpdateStatusException extends BaseException {

    private static final long serialVersionUID = 7710459871804728073L;

    public UpdateStatusException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

}
