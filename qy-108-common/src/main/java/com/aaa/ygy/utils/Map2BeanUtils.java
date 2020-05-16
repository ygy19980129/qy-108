package com.aaa.ygy.utils;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: qy-108
 * @description: bean
 * @author: ygy
 * @create: 2020-05-12-2020/5/12 18:04
 */
public class Map2BeanUtils {

    private Map2BeanUtils(){

    }

    // 使用高性能java实例化工具
    private final static ObjenesisStd OBJENESIS = new ObjenesisStd(true);
    // StringBuffer的性能虽然次于StringBuilder，但是StringBuilder是线程安全的
    private final static StringBuffer STRING_BUFFER = new StringBuffer();
    // 使用Map集合作用本地缓存池来使用(也必须要保证线程安全)
    private final static ConcurrentHashMap<Class, MethodAccess> CONCURRENT_HASH_MAP =
            new ConcurrentHashMap<Class, MethodAccess>();

    /** 
     * @Description:  把map转换为bean对象
     * @Param: 
     * @return:  
     * @Author: ygy
     * @Date: 2020/5/12 18:12 
     */
    public static <T> T map2Bean(Map<String,Object> map,Class<T> clazz){
        // 通过clazz类型获取泛型对象(获取咱们所需要的对象)(但是这个对象是一个空对象)
        T instance = OBJENESIS.newInstance(clazz);
        MethodAccess methodAccess = CONCURRENT_HASH_MAP.get(clazz);
        if (null == methodAccess){
            methodAccess = MethodAccess.get(clazz);
            // 就是为了获取下一步的get和set方法
            CONCURRENT_HASH_MAP.putIfAbsent(clazz,methodAccess);
            //循环数据
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 于是就可以获取到Map中的各种数据，我就需要通过setter方法进行赋值了
                String setMethodName = setMethodName(entry.getKey());
                int index = methodAccess.getIndex(setMethodName, entry.getValue().getClass());
                methodAccess.invoke(instance, index, entry.getValue());
            }
        }
        return instance;
        }


        /** 
         * @Description:   通过字段获取set的方法名
         * @Param: 
         * @return:  
         * @Author: ygy
         * @Date: 2020/5/12 18:25 
         */
    private static String setMethodName(String fieldName) {
        // 先把这个字段的首字母大写
        String filedJava = firstToUpperCase(fieldName);
        STRING_BUFFER.setLength(0);// 确保了StringBuffer中没有任何数据
        // 拼接set方法
        return STRING_BUFFER.append("set").append(filedJava).toString();
    }

    /**
     * @Description: 把String类型字符串的首字母大写
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 18:26
     */
    private static String firstToUpperCase(String field) {
        return field.substring(0,1).toUpperCase() + field.substring(1);
    }

}
    
