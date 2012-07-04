package com.taoists.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public class SecurityFilter implements Filter {

	private String resource = null;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = ((HttpServletRequest) req);
		Account account = (Account) request.getSession().getAttribute("currentAccount");
		String url = request.getRequestURI();

		String context = request.getContextPath();

		String[] resources = null;

		if (resource != null) {
			resources = resource.split(",");
		}

		if (handleResource(resources, context, url, request.getMethod())) {
			chain.doFilter(req, resp);
		} else if ((account != null) && StringUtils.isNotBlank(account.getUsername())) {
			chain.doFilter(req, resp);
		} else {
			((HttpServletResponse) resp).sendRedirect(context + "/login");
		}
	}

	private boolean handleResource(String[] resources, String context, String url, String method) {
		if (resources == null) {
			return true;
		}
		for (String resource : resources) {
			if (resource != null) {
				resource = resource.trim();
			}
			if (url != null && url.contains(context + resource) && !"".equals(resource)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.resource = filterConfig.getInitParameter("resource");
	}

	@Override
	public void destroy() {

	}

}
