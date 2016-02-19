package com.bw.target.adaptor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bw.target.adaptor.listener.SystemContext;
import com.bw.target.adaptor.service.BidLogger;
import com.bw.target.adaptor.service.Transformer;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.service.BiddingService;
import com.google.protobuf.MessageOrBuilder;

public abstract class BaseController extends HttpServlet {

	private static final long serialVersionUID = 5017921005635098106L;
	
	protected BidLogger bidLogger;
	protected Transformer transformer;
	protected BiddingService bidder = SystemContext.bidder;
	/**
	 * 处理所有客户端请求(GET/POST)
	 * @param request
	 * @param response
	 */
	protected abstract void excute(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 获取转换器实例
	 * @return
	 */
	protected abstract Transformer initTransformer();
	/**
	 * 获取日志记录器实例
	 * @return
	 */
	protected abstract BidLogger initBidLogger();
	
	@Override
	public void init() throws ServletException {
		super.init();
		bidLogger = initBidLogger();
		transformer = initTransformer();
	}


	/**
	 * 调取竞价服务
	 * @param requestBuilder 具体渠道的request
	 * @param channel
	 * @return
	 */
	protected Object getResponse(MessageOrBuilder requestBuilder, Integer channel){
		bidLogger.logRequest(requestBuilder, channel);
		Bidding.BidRequestOrBuilder bidRequest = transformer.tansRequest(requestBuilder);
		Bidding.BidResponseOrBuilder bidResponse = bidder.bidding(bidRequest);
		bidLogger.logResponse(bidResponse,channel);
		return transformer.transResponse(bidResponse);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		this.excute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.excute(request, response);
	}
}
