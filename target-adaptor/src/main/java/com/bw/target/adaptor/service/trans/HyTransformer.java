package com.bw.target.adaptor.service.trans;

import org.common.util.StringUtil;

import com.bw.target.adaptor.model.HyBidding;
import com.bw.target.adaptor.model.HyBidding.BidRequest.Imp;
import com.bw.target.adaptor.model.HyBidding.BidResponse.Seatbid;
import com.bw.target.adaptor.service.Transformer;
import com.bw.target.adaptor.util.Constant;
import com.bw.target.adaptor.util.HtmlSnippetEngine;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.model.Bidding.BidRequest;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;
import com.bw.target.bidder.model.Bidding.BidResponseOrBuilder;
/**
 * 海云request、response转换器。
 * <br>Date:2015年11月18日 上午11:12:35
 * @author pujie
 */
public class HyTransformer implements Transformer {

	@Override
	public <T> BidRequestOrBuilder tansRequest(T bidRequest) {
		HyBidding.BidRequest hyReq = (HyBidding.BidRequest)bidRequest;
		BidRequest.Builder innerReq = BidRequest.newBuilder();
		innerReq.setBid(hyReq.getId())      //竞价唯一标识
		.setChannel(Constant.CHANNEL_HY);   //渠道代码
		if(hyReq.getUser() != null && StringUtil.isNotBlank(hyReq.getUser().getId())){
			innerReq.setAdxId(hyReq.getUser().getId()); //百度用户标识
		}
		if(hyReq.getDevice() !=null){
			innerReq.setIp(hyReq.getDevice().getIp()) //IP地址
			.setUserAgent(hyReq.getDevice().getUa()); //user-Agent
		}
		if(hyReq.getSite() != null){
			innerReq.setUrl(hyReq.getSite().getPage());            //广告位所在的url
		}

		if(hyReq.getImpSize() > 0 && hyReq.getImp()[0].getBanner() != null){ //图文广告banner
			Imp imp = hyReq.getImp()[0];
			BidRequest.AdSlot.Builder innerAdSlot =  BidRequest.AdSlot.newBuilder();
			innerAdSlot.setImpid(imp.getId());
			innerAdSlot.setSid(imp.getTagid()+"");  //广告位唯一标识
			innerAdSlot.setSize(imp.getBanner().getW() +"x"+ imp.getBanner().getH());// 尺寸
			innerAdSlot.setMinimumPrice(imp.getBidfloor());//底价
			innerReq.addAdslot(innerAdSlot);
		}		
		return innerReq;
	}

	@Override
	public Object transResponse(BidResponseOrBuilder response) {
		HyBidding.BidResponse hyRes = null;
		if(response.getAdCount()>0){
			hyRes = HyBidding.BidResponse.create();
			Bidding.BidResponse.AdOrBuilder innerAd = response.getAdOrBuilder(0);
			hyRes.setId(response.getBid());
			hyRes.setBidid(response.getBid());
			Seatbid seatbid = Seatbid.create();
			Seatbid.Bid bid = Seatbid.Bid.create();
			bid.setId(response.getBid());  
			bid.setImpid(innerAd.getImpid());  //曝光ID，对应request的曝光ID
			bid.setPrice(innerAd.getBidPrice());  //出价价格
			bid.setAdm(innerAd.getCreativeUrl());   //广告素材地址
			bid.setClickm(innerAd.getClickUrl(0)); //点击目标url（到达页面）
			bid.setNurl(HtmlSnippetEngine.generateWinNoticeUrl(response, Constant.CHANNEL_HY)); //win_notice_url
			bid.addPvm(HtmlSnippetEngine.hyCookieMappingUrl); //海云cookiemapping地址
			bid.addPvm(innerAd.getThirdMonitor());//第三方曝光监控
			bid.addCvm(HtmlSnippetEngine.generateClickUrl(innerAd.getLandingPage(), response,Constant.CHANNEL_HY)); //第三方点击监测
			seatbid.setBid(new Seatbid.Bid[]{bid});
			hyRes.setSeatbid(new Seatbid[]{seatbid});
		}
		
		return hyRes;
	}

}
