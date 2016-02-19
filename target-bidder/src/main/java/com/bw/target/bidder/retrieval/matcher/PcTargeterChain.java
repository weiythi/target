package com.bw.target.bidder.retrieval.matcher;

import java.util.HashMap;
import java.util.Map;

import org.common.util.StringUtil;

import com.bw.target.bidder.Constant;
import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding.BidRequestOrBuilder;
import com.bw.target.bidder.retrieval.Targeter;
import com.bw.target.bidder.retrieval.TargeterChain;
/**
 * 各种targeter的调度者
 * <br>Date:2015年9月15日 下午3:48:53
 * @author pujie
 */
public class PcTargeterChain implements TargeterChain {

	private static int TARGETER_NUM = 3;
	private Map<Integer, Targeter> targeters;
	private ThreadLocal<Map<Integer, String>> targetResources = new ThreadLocal<Map<Integer,String>>(){

		@Override
		protected Map<Integer, String> initialValue() {
			return new HashMap<Integer, String>(TARGETER_NUM);
		}
		
	};
	
	
	@Override
	public void init() {
		targeters = new HashMap<Integer, Targeter>(TARGETER_NUM);
		targeters.put(Constant.TARGET_TYPE_TIME, new TimeIntervalTargeter());
		targeters.put(Constant.TARGET_TYPE_MEDIA_TYPE, new MediaTargeter());
		targeters.put(Constant.TARGET_TYPE_REGION,new RegionTargeter());
		
	}

	@Override
	public void doTarget(BidRequestOrBuilder request, AdContainer container) {
		String resource;
		AdGroup group;
		targetResources.get().clear();
		for(Integer groupId:container.getSession().getGroupIds()){
			group = container.getGroup(groupId);
			for(Map.Entry<Integer, Targeter> targeter:targeters.entrySet()){
				if(group.getTargeting().containsKey(targeter.getKey())){
					resource = targetResources.get().get(targeter.getKey());
					if(StringUtil.isBlank(resource)){//第一次计算该<定向信息>
						resource = targeter.getValue().generateResource(request);
						targetResources.get().put(targeter.getKey(), resource);
					}
					if(!targeter.getValue().doTarget(container,group,resource)){
						container.getSession().remove(group.getGroupId());break;
					}
				}
			}
		}
		targetResources.get().clear();//清空此次请求的用户信息
	}

}
