package com.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static float toFloat(String str) {
		if(str == null){
			return -1F;
		}
		try {
			return Float.parseFloat(str);
		} catch (Exception e) {
			return -1F;
		}
	}

	public static double toDouble(String str) {
		if(str == null){
			return -1D;
		}
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return -1D;
		}
	}
	
	public static int toInt(String str) {
		if(str == null){
			return -1;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}

	public static long toLong(String str) {
		if(str == null){
			return -1L;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return -1L;
		}
	}

	public static String toSql(String src) {
		if(src != null){
			src = src.trim();
			src = src.replace("\\", "\\\\");
			src = src.replace("'", "\\'");
		}
		return src;
	}

	// 用于 like '??'
	public static String toSqlLike(String src) {
		if(src != null){
			src = src.trim();
			src = src.replace("\\", "\\\\");
			src = src.replace("'", "\\'");
			src = src.replace("%", "\\%");
			src = src.replace("_", "\\_");
		}
		return src;
	}

	public static String formatDouble(double d) {
		DecimalFormat df = new DecimalFormat("##########0.##");
		return df.format(d);
	}

	public static String formatDouble2(double d) {
		DecimalFormat df = new DecimalFormat("##########0.###");
		return df.format(d);
	}

	public static String formatFloat(float d) {
		DecimalFormat df = new DecimalFormat("##########0.##");
		return df.format(d);
	}

	public static String formatFloat2(float d) {
		DecimalFormat df = new DecimalFormat("##########0.###");
		return df.format(d);
	}

	public static String formatFloat3(float d) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}
	
	public static String toWml(String src) {
		if (src == null){
			return "";
		}
		src = src.replaceAll("&", "&amp;");
		src = src.replaceAll("\\$", "");
		src = src.replaceAll("¤", "O");
		src = src.replaceAll("<", "&lt;");
		src = src.replaceAll(">", "&gt;");
		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll("", "");
		src = src.replace("\"", "&#34;");
		return src;
	}
	static Pattern p = Pattern.compile("\\[img\\]([^\\[]*)\\[img\\]");

	static String urlRegex = "[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][%]&[)][(]]*";

	public static String toHtml(String src) {
		if (src == null){
			return "";
		}
		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll(" ", "&nbsp;&nbsp;");
		src = src.replaceAll(urlRegex, "<a href=\"$0\" target=\"_blank\">$0</a>");
		Matcher m = p.matcher(src);
		while (m.find()) {
			String s = m.group(1);
			s = "<a href=\"" + s + "\" target=_blank>" + "<img src=\"" + s + "\" width=\"400\" border=0 alt=\"点击查看大图\"/></a>";
			src = m.replaceFirst(s);
			m = p.matcher(src);
		}
		return src;
	}

	public static String array2String(String[] strs, String split) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(strs.length * 10);
		for (int i = 0; i < strs.length; i++) {
			if (i != 0) {
				builder.append(split);
			}
			builder.append(strs[i]);
		}
		return builder.toString();
	}

	public static boolean isNumeric(String str) {
		if (str.matches("\\d*")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMobile(String s) {
		if (s == null) {
			return false;
		}
		if (!s.startsWith("13") && !s.startsWith("15") && !s.startsWith("14") && !s.startsWith("18")) {
			return false;
		}
		if (s.length() != 11) {
			return false;
		}
		try {
			Long.parseLong(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean toBoolean(String str){
		boolean b = false;
		if(str != null){
			try{
				b = Boolean.parseBoolean(str);
			}catch (Exception e) {
				return b;
			}
		}
		return b;
	}
	
	//异常信息输出转换
    public static String getExceptionInfo(Throwable t) {   
        StringWriter stringWriter= new StringWriter();   
        PrintWriter writer= new PrintWriter(stringWriter);   
        t.printStackTrace(writer);   
        StringBuffer buffer= stringWriter.getBuffer();   
        return buffer.toString();   
    }   

    /**
     * 说明：查询字符串数据中，是否包含某字符串
     */
    public static boolean hasStrArray(String[] array, String s){
    	boolean result = false;
    	for(int i=0;i<array.length;i++){
    		if(array[i].equals(s)){
    			result = true;
    			break;
    		}
    	}
    	return result;
    }
    
    /**
     *  &lt; < 小于号 
	 *  &gt; > 大于号 
	 *  &amp; & 和 
	 *  &apos; ' 单引号 
	 *  &quot; " 双引号
     * 功能:需要转化为xml 的特殊字符变化为 转义字符
     */
    public static String changStrToXml(String str){
    	String xml="";
    	if(str==null || str.length()<1) return "";
    	xml=str.replaceAll("<", "&lt;");
    	xml=xml.replaceAll(">", "&gt;");
    	xml=xml.replaceAll("&", "&&amp;");
    	xml=xml.replaceAll("'", "&apos;");
    	xml=xml.replaceAll("\"", "&quot;");
    	return xml;
    }
    
    static Pattern pattern = Pattern.compile("\\d{1,9}");
    
    /**
     * 
     * 功能:转化成数字
     */
    public static int parstInt(String id){
    	if(id==null) return 0;
    	Matcher m = pattern.matcher(id);
		if(m.matches()){
			return Integer.parseInt(id);
		}else{
			return 0;
		}
    }
    
    /**
     * 
     * 功能:转化成数字。 返回负数
     */
    public static int parstBackMinus(String id){
    	if(id==null) return -1;
    	Matcher m = pattern.matcher(id);
		if(m.matches()){
			return Integer.parseInt(id);
		}else{
			return -1;
		}
    }
    
    /**
	 * 功能:将字符 json化
	 * str 满足简单的json 基本 但 不带双引号
	 * 缺陷： 值里面含有 ,:}{ 将无法封装
	 */
	public static String toJsonStr(String str){
		if(str==null|| str.length()<1) return null;
		StringBuilder sb = new StringBuilder();
		StringBuilder temp=new StringBuilder();
		for(int i=0;i<str.length();i++){
			char a=str.charAt(i);
			if(a=='{' || a=='}' || a==':' || a==','){
				if(temp.length()>0){
					Matcher m = pattern.matcher(temp.toString());
					if(m.matches()|| temp.toString().equals("null")){ //若是数字和 null 将不加双引号
						sb.append(temp);
					}else{
						sb.append("\"").append(temp).append("\"");
					}
				}
				temp.delete(0, temp.length());
				sb.append(a);
				continue;
			}
			temp.append(a);
		}
		return sb.toString();
	}
}