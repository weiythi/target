package com.bw.target.adaptor;


import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.common.util.JsonUtil;

import com.bw.target.adaptor.model.BesBidding.BidRequest;
import com.bw.target.adaptor.model.BesBidding.BidRequest.AdSlot;
import com.bw.target.adaptor.model.BesBidding.BidRequest.BaiduId;
import com.bw.target.adaptor.model.BesBidding.BidResponse;



/**
 * 模拟bes流量
 * <br>Date:2015年10月23日 上午10:56:28
 * @author pujie
 */
public class UrlClientBes {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		String url = "http://114.111.162.220:8080/bid";
//		String url = "http://180.96.26.130:8088/adtarget/tanx/bid";
//		String url = "http://localhost:8080/bid/bes";
//		String url = "http://183.192.160.19:7777/bid/bes";
//		String url = "http://118.26.145.7:8084/tanx/bid";
		String url = "http://bidding.baiwutong.com/bid/bes";
		postRequest(url);
	}
    public static void postRequest(String urlStr) throws Exception{
    	URL url = new URL(urlStr);
    	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestMethod("POST");
    	connection.setUseCaches(false);
    	connection.setInstanceFollowRedirects(true);
    	connection.setRequestProperty("Content-Type","application/octet-stream");
    	connection.connect();
    	
    	
    	
    	OutputStream outS = connection.getOutputStream();
    	DataOutputStream dOut = new DataOutputStream(outS);
    	byte[] data = getRequest().build().toByteArray();
    	dOut.write(data);
    	dOut.flush();
    	dOut.close();
    	
    	InputStream input = connection.getInputStream();
    	BidResponse responseModle = BidResponse.parseFrom(input);
    	System.out.println(responseModle.getId());
    	BidResponse.Ad ads = null;
    	if(responseModle.getAdCount()>0){
    		ads = responseModle.getAd(0);
    		System.out.println("竞价价格：");
    		System.out.println(ads.getMaxCpm());
    		System.out.println("创意ID：");
    		System.out.println(ads.getCreativeId());
    		System.out.println("广告检索时间：");
    		System.out.println(responseModle.getProcessingTimeMs());
    		System.out.println("附加参数：");
    		System.out.println(ads.getExtdata());
    	}else{
    		System.out.println("DSP 没有合适的广告进行竞价！");
    	}
//    	BufferedReader br = new BufferedReader(new InputStreamReader(input));
//    	String line="";
//    	while( (line=br.readLine()) != null){
//    		System.out.println(line);
//    	}
//    	br.close();
    	connection.disconnect();

    }
    public static BidRequest.Builder getRequest(){

    	BidRequest.Builder besReq = BidRequest.newBuilder();
    	AdSlot.Builder adSlot = AdSlot.newBuilder();
    	
    	besReq.setId("0aed081b00005396a456216c00495935");
    	besReq.setIsPing(false);
    	besReq.setIsTest(false);
    	BaiduId.Builder id = BaiduId.newBuilder();
    	id.setBaiduUserId("---s9cNaoxI=");
    	id.setBaiduUserIdVersion(2);
    	besReq.addBaiduIdList(id);
    	besReq.setIp("567.124.321.123");//114.236.10.238
    	besReq.setUserAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
    	besReq.setUrl("http://laodaobazi.iteye.com/blog/850799");
    	besReq.setReferer("https://www.baidu.com/s?wd=resin%20%E9%85%8D%E7%BD%AE&rsv_spt=1");
    	besReq.setSiteCategory(101);   	
    	besReq.addExcludedProductCategory(1001);
    	
    	
    	adSlot.setAdBlockKey(1000011);
    	adSlot.setSequenceId(1);
    	adSlot.setAdslotType(2);
    	adSlot.setWidth(300);
    	adSlot.setHeight(250);
    	adSlot.setSlotVisibility(1);
    	adSlot.addCreativeType(1);
    	adSlot.setMinimumCpm(10);
    	besReq.addAdslot(adSlot);
    	
    	
    	
    	return besReq;
    }
}
