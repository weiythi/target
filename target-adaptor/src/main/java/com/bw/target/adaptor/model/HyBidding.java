package com.bw.target.adaptor.model;

import java.io.Serializable;

import org.common.util.StringUtil;

/**
 * 海云竞价请求对象（bid-request）
 * <br>Date:2015年11月17日 下午4:17:43
 * @author pujie
 */
public class HyBidding implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 海云竞价请求
	 * <br>Date:2015年11月18日 下午4:23:57
	 * @author pujie
	 */
	public static final class BidRequest implements Serializable{

		private static final long serialVersionUID = 2517554480295926929L;
		
		//由海云adx统一产生的唯一请求ID
		private String id;
		//曝光对象，一次request可以包含多个imp
		private Imp[] imp;
		//媒体站点对象
		private Site site;
		//设备对象
		private Device device;
		//用户对象
		private User user;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * 曝光次数
		 * <br>Date:2015年11月18日 下午3:24:18
		 * @author pujie
		 * @return
		 */
		public int getImpSize(){
			return this.getImp()==null?0:this.getImp().length;
		}
		public Imp[] getImp() {
			return imp;
		}
		public void setImp(Imp[] imp) {
			this.imp = imp;
		}
		public Site getSite() {
			return site;
		}
		public void setSite(Site site) {
			this.site = site;
		}
		public Device getDevice() {
			return device;
		}
		public void setDevice(Device device) {
			this.device = device;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		
		/**
		 * 曝光对象
		 * <br>Date:2015年11月18日 下午4:25:58
		 * @author pujie
		 */
		public static final class Imp implements Serializable{
			private static final long serialVersionUID = -7037536681928691459L;
			//广告位宽
			private String id;
			private Long tagid;
			private int bidfloor;
			private Banner banner;
			private Video video;
			
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public Long getTagid() {
				return tagid;
			}
			public void setTagid(Long tagid) {
				this.tagid = tagid;
			}
			public int getBidfloor() {
				return bidfloor;
			}
			public void setBidfloor(int bidfloor) {
				this.bidfloor = bidfloor;
			}
			public Banner getBanner() {
				return banner;
			}
			public void setBanner(Banner banner) {
				this.banner = banner;
			}
			public Video getVideo() {
				return video;
			}
			public void setVideo(Video video) {
				this.video = video;
			}
			/**
			 * 图文广告信息
			 * <br>Date:2015年11月17日 下午4:17:43
			 * @author pujie
			 */
			public static final class Banner implements Serializable {

				private static final long serialVersionUID = 1L;
				
				//广告位宽
				private String w;
				//广告位高
				private String h;
				
				public String getW() {
					return w;
				}
				public void setW(String w) {
					this.w = w;
				}
				public String getH() {
					return h;
				}
				public void setH(String h) {
					this.h = h;
				}
			}
			
			/**
			 * 富媒体广告信息
			 * <br>Date:2015年11月17日 下午4:17:43
			 * @author pujie
			 */
			public static final class Video implements Serializable {

				private static final long serialVersionUID = 1L;
				//广告位宽
				private String w;
				//广告位高
				private String h;
				//广为位支持的格式
				private String mimes;
				//广告展示样式。1：in-stream，2：overlay。
				private Integer linearity;
				//视频广告播放的最短时间
				private Integer minduration;
				//视频广告播放的最长时间
				private Integer maxduration;
				
				
				public String getW() {
					return w;
				}
				public void setW(String w) {
					this.w = w;
				}
				public String getH() {
					return h;
				}
				public void setH(String h) {
					this.h = h;
				}
				public String getMimes() {
					return mimes;
				}
				public void setMimes(String mimes) {
					this.mimes = mimes;
				}
				public Integer getLinearity() {
					return linearity;
				}
				public void setLinearity(Integer linearity) {
					this.linearity = linearity;
				}
				public Integer getMinduration() {
					return minduration;
				}
				public void setMinduration(Integer minduration) {
					this.minduration = minduration;
				}
				public Integer getMaxduration() {
					return maxduration;
				}
				public void setMaxduration(Integer maxduration) {
					this.maxduration = maxduration;
				}	
			}
		}
		/**
		 * 媒体信息
		 * <br>Date:2015年11月17日 下午4:17:43
		 * @author pujie
		 */
		public static final class Site implements Serializable {

			private static final long serialVersionUID = 1L;
			
			//ADX用户ID(cookie)
			private String name;
			//当前页面url
			private String page;
			//父页面url
			private String ref;
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getPage() {
				return page;
			}
			public void setPage(String page) {
				this.page = page;
			}
			public String getRef() {
				return ref;
			}
			public void setRef(String ref) {
				this.ref = ref;
			}
		}
		/**
		 * 海云adx用户设备信息
		 * <br>Date:2015年11月17日 下午4:17:43
		 * @author pujie
		 */
		public static final class Device implements Serializable {
			
			private static final long serialVersionUID = -3971034253109356487L;
			//User-Agent
			private String ua;
			//IP地址
			private String ip;
			
			public String getUa() {
				return ua;
			}
			public void setUa(String ua) {
				this.ua = ua;
			}
			public String getIp() {
				return ip;
			}
			public void setIp(String ip) {
				this.ip = ip;
			}
		}
		
		/**
		 * 海云adx用户信息字段
		 * <br>Date:2015年11月17日 下午4:17:43
		 * @author pujie
		 */
		public static final class User implements Serializable {

			/**
			 * <br>Date:2015年11月17日 下午4:18:42
			 * @author pujie 
			 */
			private static final long serialVersionUID = 1L;
			//ADX用户ID(cookie)
			private String id;
			
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
		}	
	}
	/**
	 * 竞价接口返回(BidResponse)
	 * <br>Date:2015年11月18日 下午4:46:00
	 * @author pujie
	 */
	public static final class BidResponse implements Serializable{
		private static final long serialVersionUID = 4173177592143599568L;
		
		private String id; //请求ID（DSP生成）
		private String bidid; //对应Bid Request的ID（由ADX生成的唯一竞价ID）
		private Seatbid[] seatbid;//DSP出价
		
		public static BidResponse create(){
			return new BidResponse();
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getBidid() {
			return bidid;
		}
		public void setBidid(String bidid) {
			this.bidid = bidid;
		}
		public Seatbid[] getSeatbid() {
			return seatbid;
		}
		public void setSeatbid(Seatbid[] seatbid) {
			this.seatbid = seatbid;
		}
		/**
		 * DSP出价信息
		 * <br>Date:2015年11月18日 下午5:03:33
		 * @author pujie
		 */
		public static final class Seatbid implements Serializable{
			
			private static final long serialVersionUID = -3068368834041062267L;
			
			private Bid[] bid; //针对单次曝光的出价

			public static Seatbid create(){
				return new Seatbid();
			}
			
			public Bid[] getBid() {
				return bid;
			}

			public void setBid(Bid[] bid) {
				this.bid = bid;
			}


			/*
			 * 出价信息(bid object)
			 */
			public static final class Bid implements Serializable{
				private static final long serialVersionUID = -7791103024054584439L;
				
				private String id;// 由DSP产生的相应ID
				private String impid;//对应Bid Request IMP中的ID（曝光ID）
				private float price;//DSP出价，单位是分
				private String nurl;//竞价获胜通知url，如果为null，则使用注册时的url，价格宏{HYPRICE}
				private String adm;//广告素材url
				private String ext;//DSP自定义字段
				private String[] pvm;//曝光监测url，可以有多个，价格宏{HYPRICE}
				private String[] cvm;//点击监测url，可以有多个
				private String clickm;//点击目标url（达到页面）
				
				public static Bid create(){
					return new Bid();
				}
				
				public String getId() {
					return id;
				}
				public void setId(String id) {
					this.id = id;
				}
				public String getImpid() {
					return impid;
				}
				public void setImpid(String impid) {
					this.impid = impid;
				}
				public float getPrice() {
					return price;
				}
				public void setPrice(float price) {
					this.price = price;
				}
				public String getNurl() {
					return nurl;
				}
				public void setNurl(String nurl) {
					this.nurl = nurl;
				}
				public String getAdm() {
					return adm;
				}
				public void setAdm(String adm) {
					this.adm = adm;
				}
				public String getExt() {
					return ext;
				}
				public void setExt(String ext) {
					this.ext = ext;
				}
				public String[] getPvm() {
					return pvm;
				}
				/**
				 * 曝光监测地址
				 * <br>Date:2015年11月25日 下午2:31:00
				 * @author pujie
				 */
				public void addPvm(String pvm) {
					if(StringUtil.isNotBlank(pvm)){
						if(null==this.pvm){
							this.pvm = new String[]{pvm};
						}else{
							String[] temp = new String[this.pvm.length+1];
							System.arraycopy(this.pvm, 0, temp, 0, this.pvm.length);
							temp[this.pvm.length] = pvm;
							this.pvm = temp;
						}
					}
				}
				public String[] getCvm() {
					return cvm;
				}
				/**
				 * 点击监测地址
				 * <br>Date:2015年11月25日 下午2:31:19
				 * @author pujie
				 */
				public void addCvm(String cvm) {
					if(StringUtil.isNotBlank(cvm)){
						if(null == this.cvm){
							this.cvm = new String[]{cvm};
						}else{
							String[] temp = new String[this.cvm.length+1];
							System.arraycopy(this.cvm, 0, temp, 0, this.cvm.length);
							temp[this.cvm.length] = cvm;
									this.cvm = temp;
						}
					}
				}
				public String getClickm() {
					return clickm;
				}
				public void setClickm(String clickm) {
					this.clickm = clickm;
				}
			}
		}
		
	}
	
}
