package com.yaeyi.mobile.util;

import java.io.IOException;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yaeyi.mobile.service.log.impl.SCLogger;

/**
 * hession服务工厂类
 * 
 * @author
 *
 */
public class HessionFactory {

	private static final SCLogger logger = new SCLogger(HessionFactory.class);
	
	private static final HessianProxyFactory factory = new HessianProxyFactory();
	// 本地开发时先固定IP前缀，后续可以修改为可配置的
	private static String serverIP = "";

	/**
	 * 获取接口服务类
	 * 
	 * @param system
	 * @param model
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public synchronized static Object getService(Class<?> model, String interfacesName) throws Exception {
		logger.begin();
		
		// 读取配置文件中的路径
		if (null == serverIP || serverIP.equals("")) {
			logger.debug("Begin to read hessian.server", serverIP);
			
			java.util.Properties props;
			try {
				props = PropertiesLoaderUtils.loadAllProperties("server.properties");
				serverIP = props.getProperty("hessian.server");
				logger.debug("Get hessian.server Address", serverIP);
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("Hessian serverIP Exception", e);
				
				serverIP = "http://127.0.0.1:8080/YaeyiBusiness/hessian/";
			}
		}
		logger.debug("Finally get hessian.server Address", serverIP);

		Object service = factory.create(model, serverIP + interfacesName);
		logger.end();
		return service;
	}
}
