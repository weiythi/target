package com.bw.target.bidder.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bw.target.bidder.BidderContext;
import com.bw.target.bidder.model.AdCampaign;
import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdCreative;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.rmi.Synchronizer;
import com.bw.target.bidder.rmi.ValidateCheck;
/**
 * 广告信息同步器
 * <br>Date:2015年8月26日 下午5:09:37
 * @author pujie
 */
public class SynchronizerImpl extends UnicastRemoteObject implements Synchronizer {
	
	private static final long serialVersionUID = -5594381160689052442L;
	private static Logger logger = Logger.getLogger(SynchronizerImpl.class);
	
	private AdContainer container;
	private ValidateCheck validate;
	public static String RMI_INFO = "rmi调用：";
	
	public SynchronizerImpl() throws RemoteException {
		super();
		container = BidderContext.getContainer();
		validate = new ValidateCheckImpl();
	}
	
	@Override
	public boolean isAlive() throws RemoteException {
		logger.info(RMI_INFO+"isAlive()");
		return true;
	}
	
	@Override
	public boolean isOn() throws RemoteException{
		logger.info(RMI_INFO+"isOn()");
		return this.container.isOn();
	}
	
	@Override
	public void turnOff() throws RemoteException{
		logger.info(RMI_INFO+"turnOff()");
		this.container.turnOff();
	}

	@Override
	public void turnOn() throws RemoteException{
		logger.info(RMI_INFO+"turnOn()");
		this.container.turnOn();
	}

	@Override
	public void clear() throws RemoteException{
		logger.info(RMI_INFO+"clear()");
		this.container.clear();
	}
	
	@Override
	public void addCampaign(AdCampaign campaign) throws RemoteException {
		logger.info(RMI_INFO+"addCampaign()");
		try {
			validate.checkCampain(campaign);
			container.putCampaign(campaign);
		} catch (RemoteException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	}

	@Override
	public void batchAddCampaign(Map<Integer, AdCampaign> campaigns)throws RemoteException {
		logger.info(RMI_INFO+"batchAddCampaign()");
		try {
			for(AdCampaign campaign:campaigns.values()){
				validate.checkCampain(campaign);
			}		
			for(AdCampaign campaign:campaigns.values()){
				container.putCampaign(campaign);
			}
		} catch (RemoteException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}

	}

	@Override
	public void addGroup(int campaignId, AdGroup group)throws RemoteException {
		logger.info(RMI_INFO+"addGroup()");
		try {
			if( !container.containsCampaignId(campaignId))
				throw new RemoteException("未找到指定的campaignId："+campaignId);
			validate.checkGroup(group);
			container.getCampaign(campaignId).addGroup(group);
		} catch (RemoteException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	}

	@Override
	public void batchAddGroup(int campaignId, Set<AdGroup> groups) throws RemoteException {
		logger.info(RMI_INFO+"batchAddGroup()");
		try {
			if( !container.containsCampaignId(campaignId))
				throw new RemoteException("未找到指定的campaignId："+campaignId);
			for(AdGroup group:groups){
				validate.checkGroup(group);
			}
			container.getCampaign(campaignId).addAllGroup(groups);
		} catch (RemoteException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	}

	@Override
	public void addCreative(int groupId, AdCreative creative) throws RemoteException {
		logger.info(RMI_INFO+"addCreative()");
		try {
			if(!container.containsGroupId(groupId))
				throw new RemoteException("为找到指定的groupId："+groupId);
			validate.checkCreative(creative);
			container.getGroup(groupId).addCreative(creative);
		} catch (RemoteException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	}

	@Override
	public Map<Integer,AdCampaign> getAllCampaigns() throws RemoteException {
		logger.info(RMI_INFO+"getAllCampaigns()");
		return container.getCampaigns();
	}

}
