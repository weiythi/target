package com.bw.target.adaptor.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bw.target.adaptor.model.BesBidding.BidRequest;
import com.bw.target.adaptor.model.BesBidding.BidResponse;
import com.bw.target.adaptor.service.BidLogger;
import com.bw.target.adaptor.service.Transformer;
import com.bw.target.adaptor.service.log.BesBidLogger;
import com.bw.target.adaptor.service.trans.BesTransformer;
import com.bw.target.adaptor.util.Constant;
/**
 * BES竞价接口
 * <br>Date:2015年10月20日 上午10:44:48
 * @author pujie
 */
public class BesController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(BesController.class);

	@Override
	protected void excute(HttpServletRequest request,HttpServletResponse response) {
		BidResponse.Builder besRes = null;
		try {
			BidRequest.Builder besReq = BidRequest.parseFrom(request.getInputStream()).toBuilder();
			besRes = (BidResponse.Builder)this.getResponse(besReq,Constant.CHANNEL_BES);
		} catch (IOException e) {
			logger.error("bes 竞价异常", e);
		}finally{
			try {
				response.setContentType("application/octet-stream");
				response.getOutputStream().write(besRes.build().toByteArray());
			} catch (IOException e) {
				logger.error("bes竞价返回时，IO异常！", e);
			}
		}
	}

	@Override
	protected Transformer initTransformer() {
		// TODO Auto-generated method stub
		return new BesTransformer();
	}

	@Override
	protected BidLogger initBidLogger() {
		// TODO Auto-generated method stub
		return new BesBidLogger();
	}

}
