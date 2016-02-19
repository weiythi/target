package com.bw.target.adaptor.service.trans;

import com.bw.target.adaptor.model.BesBidding;
import com.bw.target.adaptor.service.Transformer;
import com.bw.target.adaptor.util.ConfigureUtil;
import com.bw.target.adaptor.util.Constant;
import com.bw.target.adaptor.util.HtmlSnippetEngine;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.model.Bidding.BidRequest;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;
import com.bw.target.bidder.model.Bidding.BidResponseOrBuilder;
/**
 * 百度request、response转换器。
 * <br>Date:2015年10月20日 上午11:17:43
 * @author pujie
 */
public class BesTransformer implements Transformer {

	@Override
	public <T> BidRequestOrBuilder tansRequest(T bidRequest) {
		BesBidding.BidRequest.Builder besReq = (BesBidding.BidRequest.Builder)bidRequest;
		BidRequest.Builder innerReq = BidRequest.newBuilder();
		innerReq.setBid(besReq.getId())      //竞价唯一标识
		.setChannel(Constant.CHANNEL_BES);   //渠道代码
		if(besReq.getBaiduIdListCount()>0){
			innerReq.setAdxId(besReq.getBaiduIdListBuilder(0).getBaiduUserId()); //百度用户标识
		}
		innerReq.setIp(besReq.getIp())     //IP地址
		.setUserAgent(besReq.getUserAgent()) //userAgent
		.setUrl(besReq.getUrl())             //广告位所在的url
		.setCategory(besReq.getSiteCategory())//网站分类
		.addAllExcludeAdCategory(besReq.getExcludedProductCategoryList());//媒体禁止的行业类目
		if(besReq.getAdslotCount() > 0){
			BesBidding.BidRequest.AdSlotOrBuilder besAdSlot = besReq.getAdslotOrBuilder(0);
			BidRequest.AdSlot.Builder innerAdSlot =  BidRequest.AdSlot.newBuilder();
			innerAdSlot.setSid(besAdSlot.getAdBlockKey()+"")  //广告位唯一标识
			.setSequenceId(besAdSlot.getSequenceId())
			.setSize(besAdSlot.getWidth() +"x"+ besAdSlot.getHeight())// 尺寸
			.addViewType(besAdSlot.getAdslotType())//展示类型
			.addAllExcludeCreativeType(ConfigureUtil.reversCreativeTypeBes(besAdSlot.getCreativeTypeList()) )//媒体不允许的创意类型
			.setMinimumPrice(besAdSlot.getMinimumCpm());//底价
			innerReq.addAdslot(innerAdSlot);
		}		
		return innerReq;
	}

	@Override
	public Object transResponse(BidResponseOrBuilder response) {
		BesBidding.BidResponse.Builder besRes = BesBidding.BidResponse.newBuilder();
		besRes.setId(response.getBid());
		besRes.setProcessingTimeMs((int)response.getProcessingTime());
		if(response.getAdCount() > 0){
			Bidding.BidResponse.AdOrBuilder innerAd = response.getAdOrBuilder(0);
			BesBidding.BidResponse.Ad.Builder besAd = BesBidding.BidResponse.Ad.newBuilder();
//			String[] size = innerAd.getSize().split("x");
			besAd.setMaxCpm(innerAd.getBidPrice())      //最高出价
			.setSequenceId(innerAd.getSequenceId())     //当前页面广告位顺序id，同一页面从1开始
			.setCreativeId(innerAd.getCreativeId())     //创意ID
//			.setLandingPage(innerAd.getLandingPage())   //落地页面地址。静态创意可不用填写。
//			.addAllTargetUrl(innerAd.getClickUrlList()) //所有点击地址。静态创意可不用填写。
//			.addMonitorUrls(innerAd.getThirdMonitor())  //监控地址。静态创意可不用填写。
//			.setWidth(NumberUtil.toInt(size[0]))        //创意宽。静态创意可不用填写。
//			.setHeight(NumberUtil.toInt(size[1]))       //创意高。静态创意可不用填写。
//			.setType(ConfigureUtil.transCreativeTypeBes(innerAd.getCreativeType())) //创意类型。静态创意可不用填写。
//			.setCategory(innerAd.getCategory())   //创业所属行业ID。静态创意可不用填写。
			.setIsCookieMatching(true)
			.setExtdata("1"+HtmlSnippetEngine.getParameters(response, Constant.CHANNEL_BES, 1));
			besRes.addAd(besAd);
		}
		return besRes;
	}

}
