package com.bw.target.bidder.service;

import com.bw.target.bidder.model.Bidding;
/**
 * 
 * Date: 2015年8月14日 下午6:46:16 &lt;br/&gt;
 * @author pujie
 */
public interface BiddingService {

	public void init();
	/**
	 * 竞价服务
	 * @param request
	 * @param response
	 */
	public Bidding.BidResponseOrBuilder bidding(Bidding.BidRequestOrBuilder request);
}
