package com.yaeyi.mobile.controller.Order;

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

@Controller
@RequestMapping("/Order")
public class OrderController {

	/**
	 * @Fields logger :日志记录
	 */
	private static final SCLogger logger = new SCLogger(OrderController.class);
	
	/**
	 * 查询可预约的诊所列表
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileBookingQueryClinicList.do",method=RequestMethod.POST)
	public JSONObject mobileBookingQueryClinicList(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileBookingQueryClinicList Method begin");
		JSONObject data = new JSONObject();
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
			Integer userID = 0;
			if (null != userManage){
				userID = userManage.getUserID();
				parameterMap.put("userID", String.valueOf(userID));
			}else{
				parameterMap.put("userID", "");
			}
			parameter = JacksonUtils.getJson(parameterMap);
			
			IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			data = mobileService.mobileBookingQueryClinicList(parameter);
			data.put("userID", userID);// 保存userID
			data.put("status", "0");// 正常
			logger.debug("JSONObject:", data.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileBookingQueryClinicList Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileBookingQueryClinicList Method end");
		return data;
	}
	
	/**
	 * 查询诊所详细信息
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileBookingQueryClinicContent.do",method=RequestMethod.POST)
	public JSONObject mobileBookingQueryClinicContent(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileBookingQueryClinicContent Method begin");
		JSONObject data = new JSONObject();
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
			Integer userID = 0;
			if (null != userManage){
				userID = userManage.getUserID();
				parameterMap.put("userID", String.valueOf(userID));
			}else{
				parameterMap.put("userID", "");
			}
			parameter = JacksonUtils.getJson(parameterMap);
			
			IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			data = mobileService.mobileBookingQueryClinicContent(parameter);
			data.put("userID", userID);// 保存userID
			data.put("status", "0");// 正常
			logger.debug("JSONObject:", data.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileBookingQueryClinicContent Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileBookingQueryClinicContent Method end");
		return data;
	}
	
	/**
	 * 查询诊所的连锁店
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileQueryClinicChainList.do",method=RequestMethod.POST)
	public JSONObject mobileQueryClinicChainList(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileQueryClinicChainList Method begin");
		JSONObject data = new JSONObject();
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
			Integer userID = 0;
			if (null != userManage){
				userID = userManage.getUserID();
				parameterMap.put("userID", String.valueOf(userID));
			}else{
				parameterMap.put("userID", "");
			}
			parameter = JacksonUtils.getJson(parameterMap);
			
			IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			data = mobileService.mobileQueryClinicChainList(parameter);
			data.put("userID", userID);// 保存userID
			data.put("status", "0");// 正常
			logger.debug("JSONObject:", data.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileQueryClinicChainList Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileQueryClinicChainList Method end");
		return data;
	}
	
	/**
	 * 查询可预约的医生列表
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileBookingQueryDoctorList.do",method=RequestMethod.POST)
	public JSONObject mobileBookingQueryDoctorList(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileBookingQueryDoctorList Method begin");
		JSONObject data = new JSONObject();
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
			Integer userID = 0;
			if (null != userManage){
				userID = userManage.getUserID();
				parameterMap.put("userID", String.valueOf(userID));
			}else{
				parameterMap.put("userID", "");
			}
			parameter = JacksonUtils.getJson(parameterMap);
				
			IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			data = mobileService.mobileBookingQueryDoctorList(parameter);
			data.put("userID", userID);// 保存userID
			data.put("status", "0");// 正常
			logger.debug("JSONObject:", data.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileBookingQueryDoctorList Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileBookingQueryDoctorList Method end");
		return data;
	}
	
	/**
	 * 查询医生详细信息
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileBookingQueryDoctorContent.do",method=RequestMethod.POST)
	public JSONObject mobileBookingQueryDoctorContent(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileBookingQueryDoctorContent Method begin");
		JSONObject data = new JSONObject();
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
			Integer userID = 0;
			if (null != userManage){
				userID = userManage.getUserID();
				parameterMap.put("userID", String.valueOf(userID));
			}else{
				parameterMap.put("userID", "");
			}
			parameter = JacksonUtils.getJson(parameterMap);
				
			IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			data = mobileService.mobileBookingQueryDoctorContent(parameter);
			data.put("userID", userID);// 保存userID
			data.put("status", "0");// 正常
			logger.debug("JSONObject:", data.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileBookingQueryDoctorContent Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileBookingQueryDoctorContent Method end");
		return data;
	}
	
	/**
	 * 查询账号下的所有未完成的订单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileQueryAccountOrderList.do",method=RequestMethod.POST)
	public JSONObject mobileQueryAccountOrderList(){
		logger.begin("OrderController.mobileQueryAccountOrderList Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				data.put("userID", userID);
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileQueryAccountOrderList(data.toString());
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileQueryAccountOrderList Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileQueryAccountOrderList Method end");
		return data;
	}
	
	/**
	 * 查询账号下的就诊者就诊订单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileQueryAccountTreatList.do",method=RequestMethod.POST)
	public JSONObject mobileQueryAccountTreatList(){
		logger.begin("OrderController.mobileQueryAccountTreatList Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				data.put("userID", userID);
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileQueryAccountTreatList(data.toString());
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileQueryAccountTreatList Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileQueryAccountTreatList Method end");
		return data;
	}
	
	/**
	 * 手机端发起预约
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileSaveBooksOrderContent.do",method=RequestMethod.POST)
	public JSONObject mobileSaveBooksOrderContent(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileSaveBooksOrderContent Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				
				Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
				parameterMap.put("userID", String.valueOf(userID));
				parameter = JacksonUtils.getJson(parameterMap);
				
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileSaveBooksOrderContent(parameter);
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileSaveBooksOrderContent Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileSaveBooksOrderContent Method end");
		return data;
	}
	
	/**
	 * 用户取消预约
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileUpdateCancelBookedOrder.do",method=RequestMethod.POST)
	public JSONObject mobileUpdateCancelBookedOrder(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileUpdateCancelBookedOrder Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				
				Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
				parameterMap.put("userID", String.valueOf(userID));
				parameter = JacksonUtils.getJson(parameterMap);
				
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileUpdateCancelBookedOrder(parameter);
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileUpdateCancelBookedOrder Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileUpdateCancelBookedOrder Method end");
		return data;
	}
	
	/**
	 * 用户确认预约修改
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileConfirmUpdateBookedOrder.do",method=RequestMethod.POST)
	public JSONObject mobileConfirmUpdateBookedOrder(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileConfirmUpdateBookedOrder Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				
				Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
				parameterMap.put("userID", String.valueOf(userID));
				parameter = JacksonUtils.getJson(parameterMap);
				
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileConfirmUpdateBookedOrder(parameter);
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileConfirmUpdateBookedOrder Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileConfirmUpdateBookedOrder Method end");
		return data;
	}
	
	/**
	 * 用户查询预约订单详情
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileQueryPatientOrderDetail.do",method=RequestMethod.POST)
	public JSONObject mobileQueryPatientOrderDetail(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileQueryPatientOrderDetail Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				
				Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
				parameterMap.put("userID", String.valueOf(userID));
				parameter = JacksonUtils.getJson(parameterMap);
				
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileQueryPatientOrderDetail(parameter);
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileQueryPatientOrderDetail Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileQueryPatientOrderDetail Method end");
		return data;
	}
	
	/**
	 * 用户查询挂号订单详情
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileQueryPatientRegistrationDetail.do",method=RequestMethod.POST)
	public JSONObject mobileQueryPatientRegistrationDetail(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileQueryPatientRegistrationDetail Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				
				Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
				parameterMap.put("userID", String.valueOf(userID));
				parameter = JacksonUtils.getJson(parameterMap);
				
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileQueryPatientRegistrationDetail(parameter);
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileQueryPatientRegistrationDetail Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileQueryPatientRegistrationDetail Method end");
		return data;
	}
	
	/**
	 * 用户查询订单基本信息
	 * @param parameter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileQueryPatientOrderBaseInfo.do",method=RequestMethod.POST)
	public JSONObject mobileQueryPatientOrderBaseInfo(@RequestParam(value="parameter")String parameter){
		logger.begin("OrderController.mobileQueryPatientOrderBaseInfo Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				
				Map<String, String> parameterMap = JacksonUtils.getObject(parameter);
				parameterMap.put("userID", String.valueOf(userID));
				parameter = JacksonUtils.getJson(parameterMap);
				
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileQueryPatientOrderBaseInfo(parameter);
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileQueryPatientOrderBaseInfo Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileQueryPatientOrderBaseInfo Method end");
		return data;
	}
	
	/**
	 * 查询账号下的所有就诊者
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileQueryUserContactPatientList.do",method=RequestMethod.POST)
	public JSONObject mobileQueryUserContactPatientList(){
		logger.begin("OrderController.mobileQueryUserContactPatientList Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				data.put("userID", userID);
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileQueryUserContactPatientList(data.toString());
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileQueryUserContactPatientList Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileQueryUserContactPatientList Method end");
		return data;
	}
	
	/**
	 * 查询账号下的某就诊者详细
	 * @param patientID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileQueryUserContactPatientDetail.do",method=RequestMethod.POST)
	public JSONObject mobileQueryUserContactPatientDetail(@RequestParam(value="patientID")String patientID){
		logger.begin("OrderController.mobileQueryUserContactPatientDetail Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				data.put("userID", userID);
				data.put("patientID", patientID);
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileQueryUserContactPatientDetail(data.toString());
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileQueryUserContactPatientDetail Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileQueryUserContactPatientDetail Method end");
		return data;
	}
	
	/**
	 * 查询诊所的治疗项目
	 * @param clinicID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/commonQueryTreatSubjectList.do",method=RequestMethod.POST)
	public JSONObject commonQueryTreatSubjectList(@RequestParam(value="clinicID")String clinicID){
		logger.begin("OrderController.commonQueryTreatSubjectList Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			data.put("clinicID", clinicID);
			IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
			data = mobileService.commonQueryTreatSubjectList(data.toString());
			data.put("status", "0");// 正常
			logger.debug("JSONObject:", data.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.commonQueryTreatSubjectList Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.commonQueryTreatSubjectList Method end");
		return data;
	}
	
	/**
	 * 收藏诊所或医生
	 * @param clinicID
	 * @param doctorID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileSaveFavoriteClinicOrDoctor.do",method=RequestMethod.POST)
	public JSONObject mobileSaveFavoriteClinicOrDoctor(@RequestParam(value="clinicID")String clinicID, 
			@RequestParam(value="doctorID")String doctorID){
		logger.begin("OrderController.mobileSaveFavoriteClinicOrDoctor Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				data.put("userID", userID);
				data.put("clinicID", clinicID);
				data.put("doctorID", doctorID);
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileSaveFavoriteClinicOrDoctor(data.toString());
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileSaveFavoriteClinicOrDoctor Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileSaveFavoriteClinicOrDoctor Method end");
		return data;
	}
	
	/**
	 * 取消收藏诊所或医生
	 * @param clinicID
	 * @param doctorID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mobileDeleteFavoriteClinicOrDoctor.do",method=RequestMethod.POST)
	public JSONObject mobileDeleteFavoriteClinicOrDoctor(@RequestParam(value="clinicID")String clinicID, 
			@RequestParam(value="doctorID")String doctorID){
		logger.begin("OrderController.mobileDeleteFavoriteClinicOrDoctor Method begin");
		JSONObject data = new JSONObject();
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()){
			logger.end("User Session Timeout Or Never to Login.");
			data.put("status", "1");// 超时
			return data;
		}
		try {
			UserManage userManage = (UserManage)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.Y3U_SESSION_USER);
			if (null != userManage){
				Integer userID = userManage.getUserID();
				data.put("userID", userID);
				data.put("clinicID", clinicID);
				data.put("doctorID", doctorID);
				IMobileService mobileService = (IMobileService) HessionFactory.getService(IMobileService.class, InterfacesConstants.YU_MOBILE_SERVICE);
				data = mobileService.mobileDeleteFavoriteClinicOrDoctor(data.toString());
				data.put("status", "0");// 正常
				logger.debug("JSONObject:", data.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OrderController.mobileDeleteFavoriteClinicOrDoctor Method error", e.getMessage());
			data.put("status", "2");//异常
		}
		logger.end("OrderController.mobileDeleteFavoriteClinicOrDoctor Method end");
		return data;
	}
	
}
