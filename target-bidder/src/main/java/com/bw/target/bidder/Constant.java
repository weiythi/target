package com.bw.target.bidder;
/**
 * 系统常量
 * Date:2015年8月25日 下午5:40:41
 * @author pujie
 */
public class Constant {
	
	final public static String SYS_ENCODING = "utf-8";
	/**
	 * 内容分隔符：,
	 */
	final public static String CONTEXT_SEPARATE = ",";
	/**
	 * 投放渠道-tanx
	 */
	final public static int CHANNEL_TANX = 1;
	/**
	 * 默认此端口，暂不需要修改。
	 */
	public final static int SYNC_PORT = 1099;
	/**
	 * 同步器发布的服务名称
	 */
	public final static String SYNC_SERVER_NAME = "rmi://localhost:1099/sync";
	/**
	 * 日预算匀速投放
	 */
	final public static int DAY_PACE_UNIFORM = 0;
	/**
	 * 日预算尽速投放
	 */
	final public static int DAY_PACE_FAST = 1;
	/**
	 * 开关-打开
	 */
	final public static int SWITCH_ON = 1;
	/**
	 * 开关-关闭
	 */
	final public static int SWITCH_OFF = 0;
	/**
	 * 定向手段-地域定向 (1)
	 */
	final public static Integer TARGET_TYPE_REGION = 1;
	/**
	 * 定向手段-媒体类型定型(2)
	 */
	final public static Integer TARGET_TYPE_MEDIA_TYPE =2;
	/**
	 * 定向手段-时段定向 (5)
	 */
	final public static Integer TARGET_TYPE_TIME = 5;
	/**
	 * campaign临时key的前缀
	 */
	final public static String TEM_KEY_CAMPAING_PRE = "t_c_";
	final public static String TEM_KEY_CAMPAING_TSPEND = "tspend";//tracking写入的总消耗
	final public static String TEM_KEY_CAMPAING_SPEND = "spend";//tracking写入的日消耗
	final public static String TEM_KEY_CAMPAING_B_TSPEND = "b_tspend";//自身记录的总消耗
	final public static String TEM_KEY_CAMPAING_B_SPEND = "b_spend";//自身记录的日消耗
	/**
	 * customer临时key前缀。保存余额信息
	 */
	final public static String TEM_KEY_CUST_PRE = "t_cust_";
	final public static String TEM_KEY_CUST_B_BLANCE = "b_blance";
	final public static String TEM_KEY_CUST_BLANCE = "blance";
	/**
	 * group临时key的前缀。主要用于临时key的清除。
	 */
	final public static String TEMP_KEY_GROUP_F_PRE = "t_g_f_";
	
}
