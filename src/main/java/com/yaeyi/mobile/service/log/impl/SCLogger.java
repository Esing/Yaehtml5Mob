package com.yaeyi.mobile.service.log.impl;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.yaeyi.mobile.service.log.ISCLog;
 

public final class SCLogger implements ISCLog {
	private Logger logger = null;

	private XStream xStream = null;

	public <T> SCLogger(Class<T> clazz) {

		logger = Logger.getLogger(clazz);
		xStream = new XStream();
	}

	public void begin(Object... msg) {
		if (logger.isDebugEnabled()) {
			beginOrEnd(true, msg);
		}
	}

	public void end(Object... msg) {
		if (logger.isDebugEnabled()) {
			beginOrEnd(false, msg);
		}
	}

	private void beginOrEnd(boolean isBegin, Object... msg) {
		final StringBuilder outString = new StringBuilder(512);

		addStackTraceInfo(outString, 4);

		if (isBegin) {
			outString.append("Begin |");
		} else {
			outString.append("End |");
		}

		if (null != msg && msg.length > 0) {
			outString.append(" -> ");

			for (final Object obj : msg) {
				if (isSimpleClass(obj)) {
					outString.append(obj);
				} else {
					outString.append(xStream.toXML(obj));
				}

				outString.append(", ");
			}
		}

		logger.debug(outString.toString());
	}

	public void debug(String label, Object msg) {
		if (!logger.isDebugEnabled()) {
			return;
		}

		final StringBuilder outString = new StringBuilder(512);

		addStackTraceInfo(outString, 3);

		if (null != label) {
			outString.append(label).append(" -> ");
		}

		if (null == msg) {
			outString.append("null");
		} else {
			if (isSimpleClass(msg)) {
				outString.append(msg);
			} else {
				outString.append(xStream.toXML(msg));
			}
		}

		logger.debug(outString.toString());
	}

	public void error(Object msg, Throwable e) {
		logger.error(msg, e);
	}

	public void error(Object msg) {
		logger.error(msg);
	}

	public void info(Object msg, Throwable e) {
		if (!logger.isInfoEnabled()) {
			return;
		}

		StringBuilder outString = new StringBuilder(512);
		addStackTraceInfo(outString, 3);
		outString.append(msg);

		logger.info(outString.toString(), e);
	}

	public void info(Object msg) {
		if (!logger.isInfoEnabled()) {
			return;
		}

		StringBuilder outString = new StringBuilder(512);
		addStackTraceInfo(outString, 3);
		outString.append(msg);
		logger.info(outString.toString());
	}

	private final void addStackTraceInfo(StringBuilder outString, int level) {
		final StackTraceElement ste = Thread.currentThread().getStackTrace()[level];
		outString.append(".");
		outString.append(ste.getMethodName());
		outString.append("(");
		outString.append(ste.getFileName());
		outString.append(":");
		outString.append(ste.getLineNumber());
		outString.append(") | ");
	}

	private boolean isSimpleClass(Object msg) {
		if (msg instanceof String || msg instanceof Integer || msg instanceof Long) {
			return true;
		}

		return false;
	}
}
