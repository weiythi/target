package com.bw.target.bidder.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.common.util.NumberUtil;
import org.common.util.StringUtil;

import com.bw.target.bidder.Constant;
/**
 * 推广组信息<br>
 * 所有setter方法，均返回AdGroup对象
 * <br>Date:2015年8月25日 下午5:06:51
 * @author pujie
 */
public class AdGroup implements Serializable {

	private static final long serialVersionUID = -8836363258465774194L;
	final public static int FIELDSTATUS0_TARGET=0x00000001;//是否有<定向条件>
	final public static int FIELDSTATUS0_CREATIVE=0x00000002;//创意列表

	private int fieldStatus0_;
	private AdCampaign parent;
	private int groupId;
	private int campaignId;
	private String channels;
	private int bidType;
	private int fixedPrice;
	private int frequencyType;//频次控制类型。
	private int frequencyDay;
	private volatile int status;
//	private String timeIntervals;
	private int[] timeIntervals; // 所有投放时段，计算规则为 (24 *a ) + b
	private Map<Integer,String> targeting = Collections.emptyMap();
	private Map<Integer,AdCreative> creatives = Collections.emptyMap();
	
	
	/**
	 * 获得所属AdCampaign对象
	 * <br>Date:2015年8月27日 下午3:19:26
	 * @author pujie
	 */
	public AdCampaign getParent() {
		return parent;
	}
	/**
	 * 设置所属的AdCampaign对象
	 * <br>Date:2015年8月27日 下午3:19:54
	 * @author pujie
	 * @param parent
	 */
	public AdGroup setParent(AdCampaign parent) {
		this.parent = parent;
		return this;
	}
	public int getGroupId() {
		return groupId;
	}
	public AdGroup setGroupId(int groupId) {
		this.groupId = groupId;
		return this;
	}
	
