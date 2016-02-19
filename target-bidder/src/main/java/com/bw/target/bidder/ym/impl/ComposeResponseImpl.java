package com.bw.target.bidder.ym.impl;

import java.util.Map.Entry;

import org.framework.cache.redis.JedisClusterFactory;

import redis.clients.jedis.JedisCluster;

import com.bw.target.bidder.Constant;
import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdCreative;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.model.Bidding.BidRequest;
import com.bw.target.bidder.model.Bidding.BidResponse;
import com.bw.target.bidder.model.Bidding.BidResponse.Ad;
import com.bw.target.bidder.ym.ComposeResponse;
/**
 * 组装返回的response
 * <br>Date:2015年9月15日 下午2:23:07
 * @author pujie
 */
public class ComposeResponseImpl implements ComposeResponse{
	
	private JedisCluster jc =JedisClusterFactory.getcluster("dsp");

	@Override
	public void fillResponse(AdContainer container,Entry<Integer,Integer> groupWeight,BidResponse.Builder response,Bidding.BidRequestOrBuilder request) {
		
		response.setAdxId(request.getAdxId());
		
		Ad.Builder ad = Ad.newBuilder();
		BidRequest.AdSlotOrBuilder slot = request.getAdslotOrBuilder(0);
		AdGroup group = container.getGroup(groupWeight.getKey());
		AdCreative creative = group.getCreativeById(container.getSession().getSelectedCreativeId());
		response.setCustId(group.getParent().getCustId())
		.setClickCrowdId(group.getParent().getClickCrowdId());
		ad.setSid(slot.getSid())
		.setImpid(slot.getImpid())
		.setSequenceId(slot.getSequenceId())
		.setCreativeId(creative.getCreativeId())
		.setGroupId(group.getGroupId())
		.setCampaignId(group.getParent().getCampaignId())
		.setHtmlSnippet(creative.getHtmlSnippet())
		.setSize(creative.getSize())
		.setBidPrice(group.getFixedPrice()) //固定价格报价
		.addClickUrl(creative.getClickUrl())
		.setLandingPage(creative.getLandingPage())
		.setCreativeUrl(creative.getCreativeUrl())
		.setCreativeType(creative.getCreativeType());
		//消耗累计
		jc.hincrBy(Constant.TEM_KEY_CAMPAING_PRE+group.getParent().getCampaignId(), Constant.TEM_KEY_CAMPAING_B_TSPEND, group.getFixedPrice());
		jc.hincrBy(Constant.TEM_KEY_CAMPAING_PRE+group.getParent().getCampaignId(), Constant.TEM_KEY_CAMPAING_B_SPEND, group.getFixedPrice());
		//余额扣除
		jc.hincrBy(Constant.TEM_KEY_CUST_PRE + group.getParent().getCustId(), Constant.TEM_KEY_CUST_B_BLANCE, -group.getFixedPrice());
		response.addAd(ad);
	}

}
