package com.yaeyi.mobile.util;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@SuppressWarnings("unchecked")
public class StringUtil {

	/**
	 * 检查字符串对象是否为空串或null
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str)
	{
		return (null == str || "".equals(str)) ? true : false;
	}

	
	
	/**
     * 返回一个有效的字符串，将null转换为""。
     * @param value - 原字符串。
     * @return 有效的字符串。
     */
    public static String getString(Object value) {
        return (value == null || "null".equals(value)) ? "" : value.toString();
    }

    public static String secureToString(Object o){
        if(o==null) return null;
        return o.toString();
    }
    
    
    private static final char[] ZERO_ARRAY = "0000000000000000".toCharArray();

    /**
     * 将字符串之前补充0，如"9999"补到8位，将为"00009999"，最长不能超过16位
     *
     * @param string 待补充的字符串.
     * @param length 补充后新字符串的长度.
     * @return 用'0'补充后的新字符串.
     */
    public static final String zeroPadString(String string, int length) {
        if (string == null || string.length() > length) {
            return string;
        }
        StringBuilder buf = new StringBuilder(length);
        buf.append(ZERO_ARRAY, 0, length - string.length()).append(string);
        return buf.toString();
    }

    /**
     * 将日期化为毫秒后的数字，且将此用'0'补充到15位
     * @param date 日期
     * @return 将日期编码后的字符串.
     */
    public static final String dateToMillis(Date date) {
        return zeroPadString(Long.toString(date.getTime()), 15);
    }

    /**
     * 将字符串转换为长整型
     * @param s 要过滤的字符串
     * @return 过滤后的长整型
     */
    public static long getLong(String s) {
        s = getString(s);
        try {
            return s.equals("") ? 0 : Long.parseLong(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将字符串转换为整型
     * @param s 要过滤的字符串
     * @return 过滤后的整型
     */
    public static int getInt(String s) {
        s = getString(s);
        try {
            return s.equals("") ? 0 : Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将字符串转换为浮点型
     * @param s 要过滤的字符串
     * @return 过滤后的浮点型
     */
    public static float getFloat(String s) {
        s = getString(s);
        try {
            return s.equals("") ? 0 : Float.parseFloat(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将字符串转换为双精度型
     * @param s 要过滤的字符串
     * @return 过滤后的双精度型
     */
    public static double getDouble(String s) {
        s = getString(s);
        try {
            return s.equals("") ? 0 : Double.parseDouble(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将字符串内码转换
     */
    public static String convertStr(String s) {
        if (s == null)
            return "";
        else {
            try {
                return new String(s.getBytes("8859_1"), "gb2312");
            } catch (Exception ex) {
                return "";
            }
        }
    }

    /**
     * 将空字符串转换html中的&nbsp;
     * @param obj 要转换的字符串
     * @return 转换后的字符串
     */
    public static String emptyToNbsp(Object obj) {
        if (obj == null || obj.toString().equals(""))
            return "&nbsp;";
        return obj.toString();
    }

    /**
     * 将空对象转换为0字符串;
     * @param obj 要转换的字符串
     * @return 转换后的字符串
     */
    public static String emptyToZero(Object obj) {
        if (obj == null || obj.toString().equals(""))
            return "0";
        return obj.toString();
    }

    /**
     * 将空对象转换html中的&nbsp;
     * @param obj 要转换的字符串
     * @return 转换后的字符串
     */
    public static String zeroToNbsp(Object obj) {
        if (obj == null || obj.toString().equals("") || Float.parseFloat(obj.toString()) == 0)
            return "&nbsp;";
        return obj.toString();
    }

    /**
     * 将字符串数组用空串初始化;
     * @param arr 字符串数组
     * @return 初始化后数组
     */
    public static String[] initStringArrary(String[] arr) {
        if (arr == null)
            return arr;
        for (int i = 0; i < arr.length; i++)
            arr[i] = "";
        return arr;
    }

    public static String getStringValue(String value, String defaultValue) {
        return getString(value).equals("") ? defaultValue : value;
    }

    /**
     * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
     * &lt;table&gt;, etc) and converts the '&lt'' and '&gt;' characters to
     * their HTML escape sequences.
     *
     * @param in the text to be converted.
     * @return the input string with the characters '&lt;' and '&gt;' replaced
     *  with their HTML escape sequences.
     */
    public static final String escapeHTMLTags(String in) {
        return in.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    }

    public static boolean isEmpty(String s) {
        return (s == null || s.trim().equals("") || "null".equals(s.trim()));
    }
    public static boolean isEmpty(Object ob) {
        return (ob == null || ob.equals("") || "null".equals(ob));
    }
    public static boolean isNotEmpty(String s){
    	return s!=null && (s.trim().length()>0);
    }
    

    /**
     * 把一个字符串转换成javascript的源字符串。
     * @param str 一个字符串
     * @return 适合作为javascript的源字符串
     */
    public static String toJsString(String str) {
        StringBuffer sb = new StringBuffer(str.length() + 4);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String change = (String) charMap.get(c);
            if (change != null) {
                sb.append(change);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    @SuppressWarnings("rawtypes")
	private static Map charMap = new HashMap();

    static {
        charMap.put(Character.valueOf('\r'), "\\r");
        charMap.put(Character.valueOf('\n'), "\\n");
        charMap.put(Character.valueOf('\\'), "\\\\");
        charMap.put(Character.valueOf('\"'), "\\\"");
    }
    
    /**
     * 去除左边的0
     * @param s
     * @return
     */
    public static String trimLeftZero(String s){
    	if(s==null || s.length()<1) return null;
    	int begin=0;
    	for(int i=0;i<s.length()-1;i++){
    		if(s.charAt(i)!='0'){
    			begin=i;
    			break;
    		}
    	}
    	return s.substring(begin);
    }
    
    public static boolean match(String value,String match){
    	if(match==null){
    		return true;
    	}
    	if(value==null){
    		return match==null;
    	}
    	return value.indexOf(match)!=-1;
    }
    /**
     * 把一个集合转换为以<b>逗号</b>分隔的字符串返回
     * @param array T[] -- 数组，包含将被分隔出来的元素,转换String时调用T的toString()方法
     * @author Franklin --zhangyixuan(张逸轩)
     * */
    public static <T> String arrayToString(T[] array){
    	StringBuilder result = new StringBuilder();
		int i=0;
		for(T v:array){
			if(i>0)
				result.append(",");
			
			i++;
			result.append(v.toString());
		}
		
		return result.toString();
	}
    /**
     * 把一个集合转换为以<b>逗号</b>分隔的字符串返回
     * @param collection Collection<T> -- 集合，包含将被分隔出来的元素,转换String时调用T的toString()方法
     * @author Franklin --zhangyixuan(张逸轩)
     * */
    public static <T> String collectionToString(Collection<T> collection){
		String result = collection.toString();
		if(result.startsWith("[")&&result.endsWith("]"))
			result = result.substring(1,result.length()-1);
		return result;
	}
    /**
     * 把一个集合转换为以<b>指定的分隔符</b>分隔的字符串返回
     * @param collection Collection<T> -- 集合，包含将被分隔出来的元素,转换String时调用T的toString()方法
     * @param separator String -- 分隔符,比如为了返回'aaa','bbb','cccc' 可以以','为分隔符，然后自行在两边分别加上一个单引号
     * */
	public static <T> String collectionToString(Collection<T> collection,String separator){
		StringBuilder result = new StringBuilder();
		int i=0;
		for(T v:collection){
			if(i>0)
				result.append(separator);
			
			i++;
			result.append(v.toString());
		}
		
		return result.toString();
	}
	
}
