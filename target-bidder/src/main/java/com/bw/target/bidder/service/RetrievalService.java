package com.bw.target.bidder.service;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;

/**
 * 广告检索。检索出此次竞价满足投放条件的所有<推广组>
 * <br>Date:2015年8月27日 下午3:51:46
 * @author pujie
 */
public interface RetrievalService {

	public void init();
	/**
	 * 根据request、campaign信息检索出所有满足投放条件的<推广组>
	 * <br>Date:2015年8月27日 下午4:14:59
	 * @author pujie
	 * @param campaigns
	 * @param request
	 */
	public void retrieval(AdContainer container,BidRequestOrBuilder request);
}
