package com.bw.target.bidder.rank;

import java.util.Map.Entry;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;

/**
 * 对广告进行价值排序
 * <br>Date:2015年9月1日 下午6:33:59
 * @author pujie
 */
public interface Ranking {

	/**
	 * 初始化ranking实例
	 * <br>Date:2015年9月1日 下午6:41:00
	 * @author pujie
	 */
	public void init();
	/**
	 * 选择最优的一组策略进行投放。
	 * <br>Date:2015年9月1日 下午6:37:04
	 * @author pujie
	 * @param container
	 * @return Entry<groupID, price>
	 */
	public Entry<Integer, Integer> ranking(AdContainer container,BidRequestOrBuilder request);
}
