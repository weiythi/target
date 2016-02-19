package com.bw.target.bidder.retrieval.matcher;

import org.common.util.StringUtil;
import org.common.util.ip.IP;

import com.bw.target.bidder.Constant;
import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.retrieval.Targeter;
/**
 * 按IP所属地域定向
 * <br>Date:2015年8月28日 下午2:30:56
 * @author pujie
 */
public class RegionTargeter implements Targeter {

	@Override
	public boolean doTarget(AdContainer container,AdGroup group,String targetResource) {

		return group.getTargetByKey(Constant.TARGET_TYPE_REGION).contains(targetResource); //模糊匹配
	}

	@Override
	public String generateResource(Bidding.BidRequestOrBuilder request) {
		String domain = "NULL";
		if(StringUtil.isNotBlank(request.getIp())){
			domain = IP.find(request.getIp())[2]; //匹配到地市
		}
		return domain;//用户所属地域
	}

}
