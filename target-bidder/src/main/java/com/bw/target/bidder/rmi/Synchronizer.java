package com.bw.target.bidder.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

import com.bw.target.bidder.model.AdCampaign;
import com.bw.target.bidder.model.AdCreative;
import com.bw.target.bidder.model.AdGroup;
/**
 * 广告信息同步器
 * @author pujie
 * created by 2015/08/13
 */
public interface Synchronizer extends Remote {

	/**
	 * 心跳请求，接通则返回true。
	 * <br>Date:2015年10月20日 下午4:10:52
	 * @author pujie
	 * @throws RemoteException
	 */
	public boolean isAlive() throws RemoteException;
	/**
	 * 投放引擎是否开启。
	 * <br>Date:2015年9月7日 下午12:01:33
	 * @author pujie
	 */
	public boolean isOn()throws RemoteException;
	/**
	 * 关闭投放引擎
	 * <br>Date:2015年9月7日 上午11:44:45
	 * @author pujie
	 */
	public void turnOff()throws RemoteException;
	/**
	 * 打开投放引擎
	 * <br>Date:2015年9月7日 上午11:44:22
	 * @author pujie
	 */
	public void turnOn()throws RemoteException;
	/**
	 * 清空投放引擎
	 * <br>Date:2015年9月7日 上午11:50:38
	 * @author pujie
	 */
	public void clear()throws RemoteException;
	/**
	 * 同步campaign信息。保证campaign的完整性和上线状态。<br>
	 * 如果campaignId已存在，则进行替换。
	 * <br>Date:2015年9月6日 下午7:01:54
	 * @author pujie
	 * @param campaign
	 * @throws RemoteException
	 */
	public void addCampaign(AdCampaign campaign) throws RemoteException;
	
	/**
	 * 批量同步campaign信息。保证所有campaign的完整性和处于上线状态<br>
	 * 以campaignID-campaign的键值对。
	 * <br>Date:2015年9月6日 下午7:00:06
	 * @author pujie
	 * @param campaigns
	 * @throws RemoteException
	 */
	public void batchAddCampaign(Map<Integer, AdCampaign> campaigns) throws RemoteException;
	/**
	 * 向指定campaign同步group。需要保证group的完整性。
	 * <br>Date:2015年9月6日 下午6:59:54
	 * @author pujie
	 * @param campaignId
	 * @param group
	 * @throws RemoteException
	 */
	public void addGroup(int campaignId,AdGroup group) throws RemoteException;
	/**
	 * 向指定campaign批量同步group。需要保证group的完整性。
	 * <br>Date:2015年9月6日 下午7:05:14
	 * @author pujie
	 * @param campaignId
	 * @param group
	 * @throws RemoteException
	 */
	public void batchAddGroup(int campaignId,Set<AdGroup> group) throws RemoteException;
	/**
	 * 向指定group同步creative。需要保证其是审核通过状态。
	 * <br>Date:2015年9月6日 下午7:09:44
	 * @author pujie
	 * @param groupId
	 * @param creative
	 * @throws RemoteException
	 */
	public void addCreative(int groupId,AdCreative creative) throws RemoteException;
	
	/**
	 * 获取服务中 所有活动信息。
	 * <br>Date:2015年10月20日 下午4:14:06
	 * @author pujie
	 * @return Set<AdCampaign>
	 * @throws RemoteException
	 */
	public Map<Integer,AdCampaign> getAllCampaigns() throws RemoteException;
}
