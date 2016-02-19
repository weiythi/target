package com.bw.target.bidder.retrieval.matcher;

import com.bw.target.bidder.Constant;
import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.retrieval.Targeter;
/**
 * 定向手段-<K媒体定向>
 * <br>Date:2015年9月14日 上午11:28:23
 * @author pujie
 */
public class MediaTargeter implements Targeter {

	@Override
	public boolean doTarget(AdContainer container, AdGroup group,String targetResource) {

		return group.getTargetByKey(Constant.TARGET_TYPE_MEDIA_TYPE).contains(targetResource+",");
	}

	@Override
	public String generateResource(Bidding.BidRequestOrBuilder request) {
		return request.getCategory()+"";
	}

}
