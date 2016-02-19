package com.bw.target.bidder;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.commons.lang.NullArgumentException;
import org.common.util.StringUtil;
import org.common.util.file.ConfigProperties;
import org.common.util.ip.IP;
import org.framework.cache.redis.JedisClusterFactory;

import redis.clients.jedis.JedisCluster;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.rmi.impl.SynchronizerImpl;
import com.bw.target.bidder.service.BiddingService;
import com.bw.target.bidder.service.impl.BiddingServer;

/**
 * 竞价器初始化资源上下文。
 * Date: 2015年8月19日 下午3:12:45 &lt;br/&gt;
 * @author pujie
 */
public class BidderContext {

	
	private static BiddingService bidder;
	private static AdContainer container;
	private static Remote remote = null;
	public static String IP_DATA_PATH="ip.data.path";
	/**
	 * 初始化竞价器：同步服务 和 竞价服务。
	 * @return true of false
	 * @throws Exception 
	 */
	public static void initialize() throws Exception{
			initRedisCluster();
			container = new AdContainer();
			bidder = new BiddingServer(container);
			bidder.init();
			initRMIServer();
			initIPDatabase();
	}
	/**
	 * 资源回收
	 * <br>Date:2015年10月8日 上午11:24:16
	 * @author pujie
	 */
	public static void contextDestroyed(){
		try {
			UnicastRemoteObject.unexportObject(remote, true);
			remote = null;
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		}
	}
	/**
	 * rmi服务初始化
	 * <br>Date:2015年9月18日 下午3:46:05
	 * @author pujie
	 * @throws Exception 
	 */
	public static void initRMIServer() throws Exception{
		try {
			remote = LocateRegistry.createRegistry(Constant.SYNC_PORT);
			Naming.rebind(Constant.SYNC_SERVER_NAME, new SynchronizerImpl());
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
			throw new Exception("rmi初始化失败：",e);
		}
	}
	/**
	 * redis-cluster初始化
	 * <br>Date:2015年10月29日 下午6:04:32
	 * @author pujie
	 * @throws Exception
	 */
	public static void initRedisCluster() throws Exception{
		try {
			JedisClusterFactory.init(); //jedis cluster初始化
			JedisCluster jc = JedisClusterFactory.getcluster("dsp");
//			jc.exists("ping");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("redis-cluster初始化失败：",e);
		}
	}
	/**
	 * 初始化IP库
	 * <br>Date:2015年9月18日 下午3:25:38
	 * @author pujie
	 */
	public static void initIPDatabase(){
		if(StringUtil.isBlank(ConfigProperties.getProperty(IP_DATA_PATH))) throw new NullArgumentException("ip地址库不能为空,ip.data.path=");
		IP.load(ConfigProperties.getProperty(IP_DATA_PATH));
		IP.find("106.2.163.162");//测试。
	}
	/**
	 * 活动竞价器  单例。
	 * @return
	 */
	public static BiddingService getBidder(){
		return BidderContext.bidder;
	}
	/**
	 * 返回 系统单利的<广告容器>
	 * <br>Date:2015年9月1日 下午6:53:34
	 * @author pujie
	 * @return container
	 */
	public static AdContainer getContainer(){
		return BidderContext.container;
	}
}
