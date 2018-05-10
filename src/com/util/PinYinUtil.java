package com.util;

import java.util.HashMap;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class PinYinUtil {
	
	private static HashMap<Character,String> multiTones = null;
	private static HashMap<Character, String> multiTonesName = null;

	static{
		multiTones = new HashMap<Character, String>();// 姓里多音字
        multiTones.put('单', "shan");
        multiTones.put('解', "xie");
        multiTones.put('宓', "fu");
        multiTones.put('曾', "zeng");
        multiTones.put('召', "shao");
        multiTones.put('种', "chong");
        multiTones.put('莘', "shen");
        multiTones.put('颉', "xie");
        multiTones.put('车', "ju");
        multiTones.put('朴', "piao");
        multiTones.put('車', "ju");
        multiTones.put('重', "chong");
        multiTones.put('区', "ou");
        multiTones.put('仇', "qiu");
        multiTones.put('秘', "bi");
        multiTones.put('冼', "xian");
        multiTones.put('折', "she");
        multiTones.put('查', "zha");
        multiTones.put('尉', "yu");
		multiTones.put('长', "chang");
		multiTones.put('缪', "miao");
		multiTonesName = new HashMap<Character, String>();// 名里多音字
		multiTonesName.put('长', "chang");

	}
    
	 /**  
     * 汉字转换位汉语拼音，英文字符不变  
     */     
    public static String converterToSpell(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String pinyinName = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断能否为汉字字符
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                	if(i==0 && multiTones.containsKey(t1[i])){
                		pinyinName += multiTones.get(t1[i]);
                	}else{
						if (multiTonesName.containsKey(t1[i])){
							t2[0] = multiTonesName.get(t1[i]);
						}else{
							t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
						}
                        pinyinName += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                	}
                } else {
                    // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                    pinyinName += Character.toString(t1[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pinyinName;
    }
}
