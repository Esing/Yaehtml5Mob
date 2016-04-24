
package com.yaeyi.mobile.service.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.yaeyi.api.service.mobileManage.IMobileService;
import com.yaeyi.mobile.common.CommonConstant;
import com.yaeyi.mobile.common.InterfacesConstants;
import com.yaeyi.mobile.service.log.impl.SCLogger;
import com.yaeyi.mobile.util.HessionFactory;
import com.yaeyi.mobile.util.JacksonUtils;

import net.sf.json.JSONObject;

@Service
@Scope("prototype")
public class MyShiroService extends AuthorizingRealm {

	private static final SCLogger logger = new SCLogger(MyShiroService.class);

	private UserManage userManage = null;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		try {
			logger.begin();
			// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
			String loginName = (String) super.getAvailablePrincipal(principalCollection);
			logger.debug("AuthorizationInfo Login Account", loginName);
			IMobileService userSecurityService = (IMobileService) HessionFactory
					.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userAccount", loginName);
			String jsonParameter = JacksonUtils.getJson(parameterMap);
			logger.debug("jsonParameter ", jsonParameter);
			// 到数据库查是否有此对象
			JSONObject userManageJsonObject = userSecurityService.mobileQueryUserAccount(jsonParameter);
			userManage = JacksonUtils.toBean(userManageJsonObject, UserManage.class);
			if (null != userManage) {

				// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				info.addRole(String.valueOf("0"));
				info.addStringPermission(String.valueOf("0:USER"));
				logger.end();
				return info;
			}
			logger.end();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

		logger.begin();
		// UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		logger.debug("Token User Name", token.getUsername());
		IMobileService userSecurityService = null;
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userAccount", token.getUsername());
			/* parameterMap.put("userPassword", token.getPassword()); */
			String jsonParameter = JacksonUtils.getJson(parameterMap);
			// 查出是否有此用户
			userSecurityService = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			// 到数据库查是否有此对象
			JSONObject userManageJsonObject = userSecurityService.mobileQueryUserAccount(jsonParameter);
			userManage = JacksonUtils.toBean(userManageJsonObject, UserManage.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.debug("Check User Manage", userManage);

		if (null != userManage) {
			String userAccount = userManage.getUserAccount().toLowerCase().trim();
			String userPassword = userManage.getUserPassword().trim();
			logger.debug("Query User Account", userAccount + "|" + userPassword);

			// 若存在，将此用户存放到登录认证info中
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userAccount, userPassword, getName());
			logger.debug("User Login Verify Succeed", userAccount);

			// 设置session变量，Controller使用时直接用HttpSession.getAttribute(key)就可以取到
			Subject subject = SecurityUtils.getSubject();
			if (null != subject) {
				logger.debug("Get Subject Session begin", userAccount);

				Session session = subject.getSession();
				if (null != session) {
					logger.debug("Get Subject Session succeed", userAccount);
					
					// 整个对象保存
					session.setAttribute(CommonConstant.Y3U_SESSION_USER, userManage);

					// 简单对象
					session.setAttribute("USERID", userManage.getUserID());
					session.setAttribute("PATIENTID", userManage.getPatientID());
					session.setAttribute("USERACCOUNT", userManage.getUserAccount());
					session.setAttribute("USERNAME", userManage.getUserName());
					session.setAttribute("USERGENDER", userManage.getUserGender());
					session.setAttribute("USERPHONE", userManage.getUserPhone());
					session.setAttribute("USERICON", userManage.getUserICON());
					session.setAttribute("USERADDRESS", userManage.getUserAddress());
					session.setAttribute("ACTIVEORDER", userManage.getActiveOrder());
					session.setAttribute("PAYSECURITY", userManage.getPaySecurity());

					logger.info("User [" + userAccount + "] Session Manage Succeed.");
				}
			}

			logger.end();
			return info;
		}

		logger.end();
		return null;
	}
}
