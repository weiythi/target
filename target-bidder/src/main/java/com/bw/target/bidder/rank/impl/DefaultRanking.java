package com.bw.target.bidder.rank.impl;

import java.util.Map.Entry;
import java.util.Random;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Session;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;
import com.bw.target.bidder.rank.Ranking;
/**
 * 按照权重值，以不等概率选取可投放的广告。
 * <br>Date:2015年9月1日 下午6:45:48
 * @author pujie
 */
public class DefaultRanking implements Ranking {

	private Random rd = new Random();
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entry<Integer, Integer> ranking(AdContainer container,BidRequestOrBuilder request) {	
		Entry<Integer, Integer> groupKey = unequalRandom(container.getSession());
		AdGroup group = null;
		while(null != groupKey){
			group = container.getGroup(groupKey.getKey());
			if(group.getFixedPrice() < request.getAdslotOrBuilder(0).getMinimumPrice()){ // 过滤掉 price < minimumPrice的策略
				container.getSession().remove(groupKey.getKey());
				groupKey = unequalRandom(container.getSession());
			}else{
				break;
			}
		}
		return groupKey;
	}
	/**
	 * 按权重，以放回不等概率算法选出groupID
	 * <br>Date:2015年9月6日 上午10:42:18
	 * @author pujie
	 * @param session
	 * @return
	 */
	public Entry<Integer, Integer> unequalRandom(Session session){
		if(null == session || session.isEmpty()){
			return null;
		}
		int sum = 0;
		for(Integer i:session.getWeights()){
			sum += i;
		}
		int random = Math.abs(rd.nextInt()%sum);
		int index =0;
		Entry<Integer, Integer> e = null;
		for(Entry<Integer, Integer> entry:session.entrySet()){
			index += entry.getValue();
			if(random < index){
				e = entry;break;
			}
		}
		return e;
	}

}
