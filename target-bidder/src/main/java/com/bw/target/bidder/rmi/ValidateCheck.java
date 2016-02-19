package com.bw.target.bidder.rmi;

import java.rmi.RemoteException;

import com.bw.target.bidder.model.AdCampaign;
import com.bw.target.bidder.model.AdCreative;
import com.bw.target.bidder.model.AdGroup;

/**
 * <活动信息>合法性检查
 * <br>Date:2015年10月29日 下午3:09:10
 * @author pujie
 */
public interface ValidateCheck {

	/**
	 * <活动>合法性检查
	 * <br>Date:2015年10月29日 下午3:10:45
	 * @author pujie
	 * @exception 合法性检查失败
	 */
	public boolean checkCampain(AdCampaign campaign) throws RemoteException ;
	/**
	 * <推广组> 合法性检查
	 * <br>Date:2015年10月29日 下午3:12:58
	 * @author pujie
	 * @return true or false
	 */
	public boolean checkGroup(AdGroup group) throws RemoteException ;
	
	/**
	 * <创意>合法性检查
	 * <br>Date:2015年10月29日 下午3:13:44
	 * @author pujie
	 * @return true or false
	 */
	public boolean checkCreative(AdCreative creative) throws RemoteException ;
}
