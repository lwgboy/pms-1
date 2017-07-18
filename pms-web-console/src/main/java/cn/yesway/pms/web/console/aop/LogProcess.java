package cn.yesway.pms.web.console.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 控制器的日志处理类。<br>
 * 使用spring aop对操作和异常进行切面处理。
 * 
 * @author XuePeng
 */
@Component
@Aspect
public class LogProcess {

    // 日志对象
    private static final Log LOGGER = LogFactory.getLog(LogProcess.class);

    /**
     * 操作日志处理。
     * 
     * @param 切入点
     */
    @Before("execution(* cn.yesway.pms.web.console.controller.*.*(..))")
    public void operationLog(JoinPoint joinPoint) {
        LOGGER.debug("========>" + joinPoint.toString() + " op log ");
    }

    /**
     * 异常日志处理。
     * 
     * @param 切入点
     * @param 异常对象
     */
    @AfterThrowing(value = "execution(* cn.yesway.pms.web.console.controller.*.*(..))", throwing = "ex")
    public void exceptionLog(JoinPoint joinPoint, Exception ex) {
        LOGGER.error("========>" + joinPoint.toString() + " controller exception: " + ex.getMessage());
    }
}
