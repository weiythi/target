package com.bw.target.adaptor.service.trans;

import com.bw.target.adaptor.model.TanxBidding;
import com.bw.target.adaptor.service.Transformer;
import com.bw.target.adaptor.util.Constant;
import com.bw.target.adaptor.util.HtmlSnippetEngine;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.model.Bidding.BidRequest;
/**
 * 
 * <br>Date:2015年9月15日 下午3:13:36
 * @author pujie
 */
public class TanxTransformer implements Transformer {

	@Override
	public <T> BidRequest.Builder tansRequest(T bidRequest) {
		TanxBidding.BidRequest.Builder tanx = (TanxBidding.BidRequest.Builder)bidRequest;
		BidRequest.Builder request = BidRequest.newBuilder();
		request.setBid(tanx.getBid())
		.setChannel(Constant.CHANNEL_TANX)
		.setAdxId(tanx.getTid())
		.setIp(tanx.getIp())
		.setUserAgent(tanx.getUserAgent())
		.setUrl(tanx.getUrl())
		.setCategory(tanx.getCategory())
		.addAllExcludeAdCategory(tanx.getExcludedAdCategoryList());
		if(tanx.getAdzinfoCount() >0){//包含广告位信息,目前只一个请求中只含有一个广告位信息。
			TanxBidding.BidRequest.AdzInfoOrBuilder adz = tanx.getAdzinfoOrBuilder(0);
			BidRequest.AdSlot.Builder adSlot = BidRequest.AdSlot.newBuilder();
			adSlot.setSid(adz.getPid())
			.setSize(adz.getSize())
			.addAllViewType(adz.getViewTypeList())
			.addAllExcludeCreativeType(adz.getExcludedFilterList())
			.setMinimumPrice(adz.getMinCpmPrice());
			request.addAdslot(adSlot);
		}
		return request;
	}

	@Override
	public Object transResponse(Bidding.BidResponseOrBuilder response) {
		TanxBidding.BidResponse.Builder tanx = TanxBidding.BidResponse.newBuilder();
		tanx.setBid(response.getBid())
		.setVersion(3);
		if(response.getAdCount() >0){
			TanxBidding.BidResponse.Ads.Builder ads = TanxBidding.BidResponse.Ads.newBuilder();
			Bidding.BidResponse.AdOrBuilder ad = response.getAdOrBuilder(0);
			ads.setAdzinfoId(0)
			.setMaxCpmPrice(ad.getBidPrice())
			.setAdBidCountIdx(0)
			.addCreativeType(ad.getCreativeType())
			.setCreativeId(ad.getCreativeId()+"")
			.addDestinationUrl(ad.getLandingPage())
			.addClickThroughUrl(ad.getClickUrl(0));
			ads.setHtmlSnippet(HtmlSnippetEngine.macroInstead(ad.getHtmlSnippet(), response, Constant.CHANNEL_TANX));
			tanx.addAds(0, ads);
		}
		return tanx;
	}

}
