package com.bw.target.adaptor;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.common.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.bw.target.adaptor.model.HyBidding;
import com.bw.target.adaptor.model.HyBidding.BidRequest;
import com.bw.target.adaptor.model.HyBidding.BidRequest.User;


/**
 * 模拟海云流量
 * <br>Date:2015年10月23日 上午10:56:28
 * @author pujie
 */
public class UrlClientHy {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		String url = "http://114.111.162.220:8080/bid";
//		String url = "http://172.17.50.3:8080/bid/hy";
//		String url = "http://localhost:8080/bid/hy";
//		String url = "http://183.192.160.19:7777/bid/hy";
		String url = "http://bidding.baiwutong.com/bid/hy";
//		String url = "http://172.17.110.16:8888/bid/hy";
//		String url = "http://nj.vaolan.com:8088/adtarget/tanx/bid";
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
    	connection.setRequestProperty("Content-Type","application/json");
    	connection.connect();
    	
    	
    	
    	OutputStream outS = connection.getOutputStream();
    	DataOutputStream dOut = new DataOutputStream(outS);
    	byte[] data = getRequest().getBytes();
    	dOut.write(data);
    	dOut.flush();
    	dOut.close();
    	
    	int statuCode = connection.getResponseCode();
    	try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
    		StringBuilder result = new StringBuilder();
    		String line = reader.readLine();
    		while(StringUtil.isNotBlank(line)){
    			result.append(line);
    			line = reader.readLine();
    		}
    		System.out.println("http状态码："+statuCode);
    		System.out.println("返回内容："+result.toString());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	connection.disconnect();

    }
    public static String getRequest(){
    	BidRequest request = new BidRequest();
    	request.setId("reqid_000001");
    	BidRequest.User user = new BidRequest.User();
    	user.setId("adxid_1110011");
    	request.setUser(user);
    	BidRequest.Device device = new BidRequest.Device();
    	device.setIp("36.110.62.178");
    	device.setUa("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");
    	request.setDevice(device);
    	BidRequest.Site site = new BidRequest.Site();
    	site.setName("新浪");
    	site.setPage("http://www.sina.com.cn/");
    	request.setSite(site);
    	BidRequest.Imp imp = new BidRequest.Imp();
    	imp.setId("impid_22002123");
    	imp.setTagid(987532110L);
    	imp.setBidfloor(1);
    	BidRequest.Imp.Banner banner = new BidRequest.Imp.Banner();
    	banner.setW("300");
    	banner.setH("250");
    	imp.setBanner(banner);
    	request.setImp(new BidRequest.Imp[]{imp});
    	return JSON.toJSONString(request);
    }
}
