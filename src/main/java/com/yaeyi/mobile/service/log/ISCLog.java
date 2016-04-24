package com.yaeyi.mobile.service.log;

public interface ISCLog {
	void begin(Object... msgs);

	void end(Object... msgs);

	void debug(String label, Object msg);

	void info(Object msg);

	void info(Object msg, Throwable e);

	void error(Object msg);

	void error(Object msg, Throwable e);
}
