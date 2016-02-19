package com.bw.target.bidder.model;

import java.io.Serializable;

import org.common.util.StringUtil;
/**
 * 广告创意信息<br>
 * 所有setter方法，均返回AdCreative对象
 * <br>Date:2015年8月26日 上午11:49:31
 * @author pujie
 */
public class AdCreative implements Serializable {

	private static final long serialVersionUID = 2657487617790762648L;
	
	private AdGroup parent;
	private int creativeId;
	private int groupId;
	private int custId;
	private int creativeType;
	private String size;
	private String creativeUrl;
	private String landingPage;
	private String clickUrl;
	private String impreUrl;
	private String htmlSnippet;
	private String passedChannels="";//所有审核过的渠道
	/**
	 * 获得所属的AdGroup对象
	 * <br>Date:2015年8月27日 下午3:25:01
	 * @author pujie
	 */
	public AdGroup getParent() {
		return parent;
	}
	/**
	 * 设置所属的AdGroup对象
	 * <br>Date:2015年8月27日 下午3:25:41
	 * @author pujie
	 * @param parent
	 */
	public void setParent(AdGroup parent) {
		this.parent = parent;
	}
	public int getCreativeId() {
		return creativeId;
	}
	public AdCreative setCreativeId(int creativeId) {
		this.creativeId = creativeId;
		return this;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
	 * 客户ID
	 * <br>Date:2015年8月26日 下午2:57:45
	 * @author pujie
	 */
	public int getCustId() {
		return custId;
	}
	public AdCreative setCustId(int custId) {
		this.custId = custId;
		return this;
	}
	/**
	 * 素材尺寸。格式：宽x高
	 * <br>Date:2015年8月26日 下午3:18:55
	 * @author pujie
	 */
	public String getSize() {
		return size;
	}
	public AdCreative setSize(String size) {
		this.size = size;
		return this;
	}
	/**
	 * 创意类型。<br>
	 * 1-文字，2-图片，3-flash，4-视频
	 * <br>Date:2015年8月26日 下午3:17:37
	 * @author pujie
	 */
	public int getCreativeType() {
		return creativeType;
	}
	public AdCreative setCreativeType(int creativeType) {
		this.creativeType = creativeType;
		return this;
	}
	/**
	 * 创意地址
	 * <br>Date:2015年8月26日 下午3:19:32
	 * @author pujie
	 */
	public String getCreativeUrl() {
		return creativeUrl;
	}
	public AdCreative setCreativeUrl(String creativeUrl) {
		this.creativeUrl = creativeUrl;
		return this;
	}
	/**
	 * 落地页面
	 * <br>Date:2015年8月26日 下午3:19:55
	 * @author pujie
	 */
	public String getLandingPage() {
		return landingPage;
	}
	public AdCreative setLandingPage(String landingPage) {
		this.landingPage = landingPage;
		return this;
	}
	/**
	 * 点击监测地址
	 * <br>Date:2015年8月26日 下午3:20:16
	 * @author pujie
	 */
	public String getClickUrl() {
		return clickUrl;
	}
	public AdCreative setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
		return this;
	}
	/**
	 * 曝光监测地址
	 * <br>Date:2015年8月26日 下午3:21:50
	 * @author pujie
	 */
	public String getImpreUrl() {
		return impreUrl;
	}
	public AdCreative setImpreUrl(String impreUrl) {
		this.impreUrl = impreUrl;
		return this;
	}
	/**
	 * 创意展示代码
	 * <br>Date:2015年8月26日 下午3:21:58
	 * @author pujie
	 */
	public String getHtmlSnippet() {
		return htmlSnippet;
	}
	public AdCreative setHtmlSnippet(String htmlSnippet) {
		this.htmlSnippet = htmlSnippet;
		return this;
	}
	/**
	 * 多渠道之间请用英文输入法的逗号隔开。比如：1,2
	 * <br>Date:2015年9月7日 上午11:31:11
	 * @author pujie
	 * @param passedChannels
	 */
	public void addPassedChannels(String passedChannels) {
		this.passedChannels = this.passedChannels + passedChannels+",";
	}
	/**
	 * <审核通过的渠道>是否为空。不为null，返回true，否则返回false。
	 * <br>Date:2015年10月29日 下午3:51:19
	 * @author pujie
	 * @return
	 */
	public boolean isPassedChannals(){
		return StringUtil.isNotBlank(this.passedChannels);
	}
	/**
	 * 
	 * <br>Date:2015年9月7日 上午11:37:19
	 * @author pujie
	 * @param channel
	 */
	public boolean containsChannel(Integer channel){
		return this.passedChannels.indexOf(channel+",") >-1;
	}
}
