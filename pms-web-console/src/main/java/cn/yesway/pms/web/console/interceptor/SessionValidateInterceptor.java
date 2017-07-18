package cn.yesway.pms.web.console.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.yesway.pms.common.web.entity.WebConstant;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.employee.biz.EmployeeBiz;
import cn.yesway.pms.core.employee.entity.Employee;

/**
 * 登录超时验证拦截器。
 * 
 * @author XuePeng
 *
 */
public class SessionValidateInterceptor implements HandlerInterceptor {

	@Autowired
	private EmployeeBiz employeeBiz;

	// 日志对象。
	private static final Log LOGGER = LogFactory.getLog(SessionValidateInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
		LOGGER.debug(request.getRequestURI());
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		LOGGER.debug(request.getRequestURI());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		LOGGER.debug(request.getRequestURI());
		if (request.getSession().getAttribute(WebConstant.SESSION_NAME) == null) {
			Employee currentEmployee = null;
			String loginCookieUserName = "";
			String loginCookiePassword = "";
			// 读取cookie
			Cookie[] cookies = request.getCookies();
			if (null != cookies) {
				for (Cookie cookie : cookies) {
					if (WebConstant.COOKIE_LOGIN_NAME.equals(cookie.getName())) {
						loginCookieUserName = cookie.getValue();
					} else if (WebConstant.COOKIE_LOGIN_PASSWORD.equals(cookie.getName())) {
						loginCookiePassword = cookie.getValue();
					}
				}
				if (!"".equals(loginCookieUserName) && !"".equals(loginCookiePassword)) {
					currentEmployee = employeeBiz.login(loginCookieUserName, loginCookiePassword);
				}
			}
			if (currentEmployee != null) {
				request.getSession().setAttribute(WebConstant.SESSION_NAME, currentEmployee);
				return true;
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.setHeader("Session-State", "TIMEOUT");
			PrintWriter out = response.getWriter();
			out.println("{\"msg\":\"用户未登录\",\"status\":\"" + ResultStatus.TIMEOUT + "\"}");
			out.flush();
			out.close();
			return false;
		}
		return true;
	}

}
