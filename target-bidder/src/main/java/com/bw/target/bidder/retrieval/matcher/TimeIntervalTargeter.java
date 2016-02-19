package com.bw.target.bidder.retrieval.matcher;

import java.util.Calendar;

import org.common.util.DateUtil;
import org.common.util.NumberUtil;

import com.bw.target.bidder.model.AdContainer;
import com.bw.target.bidder.model.AdGroup;
import com.bw.target.bidder.model.Bidding;
import com.bw.target.bidder.retrieval.Targeter;
/**
 * 定向手段-<时段定向>
 * <br>Date:2015年9月14日 上午11:49:48
 * @author pujie
 */
public class TimeIntervalTargeter implements Targeter{

	@Override
	public boolean doTarget(AdContainer container, AdGroup group,String targetResource) {
		
		return group.containsTime(NumberUtil.toInt(targetResource));
	}

	@Override
	public String generateResource(Bidding.BidRequestOrBuilder request) {
		Calendar calendar = DateUtil.getCurrentCalendar();
		return ((calendar.get(Calendar.DAY_OF_WEEK) - 1) * 24 + calendar.get(Calendar.HOUR_OF_DAY)) + "";
	}

}
