package com.bw.target.bidder.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bw.target.bidder.Constant;
/**
 * 推广计划信息
 * <br>Date:2015年8月25日 下午1:39:38
 * @author pujie
 */
public class AdCampaign implements Serializable {

	private static final long serialVersionUID = -3936249245057983682L;
	final public static int FIELDSTATUS0_GROUP=0x00000001;//推广组列表
//	private Lock lock = new ReentrantLock(false);
	
	private int fieldStatus0_;
	private int campaignId;	
	private int custId;	
	private int clickCrowdId; //点击人群ID
	private  int budget;
	private  int dayBudget;
	private  int dayPacing;
	private Date startDate;
	private Date endDate;
	private volatile int status;
	private Set<AdGroup> groups = Collections.emptySet();
	
	/**
	 * campaign唯一标识
	 */
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	/**
	 * 客户ID
	 */
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	/**
	 * 活动预算。单位：分
	 */
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	/**
	 * 日预算
	 */
	public int getDayBudget() {
		return dayBudget;
	}
	public void setDayBudget(int dayBudget) {
		this.dayBudget = dayBudget;
	}
	/**
	 * 是否 消耗匀速投放
	 */
	public boolean isPacingSmooth() {
		return this.dayPacing == 0;
	}
	/**
	 * 投放速度控制：0：日预算均匀投放；1：尽速投放
	 * Date:2015年8月25日 下午5:41:39
	 * @param dayPacing
	 */
	public void setDayPacing(int dayPacing) {
		this.dayPacing = dayPacing;
	}
	
	public int getDayPacing() {
		return dayPacing;
	}
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 投放开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 投放结束时间
	 */
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getClickCrowdId() {
		return clickCrowdId;
	}
	/**
	 * 点击人群ID
	 */
	public void setClickCrowdId(int clickCrowdId) {
		this.clickCrowdId = clickCrowdId;
	}
	/**
	 * “上线” group
	 * Date:2015年8月26日 上午11:44:52
	 * @author pujie
	 * @return 0 or 1
	 */
	public boolean turnOn() {
		this.status = Constant.SWITCH_ON;
		return this.status == Constant.SWITCH_ON;
	}
	/**
	 * “下线”group
	 * <br>Date:2015年9月6日 下午6:48:59
	 * @author pujie
	 */
	public boolean turnOff() {
		this.status = Constant.SWITCH_OFF;
		return this.status == Constant.SWITCH_OFF;
	}
	/**
	 * 是否为上线状态。是返回true，否则返回false。
	 * <br>Date:2015年9月6日 下午3:21:44
	 * @author pujie
	 */
	public boolean isOn(){
		return this.status == Constant.SWITCH_ON;
	}
	/**
	 * 获得推广组信息
	 * <br>Date:2015年8月26日 下午4:51:54
	 * @author pujie
	 * @return
	 */
	public Set<AdGroup> getGroups() {
		return groups;
	}
	/**
	 * 批量添加推广组
	 * <br>Date:2015年8月26日 下午5:02:12
	 * @author pujie
	 * @param groups
	 */
	public AdCampaign addAllGroup(Set<AdGroup> groups){
		if(null != groups && groups.size()>0){
			ensureGroups(groups.size());
			for(AdGroup group:groups){
				group.setParent(this);
				this.groups.add(group);
			}
		}
		return this;
	}
	/**
	 * 添加推广组
	 * <br>Date:2015年8月26日 下午5:04:22
	 * @author pujie
	 * @param group
	 */
	public AdCampaign addGroup(AdGroup group){
		ensureGroups(8);
		group.setParent(this);
		this.groups.add(group);
		return this;
	}
	/**
	 * 推广组列表是否初始化
	 * <br>Date:2015年8月26日 下午5:05:24
	 * @author pujie
	 */
	public boolean hasGroup(){
		return ((fieldStatus0_ & FIELDSTATUS0_GROUP) == FIELDSTATUS0_GROUP);
	}
	/**
	 * 确保推广组列表初始化完成
	 * <br>Date:2015年8月26日 下午5:05:46
	 * @author pujie
	 * @param size
	 */
	private void ensureGroups(int size){
		if(!hasGroup()){
			fieldStatus0_ |= FIELDSTATUS0_GROUP;
			this.groups = new HashSet<>(size);
		}
	}
}
