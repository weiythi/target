package com.bw.target.adaptor.service;

import com.bw.target.bidder.model.Bidding;

/**
 * 竞价请求request和response转换器。
 * Date: 2015年8月13日 下午5:59:56 &lt;br/&gt;
 * @author pujie
 */
public interface Transformer {

	/**
	 * 适配bidRequest为内部request。
	 * @param bidRequest
	 * @return Bidding.BidRequest
	 */
	public <T> Bidding.BidRequestOrBuilder tansRequest(T bidRequest);
	
	/**
	 * 适配内部response为相应渠道可识别的应答bidResponse
	 * @return bidResponse
	 */
	public Object transResponse(Bidding.BidResponseOrBuilder response);
}
