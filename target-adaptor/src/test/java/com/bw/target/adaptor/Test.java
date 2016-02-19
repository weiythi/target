package com.bw.target.adaptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.ArrayUtils;
import org.common.util.JsonUtil;

import com.bw.target.adaptor.model.HyBidding;
import com.bw.target.adaptor.model.HyBidding.BidResponse.Seatbid.Bid;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
/*
		String str="{\"dsp_id\":\"001\",\"token\":\"1abcdefghij\",\"version\":\"2\",\"id\":\"123456789\",\"imp\":[{\"id\":\"333\",\"tagid\":3,\"bidfloor\":30.0,\"banner\":{\"w\":300,\"h\":250}}],\"site\":{\"name\":\"百度\",\"page\":\"https://www.baidu.com/\",\"ref\":\"\"},\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0\",\"ip\":\"\"},\"user\":{\"id\":\"_adx3adf\"}}";
		
		HyBidding.BidRequest req = JsonUtil.jsonToBean(str, HyBidding.BidRequest.class);
		System.out.println(req.getId());
		System.out.println(req.getUser().getId());
		*/
/*		String str = "a";
		byte[] b = new byte[50];
		byte[] src = str.getBytes();
		System.arraycopy(src, 0, b, 0, src.length);
		System.out.println(ArrayUtils.toString(b));
		System.out.println(new String(b).trim());*/
		Map<String,String> cMap = new ConcurrentHashMap<String, String>();
		Map< String, String> map = new HashMap<String, String>();
		map.put("", "");
		Bid bid = new Bid();
		System.out.print(bid.getCvm()==null);
		
	}

}
