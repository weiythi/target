package com.bw.target.adaptor.controller;

import java.io.DataOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bw.target.adaptor.model.TanxBidding.BidRequest;
import com.bw.target.adaptor.model.TanxBidding.BidResponse;
import com.bw.target.adaptor.service.BidLogger;
import com.bw.target.adaptor.service.Transformer;
import com.bw.target.adaptor.service.log.TanxBidLogger;
import com.bw.target.adaptor.service.trans.TanxTransformer;
import com.bw.target.adaptor.util.Constant;

/**
 * Tanx竞价接口
 * @author pujie
 * created by 2015/8/7
 */
public class TanxController extends BaseController {

	private static final long serialVersionUID = -4638527937331121200L;
	
	@Override
	protected void excute(HttpServletRequest request,HttpServletResponse response) {
		try (DataOutputStream dOut = new DataOutputStream(response.getOutputStream())){
			BidRequest requestModel = BidRequest.parseFrom(request.getInputStream());
			BidResponse.Builder responseBuilder = (BidResponse.Builder)getResponse(requestModel.toBuilder(), Constant.CHANNEL_TANX);
			dOut.write(responseBuilder.build().toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Transformer initTransformer() {
		return new TanxTransformer();
	}

	@Override
	protected BidLogger initBidLogger() {
		return new TanxBidLogger();
	}
}
