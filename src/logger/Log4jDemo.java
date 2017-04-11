package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Log4jDemo {

	public static void main(String[] args) {
		// 创建日志记录器
		Logger logger = LogManager.getLogger(Log4jDemo.class);
		/*
		 * 记录日志信息
		 * 
		 * log4j提供了6种不同级别的信息：
		 * fatal	致命
		 * error	错误
		 * warn 	警告
		 * info 	普通信息
		 * debug 	调试信息
		 * trace	跟踪
		 * 
		 * 日志的级别用来指定这条日志信息的重要程度，它们之间的关系为
		 * fatal>error>warn>info>debug>trace
		 * 
		 * Log4j有一个规则：只输出级别不低于设定级别的日志信息
		 * 假设级别设定为INFO，则级别比INFO低的DEBUG则不会输出。
		 */
		for (int i=0;i<10000;i++) {
			logger.trace("trace");
			logger.debug("debug");
			logger.info("info");
			logger.warn("warn");
			logger.error("error");
			logger.fatal("fatal");
		}
	}
}