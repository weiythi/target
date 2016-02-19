package com.bw.target.bidder.rmi.impl;

import java.rmi.RemoteException;

import com.bw.target.bidder.model.AdCampaign;
import com.bw.target.bidder.model.AdCreative;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.rmi.ValidateCheck;

public class ValidateCheckImpl implements ValidateCheck{

	@Override
	public boolean checkCampain(AdCampaign campaign) throws RemoteException{
		if(null != campaign && campaign.isOn()){
			for(AdGroup group:campaign.getGroups()){
				this.checkGroup(group);
			}
		}else{
			throw new RemoteException("活动ID："+ (campaign==null?"null":campaign.getCampaignId()) +" 处于<下线>状态");
		}
		return true;
	}

	@Override
	public boolean checkGroup(AdGroup group) throws RemoteException {
		if(null != group && group.isOn()){
			for(AdCreative creative:group.getCreatives()){
				this.checkCreative(creative);
			}
		}else{
			throw new RemoteException("推广组ID："+ (group==null?"null":group.getGroupId()) +" 处于<下线>状态");
		}
		return true;
	}

	@Override
	public boolean checkCreative(AdCreative creative) throws RemoteException {
		if(null == creative || !creative.isPassedChannals()){
			throw new RemoteException("创意ID："+ (creative==null?"null":creative.getCreativeId()) +" 没被渠道审核通过！");
		}
		return true;
	}

}
