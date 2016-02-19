package com.bw.target.bidder.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 存放适合投放的策略简信。
 * <br>Date:2015年9月1日 下午2:02:27
 * @author pujie
 */
public class Session {

	private static Integer DEFAULT_WEIGHT = 1;
	private Map<Integer, Integer> chosenGroup;
	private Integer selectedCreativeId;
	
	public Session(){
		chosenGroup = new HashMap<Integer, Integer>();
	}
	/**
	 * 存放策略ID和相应权重的对应关系。默认权重为1
	 * <br>Date:2015年9月1日 下午5:48:55
	 * @author pujie
	 * @param groupId
	 */
	public void put(Integer groupId){
		put(groupId,DEFAULT_WEIGHT);
	}
	/**
	 * 存放策略ID和相应权重的对应关系
	 * <br>Date:2015年9月1日 下午5:42:56
	 * @author pujie
	 * @param groupId
	 * @param weight
	 */
	public void put(Integer groupId,Integer weight){
		chosenGroup.put(groupId, weight);
	}
	/**
	 * 返回所有<策略ID-权重>映射关系的集合
	 * <br>Date:2015年9月1日 下午5:52:20
	 * @author pujie
	 * @return
	 */
	public Set<Entry<Integer, Integer>> entrySet(){
		return this.chosenGroup.entrySet();
	}
	/**
	 * 如果不包含任何元素，则返回true
	 * <br>Date:2015年9月15日 下午3:39:43
	 * @author pujie
	 */
	public boolean isEmpty(){
		return this.chosenGroup.isEmpty();
	}
	/**
	 * 返回所有的<策略ID>集合
	 * <br>Date:2015年9月1日 下午5:55:30
	 * @author pujie
	 * @return
	 */
	public Set<Integer> getGroupIds(){
		return this.chosenGroup.keySet();
	}
	/**
	 * 返回所有<权重>
	 * <br>Date:2015年9月2日 下午3:12:28
	 * @author pujie
	 */
	public Collection<Integer> getWeights(){
		return this.chosenGroup.values();
	}
	/**
	 * 按groupID移除数据
	 * <br>Date:2015年9月1日 下午5:59:35
	 * @author pujie
	 * @param groupId
	 */
	public Integer remove(Integer groupId){
		return this.chosenGroup.remove(groupId);
	}
	/**
	 * 清楚所有session信息
	 * <br>Date:2015年9月1日 下午5:47:00
	 * @author pujie
	 */
	public void clear(){
		this.chosenGroup.clear();
	}
	/**
	 * 获取被选中的创意
	 * <br>Date:2015年10月29日 下午2:52:26
	 * @author pujie
	 * @return
	 */
	public Integer getSelectedCreativeId() {
		return selectedCreativeId;
	}
	/**
	 * 设置<选中的创意>
	 * <br>Date:2015年10月29日 下午2:52:30
	 * @author pujie
	 * @param selectedCreativeId
	 */
	public void setSelectedCreativeId(Integer selectedCreativeId) {
		this.selectedCreativeId = selectedCreativeId;
	}
	
}
