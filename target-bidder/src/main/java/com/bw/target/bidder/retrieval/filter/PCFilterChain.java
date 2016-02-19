package com.bw.target.bidder.retrieval.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.retrieval.AdFilter;
import com.bw.target.bidder.retrieval.AdFilterChain;
/**
 * 初始化广告过滤器，并把所有过滤器组成一个责任链。
 * <br>Date:2015年8月26日 下午6:59:41
 * @author pujie
 */
public class PCFilterChain implements AdFilterChain {

	private List<AdFilter> filters = Collections.emptyList();
	
	@Override
	public void init() {
		filters =new ArrayList<AdFilter>(4);
		filters.add(new DateFilter());
		filters.add(new AmountControlFilter());
		filters.add(new FrequencyControlFilter());
		filters.add(new CreativeFilter());
	}

	@Override
	public boolean doFilter(AdGroup group,Bidding.BidRequestOrBuilder request,AdContainer container) {
		boolean flag = false;
		for(AdFilter f:filters){
			flag = f.filter(group,request,container);
			if(flag == false)break;
		}
		return flag;
	}

}
