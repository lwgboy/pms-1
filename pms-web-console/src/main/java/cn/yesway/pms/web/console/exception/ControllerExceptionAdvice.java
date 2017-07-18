package cn.yesway.pms.web.console.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.exception.EmployeeSalaryExistsException;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.cast.resource.exception.UpdateStatusException;

/**
 * 控制器异常处理对象。
 * 
 * @author XuePeng
 */
@RestController
@ControllerAdvice
public class ControllerExceptionAdvice {

    // 日志对象
    private static final Log LOGGER = LogFactory.getLog(ControllerExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WebAppResult handlerException(Exception execption) {
        LOGGER.error(execption.getMessage());
        execption.printStackTrace();
        return new WebAppResult(ResultStatus.ERROR, execption.getMessage(), null);
    }

    @ExceptionHandler(EmployeeSalaryExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WebAppResult handlerEmployeeSalaryExistsException(EmployeeSalaryExistsException execption) {
        LOGGER.error(execption.getMessage());
        return new WebAppResult(ResultStatus.ERROR, execption.getMessage(), null);
    }

    @ExceptionHandler(UpdateStatusException.class)
    @ResponseStatus(HttpStatus.OK)
    public WebAppResult handlerUpdateStatusException(UpdateStatusException execption) {
        LOGGER.error(execption.getMessage());
        return new WebAppResult(ResultStatus.FAIL, execption.getMessage(), null);
    }

}
