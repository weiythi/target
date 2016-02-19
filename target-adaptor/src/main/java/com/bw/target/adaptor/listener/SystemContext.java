package com.bw.target.adaptor.listener;

import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.common.util.thread.ExecutorServiceFactory;

import com.bw.target.adaptor.util.ConfigureUtil;
import com.bw.target.bidder.BidderContext;
import com.bw.target.bidder.service.BiddingService;

public class SystemContext implements ServletContextListener {

	final private static Logger LOG = Logger.getLogger(SystemContext.class);
	/**
	 * 系统线程池对象引用
	 */
	public static ThreadPoolExecutor executor;
	/**
	 * 竞价接口服务
	 */
	public static BiddingService bidder;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			//初始化系统线程池
			ExecutorServiceFactory.initialize();
			executor = ExecutorServiceFactory.getExecutor();
			ExecutorServiceFactory.printExecutorInfo();
			ConfigureUtil.init();
			//初始化bidder
			BidderContext.initialize();
			bidder = BidderContext.getBidder();
			System.out.println("竞价服务初始化成功！");
		} catch (Exception e) {
			LOG.error("系统启动失败：", e);
			System.exit(0);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(null != executor){
			executor.shutdown();
		}
		BidderContext.contextDestroyed();
	}

}
