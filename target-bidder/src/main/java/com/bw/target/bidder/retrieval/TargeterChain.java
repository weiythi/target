package com.bw.target.bidder.retrieval;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.Bidding;

/**
 * 
 * <br>Date:2015年8月31日 上午11:14:55
 * @author pujie
 */
public interface TargeterChain {

	public void init();
	
	/**
	 * 定向策略链条。如果不满足则从session中移除
	 * <br>Date:2015年8月31日 上午11:17:23
	 * @author pujie
	 * @param request
	 * @param container
	 */
	public void doTarget(Bidding.BidRequestOrBuilder request,AdContainer container);
}
