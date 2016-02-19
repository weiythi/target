package com.bw.target.adaptor.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.common.util.StringUtil;
import org.common.util.file.ConfigProperties;

import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.model.Bidding.BidResponse;

/**
 * 
 * <br>Date:2015年9月16日 下午2:05:59
 * @author pujie
 */
public class HtmlSnippetEngine {

	final public static String MONITOR_WIN_NOTICE_KEY = "monitor.win.notice";
	final public static String MONITOR_CLICK_DOMAIN_KEY = "monitor.click.domain";
	final public static String CLICK_URL_KEY = "%%CLICK_URL%%";
	final public static String WIN_NOTICE_KEY = "%%WIN_NOTICE%%";
	final public static String HY_COOKIE_MAPPING_KEY = "hy.cookie.mapping.url";
	/**
	 * TANX点击宏
	 */
	final public static String CLICK_URL_TANX_KEY = "%%CLICK_URL_PRE_UNENC%%";
	/**
	 * 海云价格宏{HYPRICE}
	 */
	final public static String PRICE_MACRO_HY = "{HYPRICE}";
	public static String hyCookieMappingUrl = "";
	//曝光监测域
	public static String monitorWinNotice="http://localhost:8080/tc/bidres?p=";
	//点击监测域
	public static String monitorClickDomain="http://localhost:8080/tc/click?h=";
	
	static{
		if(StringUtil.isNotBlank(ConfigProperties.getProperty(MONITOR_WIN_NOTICE_KEY))){
			monitorWinNotice = ConfigProperties.getProperty(MONITOR_WIN_NOTICE_KEY);
		}
		if(StringUtil.isNotBlank(ConfigProperties.getProperty(MONITOR_CLICK_DOMAIN_KEY))){
			monitorClickDomain = ConfigProperties.getProperty(MONITOR_CLICK_DOMAIN_KEY);
		}
		if(StringUtil.isNotBlank(ConfigProperties.getProperty(HY_COOKIE_MAPPING_KEY))){
			hyCookieMappingUrl = ConfigProperties.getProperty(HY_COOKIE_MAPPING_KEY);
		}
	}
	/**
	 * <曝光><点击>地址后缀的<参数>
	 * <br>Date:2015年9月16日 下午2:16:56
	 * @author pujie
	 * @param response
	 * @param channel
	 * @param 1:empress  2：click
	 */
	public static String getParameters(Bidding.BidResponseOrBuilder response,Integer channel,Integer type){
		StringBuilder parameter = new StringBuilder();
		BidResponse.AdOrBuilder ad = response.getAdOrBuilder(0);
		if(type == 1){
			if(Constant.CHANNEL_HY == channel){ //海云价格宏
				parameter.append(PRICE_MACRO_HY);
			}
			parameter.append("&xid=").append(response.getAdxId());
			if(response.hasImpressFrequency()){
				parameter.append("&if=1");
			}
		}else if(type ==2){
			parameter.append("&cu=").append(response.getClickCrowdId());
		}
		parameter.append("&cn=").append(channel)
		.append("&bid=").append(response.getBid())
		.append("&sid=").append(ad.getSid())
		.append("&c=").append(response.getCustId())
		.append("&pid=").append(ad.getCampaignId())
		.append("&gid=").append(ad.getGroupId())
		.append("&cid=").append(ad.getCreativeId());
		return parameter.toString();
	}
	/**
	 * 竞价成功通知地址<曝光监测地址>
	 * <br>Date:2015年11月19日 下午6:18:31
	 * @author pujie
	 * @param response
	 * @param channel
	 * @return win_notice_url
	 */
	public static String generateWinNoticeUrl(Bidding.BidResponseOrBuilder response,Integer channel){
		return monitorWinNotice + getParameters(response, channel,1);
	}
	/**
	 * 生成带内部监测的点击地址。
	 * <br>Date:2015年11月18日 下午7:00:04
	 * @author pujie
	 * @param landingPage
	 * @param response
	 * @param channel
	 * @return click_url
	 */
	public static String generateClickUrl(String landingPage,Bidding.BidResponseOrBuilder response,Integer channel){
		String clickUrl = "";
		try {
			clickUrl = monitorClickDomain + URLEncoder.encode(landingPage, Constant.SYS_ENCODING) + getParameters(response, channel,2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return clickUrl;
	}
	/**
	 * 
	 * <br>Date:2015年9月16日 下午2:40:15
	 * @author pujie
	 * @param htmlSnippet
	 * @param response
	 * @param channel
	 */
	public static String macroInstead(String htmlSnippet,Bidding.BidResponseOrBuilder response,Integer channel) {
		String clickUrl ="";
		String winNotice = "";
		try {
			BidResponse.AdOrBuilder ad = response.getAdOrBuilder(0);
			clickUrl = CLICK_URL_TANX_KEY+ URLEncoder.encode(monitorClickDomain+ad.getClickUrl(0),Constant.SYS_ENCODING) + getParameters(response, channel,2);
			winNotice = monitorWinNotice + getParameters(response, channel,1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return htmlSnippet.replace(CLICK_URL_KEY, clickUrl).replace(WIN_NOTICE_KEY, winNotice);
	}
	
}
