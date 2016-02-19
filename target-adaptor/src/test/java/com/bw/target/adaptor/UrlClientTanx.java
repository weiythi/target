package com.bw.target.adaptor;


import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.bw.target.adaptor.model.TanxBidding.BidRequest;
import com.bw.target.adaptor.model.TanxBidding.BidRequest.AdzInfo;
import com.bw.target.adaptor.model.TanxBidding.BidRequest.Mobile;
import com.bw.target.adaptor.model.TanxBidding.BidResponse;



public class UrlClientTanx {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		String url = "http://114.111.162.220:8080/bid";
//		String url = "http://180.96.26.130:8088/adtarget/tanx/bid";
		String url = "http://localhost:8080/bid/tanx";
//		String url = "http://118.26.145.7:8084/tanx/bid";
//		String url = "http://nj.vaolan.com:8088/adtarget/tanx/bid";
		long start = System.currentTimeMillis();
		postRequest(url);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
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
    	System.out.println(responseModle.getVersion());
    	System.out.println(responseModle.getBid());
    	BidResponse.Ads ads = null;
    	if(responseModle.getAdsCount()>0){
    		ads = responseModle.getAds(0);
    		System.out.println("落地页面：");
    		System.out.println(ads.getDestinationUrl(0));
    		System.out.println("点击地址：");
    		System.out.println(ads.getClickThroughUrl(0));
    		System.out.println("竞价html：");
    		System.out.println(ads.getHtmlSnippet());
    		System.out.println(ads.getResourceAddress());
    		System.out.println("竞价价格：");
    		System.out.println(ads.getMaxCpmPrice());
    		System.out.println("创意ID：");
    		System.out.println(ads.getCreativeId());
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

    	BidRequest.Builder bidRequest = BidRequest.newBuilder();
    	AdzInfo.Builder adzInfo = AdzInfo.newBuilder();
    	bidRequest.setVersion(3);
    	bidRequest.setBid("0aed081b00005396a456216c00495935");
    	bidRequest.setIsTest(1);
    	bidRequest.setIsPing(0);
    	bidRequest.setTid("---s9cNaoxI=");
    	bidRequest.setIp("567.124.321.123");//114.236.10.238
    	bidRequest.setUserAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
    	bidRequest.setTimezoneOffset(13);
    	bidRequest.setTidVersion(1);

    	bidRequest.addExcludedClickThroughUrl("http://clickthrough.domain9.com");
    	bidRequest.addExcludedClickThroughUrl("http://clickthrough.domain4.com");
    	bidRequest.addExcludedClickThroughUrl("http://clickthrough.domain8.com");
    	
    	bidRequest.setUrl("htt://www.zhifang.com");
    	bidRequest.setCategory(40101);
    	bidRequest.setAdxType(0);
    	bidRequest.setAnonymousId("http://2.google.anonymous/");
    	bidRequest.setDetectedLanguage("en");
    	bidRequest.setCategoryVersion(1);
    	
    	adzInfo.setId(0);
    	adzInfo.setPid("mm_34022157_4042931_14514554");
    	adzInfo.setSize("300x250");
    	adzInfo.setAdBidCount(1);
    	adzInfo.addViewType(103);
    	
    	adzInfo.setViewScreen(AdzInfo.ViewScreen.SCREEN_FOURTH);
//    	adzInfo.addExcludedFilter(2);
//    	adzInfo.addExcludedFilter(3);
//    	adzInfo.addExcludedFilter( 6);
    	adzInfo.setMinCpmPrice(0);
    	bidRequest.addAdzinfo(0,adzInfo);
    	Mobile.Builder mobile = Mobile.newBuilder();
    	mobile.setAdNum(0);
    	mobile.setIsFullscreen(false);
    	mobile.setPackageName("com.moji.weather");
    	Mobile.Device.Builder device = Mobile.Device.newBuilder();
    	device.setPlatform("android");
    	device.setOs("android");
    	device.setDeviceId("device_id_0001");
    	mobile.setDevice(device);
    	mobile.setIsApp(true);
//    	bidRequest.setMobile(mobile);
    	return bidRequest;
    }
}
