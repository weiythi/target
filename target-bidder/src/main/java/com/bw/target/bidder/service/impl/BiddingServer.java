package com.bw.target.bidder.service.impl;

import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.model.Bidding.BidResponse;
import com.bw.target.bidder.rank.Ranking;
import com.bw.target.bidder.rank.impl.DefaultRanking;
import com.bw.target.bidder.service.BiddingService;
import com.bw.target.bidder.service.RetrievalService;
import com.bw.target.bidder.ym.ComposeResponse;
import com.bw.target.bidder.ym.impl.ComposeResponseImpl;
/**
 * 竞价接口默认实现类
 * Date: 2015年8月18日 下午2:44:48 &lt;br/&gt;
 * @author pujie
 */
public class BiddingServer implements BiddingService {
	
	private static Logger LOG = Logger.getLogger(BiddingServer.class);
	private RetrievalService retrieval;
	private Ranking ranking;
	private ComposeResponse composeResponse;
	private AdContainer container;
	
	public BiddingServer(AdContainer container){
		this.container = container;
	}
	@Override
	public void init() {
		retrieval = new RetrievalServiceImpl();
		ranking = new DefaultRanking();
		composeResponse = new ComposeResponseImpl();
		retrieval.init();
		ranking.init();
	}
	
	@Override
	public Bidding.BidResponseOrBuilder bidding(Bidding.BidRequestOrBuilder request) {
		BidResponse.Builder response = BidResponse.newBuilder();
		response.setBid(request.getBid());
		long startTimeMillis = System.currentTimeMillis();
		try {
			if(container.isOn() && request.getAdslotCount()>0){ //开启状态 && 请求中有广告位信息
				retrieval.retrieval(container, request);
				Entry<Integer, Integer> selectedGroup = ranking.ranking(container,request);
				if( null != selectedGroup ){
					composeResponse.fillResponse(container, selectedGroup ,response,request);
				}
				container.clearSession(); //清空session
			}
		} catch (Exception e) {
			LOG.error("bidding exception：", e);
		}
		response.setProcessingTime( System.currentTimeMillis() - startTimeMillis );
		return response;
	}



}
