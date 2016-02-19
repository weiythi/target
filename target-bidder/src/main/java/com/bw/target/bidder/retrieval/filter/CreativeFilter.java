package com.bw.target.bidder.retrieval.filter;

import org.common.util.NumberUtil;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdCreative;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding.BidRequest.AdSlotOrBuilder;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;
import com.bw.target.bidder.retrieval.AdFilter;
/**
 * 过滤出符合投放条件的创意列表。如果列表大于1，则随机选取一个创意。
 * <br>Date:2015年9月14日 下午1:51:04
 * @author pujie
 */
public class CreativeFilter implements AdFilter {

	@Override
	public boolean filter(AdGroup group, BidRequestOrBuilder request,AdContainer container) {
		boolean flag = false;
		AdSlotOrBuilder slot = request.getAdslotOrBuilder(0);
		int[] matched = new int[group.getCreatives().size()];
		int index = 0;
		for(AdCreative creative:group.getCreatives()){
			if(creative.containsChannel(request.getChannel()) && //创意通过审核
					creative.getSize().equals(slot.getSize())){ //创意尺寸筛选
				if(slot.getExcludeCreativeTypeCount() ==0 ||
						(slot.getExcludeCreativeTypeCount()>0 && !slot.getExcludeCreativeTypeList().contains(creative.getCreativeType()))){ //媒体禁止的创意类型
					matched[index ++] = creative.getCreativeId();
				}
			}
		}
		if(index >0){
			container.getSession().setSelectedCreativeId(matched[NumberUtil.random(index)]);//随机选择一个符合条件的创意
			flag = true;
		}
		return flag;
	}

}
