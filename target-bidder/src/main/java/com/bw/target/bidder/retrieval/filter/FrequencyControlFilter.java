package com.bw.target.bidder.retrieval.filter;

import org.common.util.NumberUtil;
import org.framework.cache.redis.JedisClusterFactory;

import redis.clients.jedis.JedisCluster;

import com.bw.target.bidder.Constant;
import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;
import com.bw.target.bidder.retrieval.AdFilter;
/**
 * 频次控制<cookie每天n次>
 * <br>Date:2015年9月7日 下午2:36:02
 * @author pujie
 */
public class FrequencyControlFilter implements AdFilter {
	
	private JedisCluster jc =JedisClusterFactory.getcluster("dsp");

	@Override
	public boolean filter(AdGroup group, BidRequestOrBuilder request,AdContainer container) {
		boolean flag = true;
		if(group.getFrequencyDay() >0 ){ // 该group需要做频次控制
			String gidAdxid = group.getGroupId() + request.getAdxId();
			int frequency = NumberUtil.toInt(jc.get(Constant.TEMP_KEY_GROUP_F_PRE + gidAdxid));
			flag = frequency < group.getFrequencyDay()?true:false;
		}
		return flag;
	}

}
