package com.bw.target.bidder.retrieval.filter;

import java.util.Calendar;
import java.util.Map;

import org.common.util.DateUtil;
import org.common.util.NumberUtil;
import org.framework.cache.redis.JedisClusterFactory;

import redis.clients.jedis.JedisCluster;

import com.bw.target.bidder.Constant;
import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.retrieval.AdFilter;
/**
 * <余额判断>
 * 到量控制<总消耗><日消耗><br>
 * 消耗匀速
 * <br>Date:2015年8月27日 下午5:37:27
 * @author pujie
 */
public class AmountControlFilter implements AdFilter {

	private JedisCluster jc =JedisClusterFactory.getcluster("dsp");
	
	@Override
	public boolean filter(AdGroup group, Bidding.BidRequestOrBuilder request,AdContainer container) {
		boolean flag = false;
		int cId = group.getParent().getCampaignId();
		int minimumPrice = request.getAdslotOrBuilder(0).getMinimumPrice();
		if(blanceControl(group,minimumPrice)){ //判断余额
			Map<String, String> campaignSpend = jc.hgetAll(Constant.TEM_KEY_CAMPAING_PRE+cId);
			flag = budgetSpend(group,campaignSpend) && speedSmooth(group,campaignSpend);
		}
		return  flag;
	}
	/**
	 * 客户余额控制
	 * <br>Date:2015年12月18日 下午2:27:33
	 * @author pujie
	 * @return
	 */
	public boolean blanceControl(AdGroup group,int minimumPrice){
		boolean flag = false;
		String custKey = Constant.TEM_KEY_CUST_PRE + group.getParent().getCustId();
		Map<String,String> blance = jc.hgetAll(custKey);
		if(NumberUtil.toInt(blance.get(Constant.TEM_KEY_CUST_B_BLANCE)) <minimumPrice){ //bidder记录的余额耗尽
			if(NumberUtil.toInt(blance.get(Constant.TEM_KEY_CUST_BLANCE)) <minimumPrice){ //tracking记录的余额耗尽
				group.getParent().turnOff();   //下线该campaign
				flag = false;
			}else{
				jc.hset(custKey, Constant.TEM_KEY_CUST_B_BLANCE, blance.get(Constant.TEM_KEY_CUST_BLANCE)); //同步tracking记录的消耗
				flag = true;
			}
		}else{
			flag = true;
		}
		return flag;
	}
	/**
	 * 预算消耗控制
	 * <br>Date:2015年9月22日 下午5:32:47
	 * @author pujie
	 * @param group
	 * @param amount
	 */
	public boolean budgetSpend(AdGroup group,Map<String, String> amount){
		boolean flag = false;
		int cId = group.getParent().getCampaignId();
		if(group.getParent().getBudget()*1000 <= NumberUtil.toInt(amount.get(Constant.TEM_KEY_CAMPAING_B_TSPEND)) || //自身<总消耗>耗尽
				group.getParent().getDayBudget()*1000 <= NumberUtil.toInt(amount.get(Constant.TEM_KEY_CAMPAING_B_SPEND))){ //自身<日消耗>耗尽
			if(group.getParent().getBudget()*1000 <= NumberUtil.toInt(amount.get(Constant.TEM_KEY_CAMPAING_TSPEND)) ||   //tracking记录<总消耗> 耗尽
					group.getParent().getBudget()*1000 <= NumberUtil.toInt(amount.get(Constant.TEM_KEY_CAMPAING_SPEND))){//tracking记录<日消耗> 耗尽
				group.getParent().turnOff();   //下线该campaign
				flag = false;
			}else{
				jc.hset(Constant.TEM_KEY_CAMPAING_PRE+cId, Constant.TEM_KEY_CAMPAING_B_TSPEND, amount.get(Constant.TEM_KEY_CAMPAING_TSPEND)); //以tracking<总消耗>修改自身记录
				jc.hset(Constant.TEM_KEY_CAMPAING_PRE+cId, Constant.TEM_KEY_CAMPAING_B_SPEND, amount.get(Constant.TEM_KEY_CAMPAING_SPEND)); //以tracking<日消耗>修改自身记录
				flag =true;
			}		
		}else{
			flag =true;
		}
		return flag;
	}
	/**
	 * 预算匀速控制
	 * <br>Date:2015年9月22日 下午2:20:34
	 * @author pujie
	 */
	private boolean speedSmooth(AdGroup group,Map<String, String> amount){
		if(!group.getParent().isPacingSmooth()) return true; // 不要求 预算 匀速投放
		float realSpendRate = NumberUtil.toInt(amount.get(Constant.TEM_KEY_CAMPAING_SPEND)) / (float)(group.getParent().getDayBudget()*1000); // 《日消耗》已消耗比例。
		Calendar calendar = DateUtil.getCurrentCalendar();
		int dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) - 1) * 24; // 星期代表的值 * 24(小时)
		float planSpendRate = 1.0f; // 比例100%  表示应该消耗完毕了。
		if(group.containsTargetKey(Constant.TARGET_TYPE_TIME)){
			planSpendRate = NumberUtil.indexAndRate(group.getTimeIntervals(),dayOfWeek, dayOfWeek + 23, dayOfWeek + calendar.get(Calendar.HOUR_OF_DAY));
		}else{
			planSpendRate = (calendar.get(Calendar.HOUR_OF_DAY) +1) / 24.0f; //默认24小时 匀速投放。
		}
		return planSpendRate > realSpendRate;
	}

}
