package com.bw.target.adaptor.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.common.util.JsonUtil;
import org.common.util.StringUtil;

import com.bw.target.adaptor.model.HyBidding;
import com.bw.target.adaptor.service.BidLogger;
import com.bw.target.adaptor.service.Transformer;
import com.bw.target.adaptor.service.log.HyBidLogger;
import com.bw.target.adaptor.service.trans.HyTransformer;
import com.bw.target.adaptor.util.Constant;
import com.bw.target.bidder.model.Bidding;
/**
 * 海云adx竞价接口
 * <br>Date:2015年11月19日 下午2:59:51
 * @author pujie
 */
public class HyController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(HyController.class);

	@Override
	protected void excute(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"))){		
			StringBuilder requestContext = new StringBuilder();
			String line = reader.readLine();
			while(StringUtil.isNotBlank(line)){
				requestContext.append(line);
				line = reader.readLine();
			}
			HyBidding.BidRequest hyReq = JsonUtil.jsonToBean(requestContext.toString(), HyBidding.BidRequest.class);
			super.bidLogger.logRequest(hyReq, Constant.CHANNEL_HY);//记录竞价请求日志
			Bidding.BidRequestOrBuilder innerReq = super.transformer.tansRequest(hyReq); //请求转换
			Bidding.BidResponseOrBuilder innerRes = super.bidder.bidding(innerReq);  // 竞价服务
			super.bidLogger.logResponse(innerRes, Constant.CHANNEL_HY); //竞价返回日志
			HyBidding.BidResponse hyRes = (HyBidding.BidResponse)super.transformer.transResponse(innerRes);
			if(null == hyRes){ //不出价，把http状态设置为204
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}else{
				String result = JsonUtil.beanToJson(hyRes);
				response.getOutputStream().write(result.getBytes());
			}
		} catch (Exception e) {
			logger.error("海云竞价异常：", e);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} 
	}

	@Override
	protected Transformer initTransformer() {
		return new HyTransformer();
	}

	@Override
	protected BidLogger initBidLogger() {
		// TODO Auto-generated method stub
		return new HyBidLogger();
	}

}
