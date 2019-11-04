package com.cost168.costaudit.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * ClassName: JsonUtils 
 * @Description: TODO
 * @author lixiang
 * @date 2018-12-17上午10:13:04
 * @Company  广东华联软件科技有限公司
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 
     * @Description: 将对象转换成json字符串
     * @param @param data
     * @param @return   
     * @return String  
     * @throws
     * @author lixiang
     * @date 2018-12-17上午10:13:27
     * @Company  广东华联软件科技有限公司
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 
     * @Description: 将json结果集转化为对象
     * @param @param jsonData
     * @param @param beanType
     * @param @return   
     * @return T  
     * @throws
     * @author lixiang
     * @date 2018-12-17上午10:13:44
     * @Company  广东华联软件科技有限公司
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @Description: 将json数据转换成pojo对象list
     * @param @param jsonData
     * @param @param beanType
     * @param @return   
     * @return List<T>  
     * @throws
     * @author lixiang
     * @date 2018-12-17上午10:14:01
     * @Company  广东华联软件科技有限公司
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * 
     * @Description: pojo转map
     * @param @param obj
     * @param @return   
     * @return Map<String,Object>  
     * @throws
     * @author lixiang
     * @date 2018-12-17上午10:20:52
     * @Company  广东华联软件科技有限公司
     */
    public static Map<String, Object> object2Map(Object obj) {
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 if (obj == null) {
             return map;
         }
    	 Class<? extends Object> clazz = obj.getClass();
         Field[] fields = clazz.getDeclaredFields();
         try {
			for(Field field : fields){
				field.setAccessible(true);
				if(null!=field.getName()&&!"".equals(field.getName())){
					map.put(field.getName(), field.get(obj));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return map;
    }
    
}
