package com.yaeyi.mobile.service.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class ShiroAuthzPathFilter extends PermissionsAuthorizationFilter {
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {

		if (request instanceof HttpServletRequest) {
			Subject subject = getSubject(request, response);
			String strUrl = ((HttpServletRequest) request).getRequestURI();
			if (!subject.isPermitted(strUrl)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
