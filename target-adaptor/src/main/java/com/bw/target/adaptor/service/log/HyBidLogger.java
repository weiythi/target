package com.bw.target.adaptor.service.log;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.bw.target.adaptor.controller.BesController;
import com.bw.target.adaptor.util.Constant;

/**
 * 打印海云竞价日志.以json格式打印. 第一个字段为渠道
 * <br>Date:2015年10月20日 上午10:39:14
 * @author pujie
 */
public class HyBidLogger extends DefaultBidLogger {
	
	private static Logger logger = Logger.getLogger(BesController.class);
	
	@Override
	public <T> String getRequestStr(T request) {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(Constant.CHANNEL_HY).append(Constant.COLUMN_SEPARATOR)
			.append(Constant.LOG_TYPE_REQUEST).append(Constant.COLUMN_SEPARATOR)
			.append(JSON.toJSONString(request))
			.append(Constant.COLUMN_SEPARATOR)
			.append(System.currentTimeMillis());
		} catch (Exception e) {
			logger.error("海云日志收集异常：", e);
		}
		return  sb.toString();
	}

}
