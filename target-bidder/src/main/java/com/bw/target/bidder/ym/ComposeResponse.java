package com.bw.target.bidder.ym;

import java.util.Map.Entry;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.model.Bidding.BidResponse;

public interface ComposeResponse {

	/**
	 * 依照选中的Map<groupID,weight>组装BidResponse。
	 * <br>Date:2015年9月6日 上午10:55:54
	 * @author pujie
	 * @param container
	 * @param groupWeight  groupId-weight的键值映射
	 * @return bidResponse
	 */
	public abstract void fillResponse(AdContainer container,Entry<Integer,Integer> groupWeight,BidResponse.Builder response,Bidding.BidRequestOrBuilder request);
}