	public int getCampaignId() {
		return campaignId;
	}
	public AdGroup setCampaignId(int campaignId) {
		this.campaignId = campaignId;
		return this;
	}
	/**
	 * 获取策略所有投放渠道
	 * <br>Date:2015年8月26日 上午10:45:36
	 * @author pujie
	 */
	public String getChannels() {
		return channels;
	}
	public AdGroup setChannels(String channels) {
		this.channels = channels;
		return this;
	}
	/**
	 * 获取竞价策略。目前只支持固定出价
	 * <br>Date:2015年8月26日 上午10:48:58
	 * @author pujie
	 */
	public int getBidType() {
		return bidType;
	}
	public AdGroup setBidType(int bidType) {
		this.bidType = bidType;
		return this;
	}
	/**
	 * 获取出价<价格>
	 * <br>Date:2015年8月26日 上午11:07:31
	 * @author pujie
	 */
	public int getFixedPrice() {
		return fixedPrice;
	}
	public AdGroup setFixedPrice(int fixedPrice) {
		this.fixedPrice = fixedPrice;
		return this;
	}
	public int getFrequencyType() {
		return frequencyType;
	}
	public AdGroup setFrequencyType(int frequencyType) {
		this.frequencyType = frequencyType;
		return this;
	}
	public int getFrequencyDay() {
		return frequencyDay;
	}
	public AdGroup setFrequencyDay(int frequencyDay) {
		this.frequencyDay = frequencyDay;
		return this;
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
	 * 是否包含指定<时段值><br>
	 * <时段值>记录规则为：(24*a + b)，a表示DAY_OF_WEEK，b表示HOUR_OF_DAY。具体取值可参看Calendar中定义的常量。
	 * <br>Date:2015年9月7日 下午5:49:53
	 * @author pujie
	 * @param time
	 */
	public boolean containsTime(int time) {
		return Arrays.binarySearch(this.timeIntervals, time) > -1;
	}
	
	/**
	 * 多<时段值>以英文的逗号分隔，比如：1,2,3,4<br>
	 * <时段值>记录规则为：(24*a + b)，a表示DAY_OF_WEEK，b表示HOUR_OF_DAY。具体取值可参看Calendar中定义的常量。
	 * <br>Date:2015年9月7日 下午5:44:43
	 * @author pujie
	 * @param timeIntervals
	 */
	private void setTimeIntervals(String timeIntervals) {
		if(StringUtil.isBlank(timeIntervals)) return ;
		String[] times = timeIntervals.split(Constant.CONTEXT_SEPARATE);
		this.timeIntervals = new int[times.length];
		for(int i=0;i<times.length;i++){
			this.timeIntervals[i] = NumberUtil.toInt(times[i]);
		}
		Arrays.sort(this.timeIntervals);
	}
	/**
	 * 数组的形式返回所有投放时段。不限时段投放，该值可能为null。
	 * <br>Date:2015年9月22日 下午3:40:54
	 * @author pujie
	 */
	public int[] getTimeIntervals(){
		return this.timeIntervals;
	}
	/**
	 * 获取<定向条件>
	 * <br>Date:2015年8月26日 上午11:09:17
	 * @author pujie
	 */
	public Map<Integer, String> getTargeting() {
		return targeting;
	}
	/**
	 * 获取指定key的<定向条件>
	 * <br>Date:2015年9月14日 下午1:41:28
	 * @author pujie
	 * @param key
	 * @return value
	 */
	public String getTargetByKey(Integer key){
		return targeting.get(key);
	}
	/**
	 * 如果包含指定key的定向，则返回true
	 * <br>Date:2015年9月22日 下午3:35:29
	 * @author pujie
	 * @param key
	 */
	public boolean containsTargetKey(Integer key){
		return this.targeting.containsKey(key);
	}

	/**
	 * 批量设置<定向条件>
	 * <br>Date:2015年8月26日 上午11:10:17
	 * @author pujie
	 * @param targeting
	 */
	public AdGroup addAllTargeting(Map<Integer, String> targeting) {
		if(null != targeting && targeting.size()>0){
			ensureTargeting(targeting.size());
			for(Entry<Integer, String> entry:targeting.entrySet()){
				if(Constant.TARGET_TYPE_TIME == entry.getKey()){ //<投放时段>处理
					setTimeIntervals(entry.getValue());
				}
				this.targeting.put(entry.getKey(), entry.getValue()+",");
			}
		}
		return this;
	}
	/**
	 * 设置<定向条件>
	 * <br>Date:2015年8月26日 下午4:34:11
	 * @author pujie
	 * @param targetType
	 * @param targetValue
	 */
	public AdGroup addTargeting(Integer targetType,String targetValue){
		ensureTargeting(8);
		if(Constant.TARGET_TYPE_TIME == targetType){ //<投放时段>处理
			setTimeIntervals(targetValue);
		}
		this.targeting.put(targetType, targetValue+",");
		return this;
	}
	/**
	 * 是否包含<定向条件><br>
	 * Date:2015年8月26日 上午11:40:01
	 * @author pujie
	 * @return ture or false
	 */
	public boolean hasTargeting(){
		return ((fieldStatus0_ & FIELDSTATUS0_TARGET) == FIELDSTATUS0_TARGET);
	}
	/**
	 * 保证targeting集合已经完成初始化
	 * <br>Date:2015年8月26日 下午4:35:59
	 * @author pujie
	 * @param size
	 */
	private void ensureTargeting(int size){
		if(!hasTargeting()){
			fieldStatus0_ |= FIELDSTATUS0_TARGET;
			this.targeting = new HashMap<>(size);
		}
	}
	/**
	 * 包含的创意列表
	 * <br>Date:2015年8月26日 下午4:07:48
	 * @author pujie
	 */
	public Collection<AdCreative> getCreatives() {
		return creatives.values();
	}
	/**
	 * 返回指定Id的创意
	 * <br>Date:2015年9月15日 上午11:15:53
	 * @author pujie
	 * @param creativeId
	 */
	public AdCreative getCreativeById(Integer creativeId){
		return creatives.get(creativeId);
	}
	/**
	 * 批量添加创意
	 * <br>Date:2015年8月26日 下午4:44:04
	 * @author pujie
	 * @param creatives
	 */
	public AdGroup addAllCreative(Set<AdCreative> creatives) {
		if(null != creatives && creatives.size()>0){
			ensureCreative(creatives.size());
			for(AdCreative creative:creatives){
				creative.setParent(this);
				this.creatives.put(creative.getCreativeId(),creative);
			}
		}
		return this;
	}
	/**
	 * 添加创意
	 * <br>Date:2015年8月26日 下午4:44:32
	 * @author pujie
	 * @param creative
	 */
	public AdGroup addCreative(AdCreative creative) {
		ensureCreative(8);
		creative.setParent(this);
		this.creatives.put(creative.getCreativeId(), creative);
		return this;
	}
	/**
	 * 创意列表是否初始化
	 * <br>Date:2015年8月26日 下午4:44:51
	 * @author pujie
	 */
	public boolean hasCreative(){
		return ((fieldStatus0_ & FIELDSTATUS0_CREATIVE) == FIELDSTATUS0_CREATIVE);
	}
	/**
	 * 确保创意列表初始化完成
	 * <br>Date:2015年8月26日 下午4:46:35
	 * @author pujie
	 * @param size
	 */
	private void ensureCreative(int size){
		if(!hasCreative()){
			fieldStatus0_ |= FIELDSTATUS0_CREATIVE;
			this.creatives = new HashMap<>(size);
		}
	}
	
}
