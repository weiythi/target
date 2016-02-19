package com.bw.target.bidder.retrieval;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;

/**
 * 广告过滤器。过滤器主要针对“广告”的检索，不包括“定向”业务。
 * <br>Date:2015年8月26日 下午6:36:08
 * @author pujie
 */
public interface AdFilter {

	/**
	 * 过滤器业务实现,如果没通过过滤器验证，则返回false，否则返回true
	 * <br>Date:2015年8月26日 下午6:36:41
	 * @author pujie
	 * @return true or false
	 */
	public boolean filter(AdGroup group,Bidding.BidRequestOrBuilder request,AdContainer container);
}
