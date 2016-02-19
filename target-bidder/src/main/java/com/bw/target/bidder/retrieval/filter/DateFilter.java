package com.bw.target.bidder.retrieval.filter;

import org.common.util.DateUtil;

import com.bw.target.bidder.model.AdCampaign;
import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;
import com.bw.target.bidder.retrieval.AdFilter;
/**
 * 时间过滤:是否在投放周期内
 * 投放渠道过滤：过滤出<投放渠道>
 * <br>Date:2015年9月14日 下午4:44:50
 * @author pujie
 */
public class DateFilter implements AdFilter {

	@Override
	public boolean filter(AdGroup group, BidRequestOrBuilder request,AdContainer container) {
		AdCampaign campaign = group.getParent();
		if(campaign.getStartDate().getTime() <= System.currentTimeMillis() &&
				System.currentTimeMillis() <= DateUtil.addDays(campaign.getEndDate(), 1).getTime() ){
			if(group.getChannels().contains(request.getChannel()+"")){
				return true;
			}		
		}
		return false;
	}

}
