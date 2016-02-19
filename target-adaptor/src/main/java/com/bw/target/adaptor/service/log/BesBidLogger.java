package com.bw.target.adaptor.service.log;

import org.common.util.StringUtil;

import com.bw.target.adaptor.model.BesBidding.BidRequest;
import com.bw.target.adaptor.util.Constant;

/**
 * 按照规范信息把tanx竞价请求转换成以“\t”分割的字符串。
 * <br>Date:2015年10月20日 上午10:39:14
 * @author pujie
 */
public class BesBidLogger extends DefaultBidLogger {

	@Override
	public <T> String getRequestStr(T request) {
		BidRequest.Builder besReq = (BidRequest.Builder)request;
		StringBuilder sb = new StringBuilder();
		sb.append(Constant.CHANNEL_BES).append(Constant.COLUMN_SEPARATOR)
		.append(Constant.LOG_TYPE_REQUEST).append(Constant.COLUMN_SEPARATOR)
		.append(besReq.getId()).append(Constant.COLUMN_SEPARATOR);
		if(besReq.getBaiduIdListCount()>0){
			sb.append(besReq.getBaiduIdListBuilder(0).getBaiduUserId());//百度用户标识
		}else{
			sb.append("");
		}
		sb.append(Constant.COLUMN_SEPARATOR)
		.append(besReq.getIp()).append(Constant.COLUMN_SEPARATOR)
		.append(besReq.getUrl()).append(Constant.COLUMN_SEPARATOR)
		.append(besReq.getUserAgent()).append(Constant.COLUMN_SEPARATOR);
		BidRequest.AdSlotOrBuilder slot = null;
		if(besReq.getAdslotOrBuilderList().size() >0){
			slot = besReq.getAdslotOrBuilder(0);
		}else{
			slot = BidRequest.AdSlot.newBuilder();
		}
		sb.append(slot.getAdBlockKey()).append(Constant.COLUMN_SEPARATOR)
		.append(slot.getWidth()+"x"+slot.getHeight()).append(Constant.COLUMN_SEPARATOR)
		.append(slot.getAdslotType()).append(Constant.COLUMN_SEPARATOR)
		.append(slot.getMinimumCpm()).append(Constant.COLUMN_SEPARATOR)
		.append(slot.getSlotVisibility()).append(Constant.COLUMN_SEPARATOR)
		.append(StringUtil.list2String(slot.getCreativeTypeList(),",")).append(Constant.COLUMN_SEPARATOR)
		.append(besReq.getSiteCategory()).append(Constant.COLUMN_SEPARATOR)
		.append(StringUtil.list2String(besReq.getUserCategoryList(), ",")).append(Constant.COLUMN_SEPARATOR)
		.append(System.currentTimeMillis());
		return sb.toString();
	}

}
