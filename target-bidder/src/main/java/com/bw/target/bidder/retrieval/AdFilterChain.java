package com.bw.target.bidder.retrieval;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;

/**
 * 过滤器链
 * <br>Date:2015年8月26日 下午6:43:42
 * @author pujie
 */
public interface AdFilterChain {

	/**
	 * 初始化过滤器链
	 * <br>Date:2015年8月26日 下午6:44:56
	 * @author pujie
	 */
	public void init();
	/**
	 * 执行过责任链。全部通过则返回true
	 * <br>Date:2015年8月26日 下午6:44:16
	 * @author pujie
	 */
	public boolean doFilter(AdGroup group,Bidding.BidRequestOrBuilder request,AdContainer container);
}
