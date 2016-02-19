package com.bw.target.adaptor.service;

import com.bw.target.bidder.model.Bidding;

/**
 * 
 * Date: 2015年8月13日 下午5:48:52 &lt;br/&gt;
 * @author pujie
 */
public interface BidLogger {
	
	/**
	 * 根据渠道不同记录，返回规范后的request日志串。
	 * @param request
	 * @param channel
	 */
	public <T> void logRequest(T request,Integer channel);
	
	/**
	 * 返回竞价应答的规范化 日志串。
	 */
	public void logResponse(Bidding.BidResponseOrBuilder response,Integer channel);
}
