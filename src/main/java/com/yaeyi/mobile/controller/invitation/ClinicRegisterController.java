package com.yaeyi.mobile.controller.invitation;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yaeyi.api.service.clientClinicsManage.IClientClinicsService;
import com.yaeyi.mobile.common.CommonConstant;
import com.yaeyi.mobile.common.InterfacesConstants;
import com.yaeyi.mobile.service.log.impl.SCLogger;
import com.yaeyi.mobile.service.shiro.UserManage;
import com.yaeyi.mobile.util.HessionFactory;
import com.yaeyi.mobile.util.JacksonUtils;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/invitation")
public class ClinicRegisterController {
	/**
	 * @Fields logger :日志记录
	 */
	private static final SCLogger logger = new SCLogger(ClinicRegisterController.class);
	
	
	/**
	 * 诊所注册
	 * 
	 * @return 
	 */
	@RequestMapping(value="/registSub.do",method=RequestMethod.POST)
	@ResponseBody
	public Object registSub  (@RequestParam("userPhone") String userPhone,@RequestParam("verifyCode") String verifyCode,@RequestParam("clinicName") String clinicName
			,@RequestParam("location") String location,@RequestParam("email") String email) {
		logger.begin("#### MyAccountController.commonCheckVerifyCode   Method ####");
		JSONObject objectList = new JSONObject();
		try {
			IClientClinicsService mobileService=(IClientClinicsService) HessionFactory.getService(IClientClinicsService.class, InterfacesConstants.CLIENT_CLINICS_SERVICE);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("verifyCode", verifyCode);
			parameterMap.put("clientClinicPhone", userPhone);
			parameterMap.put("clientClinicName", clinicName);
			parameterMap.put("clientClinicAddress", location);
			parameterMap.put("clientClinicEmail", email);
			logger.debug("parameterMap ", parameterMap);
			objectList= mobileService.saveClientClinicsRegister(JacksonUtils.getJson(parameterMap));
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
	
	
	
}
