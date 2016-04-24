package com.yaeyi.mobile.controller.userManage;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yaeyi.mobile.service.log.impl.SCLogger;
import com.yaeyi.mobile.util.MD5Util;
import com.yaeyi.mobile.util.StringUtil;

import net.sf.json.JSONObject;


/**
 * @author Dean
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * @Fields logger :日志记录
	 */
	private static final SCLogger logger = new SCLogger(UserController.class);
	
	@ResponseBody
	@RequestMapping(value = "/Login.do", method = RequestMethod.GET)
	public ModelAndView Login() {
		logger.begin();

		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			logger.end("User Session Timeout Or Never to Login.");
			return new ModelAndView("forward:/");
		}

		logger.end();
		return new ModelAndView("forward:/");
	}
	
	@ResponseBody
	@RequestMapping(value = "/Login.do", method = RequestMethod.POST)
	public ModelAndView Login(@RequestParam(value = "username") String userAccount,
			@RequestParam(value = "password") String userPassword) {
		logger.begin();

		logger.debug("Get UserAccount and UserPassword", userAccount + "|" + userPassword);
		if (!StringUtil.isEmptyString(userAccount) && !StringUtil.isEmptyString(userPassword)) {
			try {
				// 用户登录账号
				userAccount = userAccount.toLowerCase().trim();
				logger.debug("Try to Login with User", userAccount);

				// 登录密码
				String userMD5Password = MD5Util.MD5(userPassword);
				logger.debug("User Login by Encrypted Password", userMD5Password);

				// 总是进行登录验证
				Subject subject = SecurityUtils.getSubject();
				subject.login(new UsernamePasswordToken(userAccount, userMD5Password));

				// 登录成功
				if (subject.isAuthenticated()) {
					logger.debug("User Login Succeed", userAccount);

					ModelAndView mav = new ModelAndView("redirect:/oa/account/my-account-no1.html");

					logger.end();
					return mav;
				}
			} catch (Exception e) {
			}
		}

		logger.debug("User Login Failed", userAccount + "|" + userPassword);
		logger.end();
		return new ModelAndView("redirect:/oa/Login/login.html");
	}

	@ResponseBody
	@RequestMapping(value = "/orderLogin.do", method = RequestMethod.POST)
	public JSONObject orderLogin(@RequestParam(value = "username") String userAccount,
			@RequestParam(value = "password") String userPassword) {
		logger.begin();
		JSONObject data = new JSONObject();
		
		logger.debug("Get UserAccount and UserPassword", userAccount + "|" + userPassword);
		if (!StringUtil.isEmptyString(userAccount) && !StringUtil.isEmptyString(userPassword)) {
			try {
				// 用户登录账号
				userAccount = userAccount.toLowerCase().trim();
				logger.debug("Try to Login with User", userAccount);

				// 登录密码
				String userMD5Password = MD5Util.MD5(userPassword);
				logger.debug("User Login by Encrypted Password", userMD5Password);

				// 总是进行登录验证
				Subject subject = SecurityUtils.getSubject();
				subject.login(new UsernamePasswordToken(userAccount, userMD5Password));

				// 登录成功
				if (subject.isAuthenticated()) {
					logger.debug("User Login Succeed", userAccount);

					data.put("status", true);
					
					logger.end();
					return data;
				}
			} catch (Exception e) {
			}
		}

		logger.debug("User Login Failed", userAccount + "|" + userPassword);
		logger.end();
		data.put("status", false);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Logout.do", method = RequestMethod.GET)
	public ModelAndView Logout() {
		logger.begin();

		SecurityUtils.getSubject().logout();

		logger.end();
		return new ModelAndView("forward:/oa/Login/login.html");
	}
	
}
