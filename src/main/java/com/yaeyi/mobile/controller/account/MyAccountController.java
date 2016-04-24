package com.yaeyi.mobile.controller.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yaeyi.api.service.mobileManage.IMobileService;
import com.yaeyi.mobile.common.CommonConstant;
import com.yaeyi.mobile.common.InterfacesConstants;
import com.yaeyi.mobile.service.log.impl.SCLogger;
import com.yaeyi.mobile.service.shiro.UserManage;
import com.yaeyi.mobile.util.HessionFactory;
import com.yaeyi.mobile.util.JacksonUtils;
import com.yaeyi.mobile.util.StringUtil;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/Account")
public class MyAccountController {
	/**
	 * @Fields logger :日志记录
	 */
	private static final SCLogger logger = new SCLogger(MyAccountController.class);
	
	
	/**
	 * 用户查看个人信息
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/viewPersonInfo.do",method=RequestMethod.POST)
	@ResponseBody
	public Object viewPersonInfo() {
		logger.begin("#### MyAccountController.viewPersonInfo Method ####");
		JSONObject objectList = new JSONObject();
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");
				return objectList;
		}
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			objectList.put("userName", userManage.getUserName());
			objectList.put("address", userManage.getUserAddress());
			objectList.put("gender", userManage.getUserGender());
			objectList.put("mobile", userManage.getUserPhone());
			objectList.put("userIcon", userManage.getUserICON());
			objectList.put("status", "0");
			logger.debug("personInfo ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.viewPersonInfo Method error:", e.getMessage());
			objectList.put("status", "2");
		}
		logger.end("####  end  MyAccountController.viewPersonInfo Method ####");
		return objectList ;
	}
	
	
	/**
	 * 用户修改姓名，年龄，地址信息,手机号
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileUserUpdateAccount.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUserUpdateAccount(@RequestParam("userName") String userName,@RequestParam("userGender") String userGender,
			@RequestParam("userAddress") String userAddress) {
		logger.begin("#### MyAccountController.mobileUserUpdateAccount Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("userID", userManage.getUserID());
			parameterMap.put("userName", userName);
			parameterMap.put("userGender", userGender);
			parameterMap.put("userAddress", userAddress);
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileUserUpdateAccount(JacksonUtils.getJson(parameterMap));
			
			if(StringUtil.isNotEmpty(userName)){
				userManage.setUserName(userName);
			}else if(StringUtil.isNotEmpty(userGender)){
				userManage.setUserGender(userGender);
			}else if(StringUtil.isNotEmpty(userAddress)){
				userManage.setUserAddress(userAddress);
			}
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileUserUpdateAccount Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileUserUpdateAccount Method ####");
		return objectList ;
	}
	

	
	/**
	 * 修改登录密码时验证旧密码
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileUserVerifyLoginPassword.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUserVerifyLoginPassword(@RequestParam("userPassword") String userPassword) {
		logger.begin("#### MyAccountController.mobileUserVerifyLoginPassword Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("userPassword", userPassword);
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileUserVerifyLoginPassword(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileUserVerifyLoginPassword Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileUserVerifyLoginPassword Method ####");
		return objectList ;
	}
	
	
	/**
	 * 修改新登录密码
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileUserChangeLoginPassword.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUserChangeLoginPassword(@RequestParam("userPassword") String userPassword) {
		logger.begin("#### MyAccountController.mobileUserVerifyLoginPassword Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("userPassword", userPassword);
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileUserChangeLoginPassword(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileUserChangeLoginPassword Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileUserChangeLoginPassword Method ####");
		return objectList ;
	}
	
	/**
	 * 修改支付密码时验证旧密码
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileUserVerifyPaySecurity.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUserVerifyPaySecurity(@RequestParam("paySecurity") String paySecurity) {
		logger.begin("#### MyAccountController.mobileUserVerifyPaySecurity Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("paySecurity", paySecurity);
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileUserVerifyPaySecurity(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileUserVerifyPaySecurity Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileUserVerifyPaySecurity Method ####");
		return objectList ;
	}
	
	
	
	/**
	 * 設置新支付密碼
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileUserResetPaySecurity.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUserResetPaySecurity(@RequestParam("paySecurity") String paySecurity) {
		logger.begin("#### MyAccountController.mobileUserResetPaySecurity Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("paySecurity", paySecurity);
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileUserSetPaySecurity(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			
			userManage.setPaySecurity(Integer.parseInt(paySecurity));
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileUserResetPaySecurity Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileUserResetPaySecurity Method ####");
		return objectList ;
	}
	
	
	/**
	 * 退出登陆
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileUserLogout.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUserLogout() {
		logger.begin("#### MyAccountController.mobileUserResetPaySecurity Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
//			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("userAccount", userManage.getUserAccount());
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			SecurityUtils.getSubject().logout();
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileUserLogout Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileUserLogout Method ####");
		return objectList ;
	}
	
	
	/**
	 * 查询账户余额
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileAccountQueryBalance.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileAccountQueryBalance() {
		logger.begin("#### MyAccountController.mobileAccountQueryBalance Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileAccountQueryBalance(JacksonUtils.getJson(parameterMap));
			objectList.put("userName", userManage.getUserName());
			objectList.put("userIcon", userManage.getUserICON());
			objectList.put("status", "0");//取值成功，返回数据
			
			// 查看账号下面是否有订单
			JSONObject data = new JSONObject();
			data = mobileService.mobileQueryAccountOrderList(JacksonUtils.getJson(parameterMap));
			if(data.isEmpty() || StringUtil.isEmpty(data.get("orderList"))){
				objectList.put("orderFalg", false);
			}else{
				objectList.put("orderFalg", true);
			}
			
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileAccountQueryBalance Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileAccountQueryBalance Method ####");
		return objectList ;
	}
	
	/**
	 * 查询用户就诊账单列表
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileAccountQueryBillsList.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileAccountQueryBillsList() {
		logger.begin("#### MyAccountController.mobileAccountQueryBillsList Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileAccountQueryBillsList(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileAccountQueryBillsList Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileAccountQueryBillsList Method ####");
		return objectList ;
	}
	
	
	/**
	 * 查询账单交易详情
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileAccountQueryBillContent.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileAccountQueryBillContent(@RequestParam("parameter") String parameter) {
		logger.begin("#### MyAccountController.mobileAccountQueryBillContent Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileAccountQueryBillContent(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileAccountQueryBillContent Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileAccountQueryBillContent Method ####");
		return objectList ;
	}
	
	/**
	 * 验证是否设置支付密码
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/commonCheckPaySecurityIsExist.do",method=RequestMethod.POST)
	@ResponseBody
	public Object commonCheckPaySecurityIsExist () {
		logger.begin("#### MyAccountController.commonCheckPaySecurityIsExist  Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			objectList.put("paySecurity", userManage.getPaySecurity());
			objectList.put("status", "0");
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.commonCheckPaySecurityIsExist  Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.commonCheckPaySecurityIsExist  Method ####");
		return objectList ;
	}
	
	
	/**
	 * 查询收藏的诊所列表
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileQueryFavoriteClinicList.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQueryFavoriteClinicList (@RequestParam("gpsLatitude") String gpsLatitude,@RequestParam("gpsLongitude") String gpsLongitude,@RequestParam("clinicName") String clinicName) {		logger.begin("#### MyAccountController.mobileQueryFavoriteClinicList  Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userID", userManage.getUserID());
			parameterMap.put("gpsLatitude", gpsLatitude);
			parameterMap.put("gpsLongitude",gpsLongitude);
			parameterMap.put("clinicName", clinicName);
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileQueryFavoriteClinicList (JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileQueryFavoriteClinicList  Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileQueryFavoriteClinicList  Method ####");
		return objectList ;
	}
	
	
	/**
	 * 查询收藏的医生列表
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileQueryFavoriteDoctorList.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQueryFavoriteDoctorList (@RequestParam("gpsLatitude") String gpsLatitude,@RequestParam("gpsLongitude") String gpsLongitude,@RequestParam("doctorName") String doctorName) {
		logger.begin("#### MyAccountController.mobileQueryFavoriteClinicList  Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userID", userManage.getUserID());
			parameterMap.put("gpsLatitude", gpsLatitude);
			parameterMap.put("gpsLongitude",gpsLongitude);
			parameterMap.put("doctorName", doctorName);
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileQueryFavoriteDoctorList (JacksonUtils.getJson(parameterMap));
			
			
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileQueryFavoriteDoctorList  Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileQueryFavoriteDoctorList  Method ####");
		return objectList ;
	}
	
	
	/**
	 * 获取短信验证码
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileQuerySMSVerifyCode.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQuerySMSVerifyCode  (@RequestParam("MSISDN") String MSISDN,@RequestParam("templateId") String templateId) {
		logger.begin("#### MyAccountController.commonCheckVerifyCode   Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("MSISDN", MSISDN);
			parameterMap.put("codeType", templateId);
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileQuerySMSVerifyCode(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("patientList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileQuerySMSVerifyCode   Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileQuerySMSVerifyCode   Method ####");
		return objectList ;
	}
	
	
	
	/**
	 * 验证验证码是否正确
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/commonCheckVerifyCode.do",method=RequestMethod.POST)
	@ResponseBody
	public Object commonCheckVerifyCode  (@RequestParam("userPhone") String userPhone,@RequestParam("verifyCode") String verifyCode,@RequestParam("isChangeMobile") String isChangeMobile) {
		logger.begin("#### MyAccountController.commonCheckVerifyCode   Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("verifyCode", verifyCode);
			parameterMap.put("userPhone", userPhone);
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.commonCheckVerifyCode(JacksonUtils.getJson(parameterMap));
			//判断是否属于修改手机号操作
//			if(StringUtils.isNotBlank(isChangeMobile)){
//				Object obj = objectList.get("resultMSG");
//				ResultMsg resultmsg = JacksonUtils.toBean(obj, ResultMsg.class);
//				if("0".equals(resultmsg.getResultCode())){
//					userManage.setUserPhone(userPhone);
//					userManage.setUserAccount(userPhone);
//				}
//			}
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.commonCheckVerifyCode   Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.commonCheckVerifyCode   Method ####");
		return objectList ;
	}
	
	

	/**
	 * 查询消息列表
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileQueryMyMessageList.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQueryMyMessageList() {
		logger.begin("#### MyAccountController.mobileQueryMyMessageList   Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userID", userManage.getUserID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileQueryMyMessageList(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileQueryMyMessageList   Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileQueryMyMessageList   Method ####");
		return objectList ;
	}
	
	
	/**
	 * 查询消息明细
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileQueryMyMessageDetail.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQueryMyMessageDetail(@RequestParam("parameter") String parameter) {
		logger.begin("#### MyAccountController.mobileQueryMyMessageDetail   Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
			parameterMap.put("userID",userManage.getUserID().toString());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileQueryMyMessageDetail(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileQueryMyMessageDetail   Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileQueryMyMessageDetail   Method ####");
		return objectList ;
	}
	
	
	/**
	 * 修改消息状态
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileChangeMyMessagesStatus.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileChangeMyMessagesStatus(@RequestParam("msgIDs") String msgIDs,@RequestParam("changeType") Integer changeType) {
		logger.begin("#### MyAccountController.mobileChangeMyMessagesStatus   Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userID",userManage.getUserID());
			parameterMap.put("msgIDs",msgIDs);
			parameterMap.put("changeType",changeType);
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileChangeMyMessagesStatus(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("josnData ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileChangeMyMessagesStatus   Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileChangeMyMessagesStatus   Method ####");
		return objectList ;
	}
	
	
	/**
	 * 查询就诊者授权诊所列表
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileQueryClinicAuthorizedClinicList.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQueryClinicAuthorizedClinicList() {
		logger.begin("#### MyAccountController.mobileUpdateClinicAuthorizedStatus   Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("patientID",userManage.getPatientID());
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileQueryClinicAuthorizedClinicList(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("josnData ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileQueryClinicAuthorizedClinicList   Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileQueryClinicAuthorizedClinicList   Method ####");
		return objectList ;
	}
	
	
	
	/**
	 * 就诊者对诊所进行授权操作
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常
	 */
	@RequestMapping(value="/mobileUpdateClinicAuthorizedStatus.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUpdateClinicAuthorizedStatus(@RequestParam("clinicID") String clinicID,@RequestParam("basicAuthorized") String basicAuthorized,@RequestParam("medicineAuthorized") String medicineAuthorized) {
		logger.begin("#### MyAccountController.mobileUpdateClinicAuthorizedStatus   Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("clinicID",clinicID);
			parameterMap.put("patientID",userManage.getPatientID());
			parameterMap.put("basicAuthorized",basicAuthorized);
			parameterMap.put("medicineAuthorized",medicineAuthorized);
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.mobileUpdateClinicAuthorizedStatus(JacksonUtils.getJson(parameterMap));
			objectList.put("status", "0");//取值成功，返回数据
			logger.debug("josnData ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("MyAccountController.mobileUpdateClinicAuthorizedStatus   Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  MyAccountController.mobileUpdateClinicAuthorizedStatus   Method ####");
		return objectList ;
	}
	
	
}
