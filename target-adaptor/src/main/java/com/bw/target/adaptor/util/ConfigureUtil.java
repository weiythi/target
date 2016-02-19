package com.bw.target.adaptor.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.common.util.NumberUtil;
import org.common.util.StringUtil;
import org.common.util.file.ConfigProperties;

/**
 * 配置文件中参数的 相关计算。
 * <br>Date:2015年10月20日 下午5:34:10
 * @author pujie
 */
public class ConfigureUtil {
	
	private static final String CREATIVE_TYPE_MAPPING_BES = "creative.type.mapping.bes";
	//Map<adxCreativeType,innerCreativeType>
	private static Map<Integer, Integer> besMapping = new HashMap<Integer, Integer>();

	/**
	 * 初始化
	 * <br>Date:2015年10月20日 下午5:53:19
	 * @author pujie
	 */
	public static void init(){
		String creativeTypeMappingBes = ConfigProperties.getProperty(CREATIVE_TYPE_MAPPING_BES);
		if(StringUtil.isBlank(creativeTypeMappingBes)){
			throw new NullPointerException("not find configure param creative.type.mapping.bes");
		}
		String[] mappings = creativeTypeMappingBes.split(",");
		String[] tupple;
		for(String mapping:mappings){
			tupple = mapping.split("-");
			besMapping.put(NumberUtil.toInt(tupple[0]), NumberUtil.toInt(tupple[1]));
		}
	}
	/**
	 * 把支持的创意类型，反转为 不支持的创意类型。
	 * <br>Date:2015年10月20日 下午5:54:11
	 * @param List<Integer> - 允许的创意类型<bes>
	 * @author pujie
	 * @return excludeCreativeType
	 */
	public static List<Integer> reversCreativeTypeBes(List<Integer> creativeTypes){
		if(null == creativeTypes || creativeTypes.size()==0 ){
			return new LinkedList<Integer>(besMapping.values());
		}
		List<Integer> excludeCreativeType = new LinkedList<>();
		for(Entry<Integer, Integer> entry:besMapping.entrySet()){
			if( !creativeTypes.contains(entry.getKey())){ //不支持的创意类型
				excludeCreativeType.add(entry.getValue()); 
			}
		}
		return excludeCreativeType;
	}
	/**
	 * 创意类型转换
	 * <br>Date:2015年10月20日 下午7:01:55
	 * @author pujie
	 * @param innerCreativeType
	 * @return besCreativeType
	 */
	public static Integer transCreativeTypeBes(Integer innerCreativeType){
		Integer besCreativeType = 0;
		for(Entry<Integer, Integer> entry:besMapping.entrySet()){
			if(entry.getValue() == innerCreativeType){
				besCreativeType = entry.getKey();break;
			}
		}
		return besCreativeType;
	}
}
