package com.bw.target.adaptor.service.log;

import java.util.Calendar;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.common.util.StringUtil;

import com.bw.target.adaptor.listener.SystemContext;
import com.bw.target.adaptor.service.BidLogger;
import com.bw.target.adaptor.util.Constant;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.model.Bidding.BidResponse;
/**
 * 日志记录器的默认实现。异步记录竞价请求和应答日志信息。
 * Date: 2015年8月18日 下午3:04:23 &lt;br/&gt;
 * @author pujie
 */
public abstract class DefaultBidLogger implements BidLogger {

	private static Logger responseInfo = Logger.getLogger("bid_response");
	private static Logger requestInfo = Logger.getLogger("bid_request");
	
	ThreadPoolExecutor executor = SystemContext.executor;
	
	public abstract <T> String getRequestStr(T request);
	
	@Override
	public <T> void logRequest(final T request, Integer channel) {
		final DefaultBidLogger handle = this;
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				requestInfo.info(handle.getRequestStr(request));
			}
		});
	}

	@Override
	public void logResponse(final Bidding.BidResponseOrBuilder response,final Integer channel) {
		if(response.getAdCount()<1)return; //如果无广告应答，则不打印应答日志
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				Calendar calendar = Calendar.getInstance();
				StringBuilder sb = new StringBuilder();
				BidResponse.AdOrBuilder ad = response.getAdOrBuilder(0);//目前只支持返回一个广告
				sb.append(channel).append(Constant.COLUMN_SEPARATOR)
				.append(response.getBid()).append(Constant.COLUMN_SEPARATOR)
				.append(response.getProcessingTime()).append(Constant.COLUMN_SEPARATOR)
				.append(ad.getSid()).append(Constant.COLUMN_SEPARATOR)
				.append(ad.getBidPrice()).append(Constant.COLUMN_SEPARATOR)
				.append(ad.getCreativeId()).append(Constant.COLUMN_SEPARATOR)
				.append(ad.getCreativeType()).append(Constant.COLUMN_SEPARATOR)
				.append(ad.getGroupId()).append(Constant.COLUMN_SEPARATOR)
				.append(ad.getCategory()).append(Constant.COLUMN_SEPARATOR)
				.append(StringUtil.list2String(ad.getClickUrlList(), Constant.SEPARATOR_COMMA)).append(Constant.COLUMN_SEPARATOR)
				.append(ad.getLandingPage()).append(Constant.COLUMN_SEPARATOR)
				.append(calendar.get(Calendar.DAY_OF_WEEK)).append(Constant.COLUMN_SEPARATOR)
				.append(calendar.get(Calendar.HOUR_OF_DAY)).append(Constant.COLUMN_SEPARATOR)
				.append(System.currentTimeMillis());
				responseInfo.info(sb.toString());
			}
		});
	}

}
