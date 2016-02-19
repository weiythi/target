package com.bw.target.bidder.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 广告campaign容器。考虑多线程下的存取。
 * <br>Date:2015年8月27日 下午6:55:10
 * @author pujie
 */
public class AdContainer {

	private volatile int offAndOn = 1;//启停开关。默认为开启。1位开启，0 关闭
	
	private Lock lock = new ReentrantLock(false);
	private Map<Integer, AdCampaign> campaigns = new ConcurrentHashMap<>();
	private Map<Integer, AdGroup> groups = new ConcurrentHashMap<>();
	private ThreadLocal<Session> session = new ThreadLocal<Session>(){

		@Override
		protected Session initialValue() {
			return new Session();
		}
		
	};
	
	private void ensureGroup(AdCampaign campaign){
		for(AdGroup group:campaign.getGroups()){
			groups.put(group.getGroupId(), group);
		}
	}
	/**
	 * 
	 * <br>Date:2015年8月31日 上午10:46:03
	 * @author pujie
	 */
	public Session getSession(){
		Session s = session.get(); 
/*		if(null == s){
			s = new Session();
		}*/
		return s;
	}
	/**
	 * 使用后一定要对session做清理操作<br>
	 * <br>Date:2015年8月31日 上午11:10:17
	 * @author pujie
	 */
	public void clearSession(){
		session.get().clear();
	}
	/**
	 * 清空投放引擎
	 * <br>Date:2015年9月7日 上午11:49:36
	 * @author pujie
	 */
	public void clear(){
		lock.lock();
		this.campaigns.clear();
		this.groups.clear();
		lock.unlock();
	}
	/**
	 * 向容器中添加AdCampaign对象
	 * <br>Date:2015年8月28日 上午9:44:49
	 * @author pujie
	 * @param campaign
	 */
	public void putCampaign(AdCampaign campaign){
		lock.lock();
		ensureGroup(campaign);
		campaigns.put(campaign.getCampaignId(),campaign);
		lock.unlock();
	}
	/**
	 * 批量添加AdCampaign对象
	 * <br>Date:2015年8月28日 上午9:48:21
	 * @author pujie
	 * @param campaigns
	 */
	public void putAllCampaign(Map<Integer, AdCampaign> campaigns){
		lock.lock();
		for(AdCampaign campaign:campaigns.values()){
			ensureGroup(campaign);
		}
		this.campaigns.putAll(campaigns);
		lock.unlock();
	}
	/**
	 * 移除制定的campaignID对应的value值。
	 * <br>Date:2015年8月28日 上午9:51:45
	 * @author pujie
	 * @param campaignId
	 */
	public void removeCampaign(Integer campaignId){
		lock.lock();
		AdCampaign c = campaigns.remove(campaignId);
		if(c == null)return;
		for(AdGroup group:c.getGroups()){
			groups.remove(group.getGroupId());
		}
		lock.unlock();
	}
	/**
	 * 返回指定campaignId对应的value。
	 * <br>Date:2015年8月28日 上午9:53:44
	 * @author pujie
	 * @param campaignId
	 * @return campaign
	 */
	public AdCampaign getCampaign(Integer campaignId){
		return this.campaigns.get(campaignId);
	}
	/**
	 * 返回系统所有 campaigns
	 * <br>Date:2015年10月20日 下午4:20:07
	 * @author pujie
	 * @return Map<Integer, AdCampaign>
	 */
	public Map<Integer, AdCampaign> getCampaigns(){
		return this.campaigns;
	}
	/**
	 * 返回指定的groupId对应的group对象
	 * <br>Date:2015年8月28日 下午2:36:07
	 * @author pujie
	 * @param groupId
	 * @return group
	 */
	public AdGroup getGroup(Integer groupId){
		return this.groups.get(groupId);
	}
	/**
	 * 获取所有AdGroup信息
	 * <br>Date:2015年8月28日 上午11:47:26
	 * @author pujie
	 * @return groups
	 */
	public Collection<AdGroup> groupValues(){
		return groups.values();
	}
	/**
	 * 投放开关是否打开。
	 * <br>Date:2015年9月2日 上午10:02:02
	 * @author pujie
	 * @return on-true off-false
	 */
	public boolean isOn(){
		return offAndOn == 1;
	}
	/**
	 * 开启投放
	 * <br>Date:2015年9月2日 上午10:22:07
	 * @author pujie
	 */
	public void turnOn(){
		this.offAndOn = 1;
	}
	/**
	 * 关闭投放
	 * <br>Date:2015年9月2日 上午10:22:36
	 * @author pujie
	 */
	public void turnOff(){
		this.offAndOn = 0;
	}
	/**
	 * 容器中是否包含指定的campaignId。
	 * <br>Date:2015年9月7日 上午10:15:12
	 * @author pujie
	 * @param campaignId
	 */
	public boolean containsCampaignId(Integer campaignId){
		return this.campaigns.containsKey(campaignId);
	}
	/**
	 * 容器中是否包含指定的groupId
	 * <br>Date:2015年9月7日 上午11:00:39
	 * @author pujie
	 * @param groupId
	 */
	public boolean containsGroupId(Integer groupId){
		return this.groups.containsKey(groupId);
	}
}
