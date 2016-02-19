package com.bw.target.bidder.retrieval;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;

/**
 * 定向者。执行所有<上下文定向>和<人群定向>
 * <br>Date:2015年8月27日 下午6:20:28
 * @author pujie
 */
public interface Targeter {

	/**
	 * 对进行定向信息匹配。如果匹配失败则
	 * <br>Date:2015年8月27日 下午6:24:31
	 * @author pujie
	 * @param request
	 */
	public boolean doTarget(AdContainer container,AdGroup group,String targetResource);
	/**
	 * 用户的定向属性<br>
	 * ex：用户所在地域-用于地域定向；用户的所属的特征标签-用于特征标签定向<br>
	 * 此方法目的是对相关resource进行一次计算，达到共享的目的。
	 * <br>Date:2015年9月1日 下午4:32:26
	 * @author pujie
	 */
	public String generateResource(Bidding.BidRequestOrBuilder request);
}
