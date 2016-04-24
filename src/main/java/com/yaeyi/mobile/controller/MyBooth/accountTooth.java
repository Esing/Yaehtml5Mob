package com.yaeyi.mobile.controller.MyBooth;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yaeyi.api.service.mobileManage.IMobileService;
import com.yaeyi.mobile.common.CommonConstant;
import com.yaeyi.mobile.common.InterfacesConstants;
import com.yaeyi.mobile.controller.Regiter.RegiterController;
import com.yaeyi.mobile.service.log.impl.SCLogger;
import com.yaeyi.mobile.service.shiro.UserManage;
import com.yaeyi.mobile.util.HessionFactory;

/*
 * 
 * @author feather
 *
 */
@Controller
@RequestMapping("/accountTooth")
public class accountTooth {
	/**
	 * @Fields logger :日志记录
	 */
	private static final SCLogger logger = new SCLogger(RegiterController.class);
	/**
	 * 
	 * {@docRoot oa/MyTooth/my-tooth-no1.html所需路径
	 * 功能：显示用户的预约记录
	 * }
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/trearecords.do", method = RequestMethod.POST)
	@ResponseBody
	public Object treatmentrecords(@RequestParam(value = "userid") String userid) {
		logger.begin("accountTooth.treatmentrecords Method begin");
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		JSONObject para = new JSONObject();
		// // 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			logger.end("User Session Timeout Or Never to Login.");
			htmlresult.put("code", 1);// 超时
			logger.end("treatmentrecords-htmlresult", htmlresult.toString());
			return htmlresult;
		}
		try {
			UserManage userManage = (UserManage) SecurityUtils.getSubject().getSession()
					.getAttribute(CommonConstant.Y3U_SESSION_USER);
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			para.put("userID", userManage.getUserID());
			JSONObject json = ser.mobileQueryUserBookedOrderList(para.toString());
			htmlresult.put("date",
					json.getString("orderList").equals("") ? new JSONArray() : json.getJSONArray("orderList"));//
			htmlresult.put("code", 0);// json.getInt("resultCode") == 0 ? 0 : 2
			htmlresult.put("titleHead", userManage.getUserICON());
			htmlresult.put("titleName", userManage.getUserName());
			htmlresult.put("patientid", userManage.getPatientID());
		} catch (Exception e) {
			logger.debug("treatmentrecords-Exception", e.getMessage());
		} finally {
			logger.info("treatmentrecords-htmlresult" + htmlresult.toString());
			logger.end("accountTooth.treatmentrecords Method end");
		}
		// 此处需要加上错误返回嘛到json中
		return htmlresult;
	}

	/**
	 * 查询病人的就诊过的诊所集合集合
	 * 
	 * @param patientID
	 * @param userID
	 * @return -1表示失败，0表示成功
	 */
	@RequestMapping(value = "/treatmeclinics.do", method = RequestMethod.POST)
	@ResponseBody
	public Object treatmeclinics(@RequestParam(value = "patientID") String patientID) {
		logger.begin("accountTooth.treatmeclinics Method begin");
		JSONObject para = new JSONObject();
		para.put("patientID", patientID);
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			logger.end("User Session Timeout Or Never to Login.");
			htmlresult.put("code", 1);// 超时
			return htmlresult;
		}
		try {
			logger.info(htmlresult.toString());
			// 获取到 IMobileService 对象
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			JSONObject result = ser.mobileQueryPatientClinicList(para.toString());
			htmlresult.put("date",
					result.getString("clinicList").equals("") ? new JSONArray() : result.getJSONArray("clinicList")); //
			htmlresult.put("code", 0);
		} catch (Exception e) {
			logger.debug("treatmeclinics-Exception", e.getMessage());
		} finally {
			logger.info(htmlresult.toString());
			logger.end("accountTooth.treatmeclinics Method end");
		}
		return htmlresult;
	}

	/**
	 * 查询病人的就诊过的医生集合
	 * 
	 * @param patientID
	 * @param userID
	 * @return -1表示失败，0表示成功
	 */
	@RequestMapping(value = "/treatmedoctors.do", method = RequestMethod.POST)
	@ResponseBody
	public Object treatmedoctors(@RequestParam(value = "patientID") String patientID) {
		logger.begin("accountTooth.treatmedoctors Method begin");
		JSONObject para = new JSONObject();
		para.put("patientID", Integer.valueOf(patientID));
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			logger.end("User Session Timeout Or Never to Login.");
			htmlresult.put("code", 1);// 超时
			return htmlresult;
		}
		try {
			logger.info(htmlresult.toString());
			// 获取到 IMobileService 对象
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			logger.info("accountTooth.treatmedoctors --para" + para.toString());
			JSONObject result = ser.mobileQueryPatientDoctorList(para.toString());
			htmlresult.put("date",
					result.getString("doctorList").equals("") ? new JSONArray() : result.getJSONArray("doctorList"));
			htmlresult.put("code", 0);
		} catch (Exception e) {
			logger.debug("treatmedoctors-Exception", e.getMessage());
		} finally {
			logger.info(htmlresult.toString());
			logger.end("accountTooth.treatmedoctors Method end");
		}
		return htmlresult;
	}

	/**
	 * 查询账号下面某个就诊者未完成订单 html:my-tooth-no8.html
	 * 
	 * @param patientID
	 * @return
	 */
	@RequestMapping(value = "/querytreadOrder.do", method = RequestMethod.POST)
	@ResponseBody
	public Object querytreadOrder(@RequestParam(value = "patientID") String patientID) {
		JSONObject para = new JSONObject();
		para.put("patientID", patientID);
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			logger.end("User Session Timeout Or Never to Login.");
			htmlresult.put("code", 1);// 超时
			return htmlresult;
		}
		try {
			// 获取到 IMobileService 对象
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			logger.info("accountTooth.querytreadOrder --para" + para.toString());
			JSONObject result = ser.mobileQueryPatientProcessingOrderList(para.toString());
			htmlresult.put("date", result.getJSONObject("patient"));//
			htmlresult.put("code", 0);
		} catch (Exception e) {
			logger.debug("treatmedoctors-Exception", e.getMessage());
		} finally {
			logger.info(htmlresult.toString());
			logger.end("accountTooth.querytreadOrder Method end");
		}
		return htmlresult;
	}

	/**
	 * 查询病人资料
	 * 
	 * @param patientID
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/queryPatient.do", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPatientinfo(@RequestParam(value = "patientID") String patientID,
			@RequestParam(value = "userid") String userid) {
		JSONObject para = new JSONObject();
		para.put("patientID", patientID);

		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		// 检查用户是否登录或会话超时
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			logger.end("User Session Timeout Or Never to Login.");
			htmlresult.put("code", 1);// 超时
			return htmlresult;
		}
		UserManage userManage = (UserManage) SecurityUtils.getSubject().getSession()
				.getAttribute(CommonConstant.Y3U_SESSION_USER);
		if (null != userManage) {
			Integer userID = userManage.getUserID();
			para.put("useID", userID);
			try {
				logger.info(htmlresult.toString());
				// 获取到 IMobileService 对象
				IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
						InterfacesConstants.YU_MOBILE_SERVICE);
				logger.info("accountTooth.queryPatientinfo --para" + para.toString());
				JSONObject result = ser.mobileQueryUserContactPatientDetail(para.toString());
				htmlresult.put("date",
						result.getString("patient").equals("") ? new JSONArray() : result.getJSONArray("resultMSG"));
				htmlresult.put("code", 0);
			} catch (Exception e) {
				logger.info(htmlresult.toString());
				logger.debug("treatmedoctors-Exception", e.getMessage());
			} finally {
				logger.info(htmlresult.toString());
				logger.end("accountTooth.queryPatientinfo Method end");
			}
		} else {
			htmlresult.put("code", 1);
		}
		return htmlresult;
	}

	/**
	 * 查询就诊的集合
	 * 
	 * @param patientID
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/querytreadLists.do", method = RequestMethod.POST)
	@ResponseBody
	public Object querytreadLists(@RequestParam(value = "patientID") String patientID) {
		JSONObject para = new JSONObject();
		para.put("patientID", patientID);
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		try {
			// 检查用户是否登录或会话超时
			if (!SecurityUtils.getSubject().isAuthenticated()) {
				logger.end("User Session Timeout Or Never to Login.");
				htmlresult.put("code", 1);// 超时
				return htmlresult;
			}
			logger.info(htmlresult.toString());
			// 获取到 IMobileService 对象
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			logger.info("accountTooth.querytreadLists --para" + para.toString());
			JSONObject result = ser.mobilePatientFinishedOrderList(para.toString());
			logger.info("accountTooth.querytreadLists-result:" + result.toString());
			htmlresult.put("date", result.getJSONObject("patient"));
			htmlresult.put("code", 0);
		} catch (Exception e) {
			logger.debug("treatmedoctors-Exception", e.getMessage());
			// }finally{
			logger.info(htmlresult.toString());
			logger.end("accountTooth.querytreadLists Method end");
		}
		return htmlresult;
	}

	/**
	 * 查询订单详情
	 * 
	 * @param patientID
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/querytreadOrderDesc.do", method = RequestMethod.POST)
	@ResponseBody
	public Object querytreadDesc(@RequestParam(value = "patientID") String patientID,
			@RequestParam(value = "orderID") String orderID) {
		JSONObject para = new JSONObject();
		para.put("patientID", patientID);
		para.put("orderID", orderID);
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		try {
			// 检查用户是否登录或会话超时
			if (!SecurityUtils.getSubject().isAuthenticated()) {
				logger.end("User Session Timeout Or Never to Login.");
				htmlresult.put("code", 1);// 超时
				return htmlresult;
			}
			logger.info(htmlresult.toString());
			// 获取到 IMobileService 对象
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			logger.info("accountTooth.querytreadDesc --para" + para.toString());
			JSONObject result = ser.mobileQueryPatientTreatOrderDetail(para.toString());
			logger.info(result.toString());
			htmlresult.put("date", result.isEmpty() ? new JSONObject() : result.getJSONObject("treatOrder"));
			htmlresult.put("code", 0);
		} catch (Exception e) {
			logger.debug("treatmedoctors-Exception", e.getMessage());
		} finally {
			logger.info(htmlresult.toString());
			logger.end("accountTooth.querytreadDesc Method end");
		}
		return htmlresult;
	}

	/**
	 * 查询订单详情
	 * 
	 * @param patientID
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/queryPatientList.do", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPatientList() {
		JSONObject para = new JSONObject();
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		try {
			// 检查用户是否登录或会话超时
			if (!SecurityUtils.getSubject().isAuthenticated()) {
				logger.end("User Session Timeout Or Never to Login.");
				htmlresult.put("code", 1);// 超时
				return htmlresult;
			}
			UserManage userManage = (UserManage) SecurityUtils.getSubject().getSession()
					.getAttribute(CommonConstant.Y3U_SESSION_USER);
			logger.info(userManage.toString());

			para.put("userID", userManage.getUserID());
			logger.info(htmlresult.toString());
			// 获取到 IMobileService 对象
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			logger.info("accountTooth.queryPatientList --para" + para.toString());
			JSONObject result = ser.mobileQueryExceptUserContactPatientList(para.toString());
			htmlresult.put("date",
					result.getString("patientList").equals("") ? new JSONArray() : result.getJSONArray("patientList"));
			htmlresult.put("headimg", userManage.getUserICON());
			htmlresult.put("PatientName", userManage.getUserName());
			htmlresult.put("PatientID", userManage.getPatientID());
			htmlresult.put("code", 0);
		} catch (Exception e) {
			logger.debug("treatmedoctors-Exception", e.getMessage());
		} finally {
			logger.info(htmlresult.toString());
			logger.end("accountTooth.queryPatientList Method end");
		}
		return htmlresult;
	}

	/**
	 * 查询诊所的授权详情
	 * 
	 * @param patientID
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/queryclinicAuto.do", method = RequestMethod.POST)
	@ResponseBody
	public Object queryclinicAuto(@RequestParam(value = "patientID") String patientID,
			@RequestParam(value = "clinicID") String clinicID) {
		JSONObject para = new JSONObject();
		para.put("patientID", patientID);
		para.put("clinicID", clinicID);
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		try {
			// 检查用户是否登录或会话超时
			if (!SecurityUtils.getSubject().isAuthenticated()) {
				logger.end("User Session Timeout Or Never to Login.");
				htmlresult.put("code", 1);// 超时
				return htmlresult;
			}
			logger.info(htmlresult.toString());
			// 获取到 IMobileService 对象
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			logger.info("accountTooth.queryclinicAuto --para" + para.toString());
			JSONObject result = ser.mobileQueryClinicAuthorizedStatus(para.toString());
			logger.info(result.toString());
			htmlresult.put("date", result.getString("clinicAuthorized").equals("") ? new JSONArray() : result.getJSONArray("clinicAuthorized"));
			htmlresult.put("code", 0);
		} catch (Exception e) {
			logger.debug("treatmedoctors-Exception", e.getMessage());
		} finally {
			logger.info(htmlresult.toString());
			logger.end("accountTooth.queryclinicAuto Method end");
		}
		return htmlresult;
	}

	/**
	 * 修改诊所的授权详情
	 * 
	 * @param patientID
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/updateclinicAuto.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateclinicAuto(@RequestParam(value = "patientID") String patientID,
			@RequestParam(value = "clinicID") String clinicID,
			@RequestParam(value = "basicAuthorized") int basicAuthorized,
			@RequestParam(value = "medicineAuthorized") int medicineAuthorized) {
		JSONObject para = new JSONObject();
		para.put("patientID", patientID);
		para.put("clinicID", clinicID);
		para.put("basicAuthorized", basicAuthorized);
		para.put("medicineAuthorized", medicineAuthorized);
		JSONObject htmlresult = new JSONObject();
		htmlresult.put("code", 2);
		try {
			// 检查用户是否登录或会话超时
			if (!SecurityUtils.getSubject().isAuthenticated()) {
				logger.end("User Session Timeout Or Never to Login.");
				htmlresult.put("code", 1);// 超时
				return htmlresult;
			}
			UserManage userManage = (UserManage) SecurityUtils.getSubject().getSession()
					.getAttribute(CommonConstant.Y3U_SESSION_USER);
			logger.info(htmlresult.toString());
			// 获取到 IMobileService 对象
			IMobileService ser = (IMobileService) HessionFactory.getService(IMobileService.class,
					InterfacesConstants.YU_MOBILE_SERVICE);
			logger.info("accountTooth.updateclinicAuto --para" + para.toString());
			JSONObject result = ser.mobileUpdateClinicAuthorizedStatus(para.toString());
			logger.info(result.toString());
			htmlresult.put("date", result.isEmpty() ? new JSONArray() : result.getJSONObject("resultMSG"));
			htmlresult.put("code", 0);
			htmlresult.put("titleHead", userManage.getUserICON());
			htmlresult.put("titleName", userManage.getUserName());
			htmlresult.put("patientID", userManage.getPatientID());
		} catch (Exception e) {
			logger.debug("treatmedoctors-Exception", e.getMessage());
		} finally {
			logger.info(htmlresult.toString());
			logger.end("accountTooth.updateclinicAuto Method end");
		}
		return htmlresult;
	}

}
