package com.yaeyi.mobile.controller.Regiter;


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

import net.sf.json.JSONObject;


/**
 * @author weiyun
 *
 */
@Controller
@RequestMapping("/Regiter")
public class RegiterController {
	
	/**
	 * @Fields logger :日志记录
	 */
	private static final SCLogger logger = new SCLogger(RegiterController.class);
	
	
	/**
	 * 获取短信验证码接口
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常 3、接口返回数据异常
	 */
	@RequestMapping(value="/mobileQuerySMSVerifyCode.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQuerySMSVerifyCode(@RequestParam(value="MSISDN") String MSISDN,@RequestParam(value="codeType") String codeType) {
		logger.begin("#### RegiterController.mobileQuerySMSVerifyCode Method ####");
		JSONObject objectList = new JSONObject();
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("MSISDN", MSISDN);
			parameterMap.put("codeType", codeType);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.mobileQuerySMSVerifyCode(JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.mobileQuerySMSVerifyCode Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileQuerySMSVerifyCode Method ####");
		return objectList ;
	}
	
	/**
	 * 用户注册验证校验码接口
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常 3、接口返回数据异常
	 */
	@RequestMapping(value="/commonCheckVerifyCode.do",method=RequestMethod.POST)
	@ResponseBody
	public Object commonCheckVerifyCode (@RequestParam(value="userPhone") String userPhone,@RequestParam(value="verifyCode") String verifyCode) {
		logger.begin("#### RegiterController.commonCheckVerifyCode  Method ####");
		JSONObject objectList = new JSONObject();
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("userPhone", userPhone);
			parameterMap.put("verifyCode", verifyCode);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.commonCheckVerifyCode(JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.commonCheckVerifyCode  Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileQuerySMSVerifyCode Method ####");
		return objectList ;
	}
	
	/**
	 * 完善个人信息
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常  3、接口返回数据异常
	 */
	@RequestMapping(value="/mobileUserSupplyRegister.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUserSupplyRegister(@RequestParam(value="userAccount") String userAccount,@RequestParam(value="userPassword") String userPassword,
			@RequestParam(value="userName") String userName,@RequestParam(value="userGender") String userGender) {
		logger.begin("#### RegiterController.mobileUserSupplyRegister Method ####");
		JSONObject objectList = new JSONObject();
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("userAccount", userAccount);
			parameterMap.put("userPassword", userPassword);
			parameterMap.put("userName", userName);
			parameterMap.put("userGender", userGender);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.mobileUserSupplyRegister(JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.mobileUserSupplyRegister Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileUserSupplyRegister Method ####");
		return objectList ;
	}
	
	
	
	/**
	 * 查询账号下的某就诊者详细
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常  3、接口返回数据异常
	 */
	@RequestMapping(value="/mobileQueryUserContactPatientdetail.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQueryUserContactPatientdetail(@RequestParam(value="patientID") String patientID) {
		logger.begin("#### RegiterController.mobileQueryUserContactPatientdetail  Method ####");
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
			parameterMap.put("patientID", patientID);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.mobileQueryUserContactPatientDetail (JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.mobileQueryUserContactPatientdetail  Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileQueryUserContactPatientdetail  Method ####");
		return objectList ;
	}
	
	/**
	 * 新增就诊者
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常  3、接口返回数据异常
	 */
	@RequestMapping(value="/mobileSaveUserContactPatient.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileSaveUserContactPatient(@RequestParam(value="patientName") String patientName,@RequestParam(value="patientICON") String patientICON,
			@RequestParam(value="patientGender") String patientGender,@RequestParam(value="patientBirthday") String patientBirthday,
			@RequestParam(value="patientAge",required=false) String patientAge,@RequestParam(value="patientCitizenID",required=false) String patientCitizenID,
			@RequestParam(value="patientPhone",required=false) String patientPhone,@RequestParam(value="patientQQ",required=false) String patientQQ,
			@RequestParam(value="patientEmail",required=false) String patientEmail,@RequestParam(value="patientAddress",required=false) String patientAddress,
			@RequestParam(value="contactName",required=false) String contactName,@RequestParam(value="contactPhone",required=false) String contactPhone,
			@RequestParam(value="allergicDrug",required=false) String allergicDrug,@RequestParam(value="diseaseHostory",required=false) String diseaseHostory) {
		logger.begin("#### RegiterController.mobileSaveUserContactPatient Method ####");
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
			parameterMap.put("patientName", patientName);
			parameterMap.put("patientICON", patientICON);
			parameterMap.put("patientGender", patientGender);
			parameterMap.put("patientBirthday", patientBirthday);
			parameterMap.put("patientAge", patientAge);
			parameterMap.put("patientCitizenID", patientCitizenID);
			parameterMap.put("patientPhone", patientPhone);
			parameterMap.put("patientQQ", patientQQ);
			parameterMap.put("patientEmail", patientEmail);
			parameterMap.put("patientAddress", patientAddress);
			parameterMap.put("contactName", contactName);
			parameterMap.put("contactPhone", contactPhone);
			parameterMap.put("allergicDrug", allergicDrug);
			parameterMap.put("diseaseHostory", diseaseHostory);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.mobileSaveUserContactPatient(JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.mobileSaveUserContactPatient Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileSaveUserContactPatient Method ####");
		return objectList ;
	}
	
	
	
	
	/**
	 * 修改账号下的就诊者信息
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常  3、接口返回数据异常
	 */
	@RequestMapping(value="/mobileUpdateUserContactPatient.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileUpdateUserContactPatient(@RequestParam(value="patientID") String patientID,@RequestParam(value="patientName",required=false) String patientName,
			@RequestParam(value="patientICON",required=false) String patientICON,
			@RequestParam(value="patientGender",required=false) String patientGender,@RequestParam(value="patientBirthday",required=false) String patientBirthday,
			@RequestParam(value="patientAge",required=false) String patientAge,@RequestParam(value="patientCitizenID",required=false) String patientCitizenID,
			@RequestParam(value="patientPhone",required=false) String patientPhone,@RequestParam(value="patientQQ",required=false) String patientQQ,
			@RequestParam(value="patientEmail",required=false) String patientEmail,@RequestParam(value="patientAddress",required=false) String patientAddress,
			@RequestParam(value="contactName",required=false) String contactName,@RequestParam(value="contactPhone",required=false) String contactPhone,
			@RequestParam(value="allergicDrug",required=false) String allergicDrug,@RequestParam(value="diseaseHostory",required=false) String diseaseHostory) {
		logger.begin("#### RegiterController.mobileUpdateUserContactPatient Method ####");
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
			parameterMap.put("patientID", patientID);
			parameterMap.put("patientName", patientName);
			parameterMap.put("patientICON", patientICON);
			parameterMap.put("patientGender", patientGender);
			parameterMap.put("patientBirthday", patientBirthday);
			parameterMap.put("patientAge", patientAge);
			parameterMap.put("patientCitizenID", patientCitizenID);
			parameterMap.put("patientPhone", patientPhone);
			parameterMap.put("patientQQ", patientQQ);
			parameterMap.put("patientEmail", patientEmail);
			parameterMap.put("patientAddress", patientAddress);
			parameterMap.put("contactName", contactName);
			parameterMap.put("contactPhone", contactPhone);
			parameterMap.put("allergicDrug", allergicDrug);
			parameterMap.put("diseaseHostory", diseaseHostory);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.mobileUpdateUserContactPatient(JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.mobileUpdateUserContactPatient Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileUpdateUserContactPatient Method ####");
		return objectList ;
	}
	
	/**
	 * 查询就诊订单详细信息
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常  3、接口返回数据异常
	 */
	@RequestMapping(value="/mobileQueryPatientTreatOrderDetail.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQueryPatientTreatOrderDetail(@RequestParam(value="orderID") String orderID,@RequestParam(value="patientID") String patientID) {
		logger.begin("#### RegiterController.mobileQueryPatientTreatOrderDetail Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		//UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("patientID", patientID);
			parameterMap.put("orderID", orderID);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.mobileQueryPatientTreatOrderDetail(JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.mobileQueryPatientTreatOrderDetail Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileQueryPatientTreatOrderDetail Method ####");
		return objectList ;
	}
	
	
	
	/**
	 * 手机端发起预约
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常  3、接口返回数据异常
	 */
	@RequestMapping(value="/mobileSaveBooksOrderContent.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileSaveBooksOrderContent(@RequestParam(value="clinicID") String clinicID,@RequestParam(value="patientID") String patientID,
			@RequestParam(value="orderProperty") String orderProperty,@RequestParam(value="patientTreatSubject") String patientTreatSubject,
			@RequestParam(value="orderBookedTime") String orderBookedTime,
			@RequestParam(value="patientSymptom") String patientSymptom) {
		logger.begin("#### RegiterController.mobileQueryTreatedOrderContent Method ####");
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
			parameterMap.put("clinicID", clinicID);
			parameterMap.put("patientID", patientID);
			parameterMap.put("orderProperty",orderProperty);
			parameterMap.put("patientTreatSubject", patientTreatSubject);
			parameterMap.put("orderBookedTime", orderBookedTime);
			parameterMap.put("patientSymptom", patientSymptom);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.mobileSaveBooksOrderContent(JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.mobileSaveBooksOrderContent  Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileSaveBooksOrderContent  Method ####");
		return objectList ;
	}
	
	
	/**
	 * 用户查询订单基本信息
	 * 
	 * @return status 1、登录超时  0、接口调用成功  2、接口异常  3、接口返回数据异常
	 */
	@RequestMapping(value="/mobileQueryPatientOrderBaseInfo.do",method=RequestMethod.POST)
	@ResponseBody
	public Object mobileQueryPatientOrderBaseInfo (@RequestParam(value="patientID") String patientID,@RequestParam(value="orderID") String orderID) {
		logger.begin("#### RegiterController.mobileQueryPatientOrderBaseInfo Method ####");
		JSONObject objectList = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
				logger.end("User Session Timeout Or Never to Login.");
				objectList.put("status", "1");//登录超时
				return objectList;
		}
		// 获取Session对象
		//UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
		try {
			IMobileService mobileService=(IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			Map<String,Object> parameterMap=new HashMap<String, Object>();
			parameterMap.put("patientID", patientID);
			parameterMap.put("orderID", orderID);
			logger.debug("parameterMap ", parameterMap);
			JSONObject resultList= mobileService.mobileQueryPatientOrderBaseInfo(JacksonUtils.getJson(parameterMap));
			if(resultList!=null){
				objectList = resultList ;
				objectList.put("status", "0");//取值成功，返回数据
			}else{
				objectList.put("status", "3");//接口返回数据异常
			}
			logger.debug("objectList ", objectList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RegiterController.mobileQueryPatientOrderBaseInfo  Method error:", e.getMessage());
			objectList.put("status", "2");//系统异常
		}
		logger.end("####  end  RegiterController.mobileSaveBooksOrderContent  Method ####");
		return objectList ;
	}
	
}
