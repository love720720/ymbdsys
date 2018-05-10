package com.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
 * 常量定义
 */
public class Constant {

	public final static DateFormat YYMMDDFOMAT = new SimpleDateFormat("yyMMdd");
	public final static DateFormat YYYYMMDD_FOMAT = new SimpleDateFormat("yyyy-MM-dd");
	public final static DateFormat YYYYMMDDHHMMSS_FOMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static String TREELIST = "treeList";
	public final static String BUTTONLIST = "buttonList";
	public final static long LONGINIT = 0L;
	//创建时 默认的账号密码
	public final static String PASSWORD = "admin";
	//允许上传的文件后缀类型
	public final static String [] SUFFIX = {".jpg",".jpge"};
	//SESSIONORDERIDANDPAGEINDEX 今日订单列表orderId session缓存 不可再用 以免导致数据错乱
	public final static String SESSIONORDERIDANDPAGEINDEX = "sessionOrderIdAndPageIndex";
	//url
	public final static String URL = "URL";
	//后台登陆用户
	public final static String STAFF = "STAFF";
	//分页数量
	public final static int COUNT_5 = 5;
	public final static int COUNT_10 = 10;
	public final static int COUNT_15 = 15;
	public final static int COUNT_20 = 20;
	//页码显示数量
	public final static int PAGENUM_5 = 5;
	public final static int PAGENUM_9 = 9;
	//商品在售情况
	public final static int STAT_UNSOLD = 0;// 未售
	public final static int STAT_SALE = 1;// 在售
	public final static int STAT_SOLD = 2;// 售完
	public final static String[] STATS = { "商品暂未出售", "商品正在出售", "商品已售完" };
	//商品配图
	public final static int IMAGE1 = 0;// 无图片
	public final static int IMAGE2 = 1;// 有图片
	//性别
	public final static String [] GENDERS = {"男","女","未知"};
	//功能树打开方式
	public final static String [] TARGETS = {"_blank","conframe"};
	//订单状态
	public final static int STAT_ORDER_1 = 0;//已下单
	public final static int STAT_ORDER_2 = 1;//运送中
	public final static int STAT_ORDER_3 = 2;//已签收
	public final static String [] STATORDERS = {"已下单","运送中","已签收"};
	//是否手动生成订单
	public final static int FLAG_ORDER_A = 0;//自动生成
	public final static int FLAG_ORDER_B = 1;//前台手动生成订单
	public final static int FLAG_ORDER_C = 2;//后台手动生成订单
	public final static String NO_ORDER_A = "A";//自动生成
	public final static String NO_ORDER_B = "B";//前台手动生成订单
	public final static String NO_ORDER_C = "C";//后台手动生成订单
	//送餐时间
	public final static String [] SENDTIMES = {"10:00 - 10:30","10:30 - 11:00","11:00 - 11:30","11:30 - 12:00","12:00 - 12:30","12:30 - 13:00","16:00 - 16:30","16:30 - 17:00","17:00 - 17:30","17:30 - 18:00","18:00 - 18:30","18:30 - 19:00","19:00 - 19:30","19:30 - 20:00"};
	//库存
	public final static int INVENTORY_FULL = -1;//满库存
	public final static int INVENTORY_EMPTY = 0;//空库存
	//更新log状态
	public final static String [] UPDATELOGSTAT = {"普通更新","修复更新","BUG更新","安全更新"};
	//一天的毫秒数
	public final static long TODAYMILLISECOND = 24 * 3600 * 1000;
	//首页问候语
	public final static String [] WELCOMELIST = {"凌晨[请注意休息]","清晨早","上午好","中午好","下午好","夜已黑","深夜[骚年洗洗睡觉吧]"};
	//staff激活状态
	public final static int STAFF_ACTIVATE = 0;//激活
	public final static int STAFF_DESTROY = 1;//注销 不可登陆
	//随机密码
	public final static char [] PASSWORDSTR = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
	//权限树显示方式
	public final static int TREE_DISPLAY_SHOW = 0;//显示
	public final static int TREE_DISPLAY_HIDDEN = 1;//不显示
	public final static String [] ROLELEVELS = {"一级","二级","三级","四级","五级"};
	
	public final static int RECOMMEND_FALSE = 0;
	public final static int RECOMMEND_TRUE = 1;
	
	public final static int RECOMMEND_COUNT = 4;
}
