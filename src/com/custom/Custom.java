package com.custom;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.constant.Constant;
import com.pojo.PageBean;
import com.util.DateUtil;
import com.util.MailUtil;

/*
 * 定义方法
 */
public class Custom {
	
	//随机密码发送
	public static synchronized boolean sendStaffPassword(String email,String password){
		return MailUtil.sendStaffPassword(email,password);
	}
	
	//随机密码
	public static synchronized String getPassword(){
		char [] passwordStr = Constant.PASSWORDSTR;
		final int  maxNum = passwordStr.length - 1;
		int i;  //生成的随机数
		int count = 0; //生成的密码的长度
		int len = 10;//密码长度
		StringBuilder password = new StringBuilder("");
		Random r = new Random();
		while(count < len){
			//生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum));//生成的数最大为36-1
			if (i >= 0 && i < passwordStr.length) {
				password.append(passwordStr[i]);
			    count ++;
			}
		}
		return password.toString();
	}
	
	//商品编号生成
	public static synchronized String getProductNo(String productNo){
		String init = "1000000000";
		if(productNo == null || productNo.length() != 10){
			productNo = init;
		}
		productNo = productNo.substring(6, productNo.length());
        long productNoLong = Long.parseLong(productNo);
        productNoLong++;
        productNo = String.format("%04d", productNoLong);
	    String dateStr = Constant.YYMMDDFOMAT.format(new Date());
	    productNo = dateStr + productNo;
		return productNo;
	}
	
	//订单编号生成
	public static synchronized String getOrderNo(String orderNo,int flag){
		String init = "100000000000";
		if(orderNo == null || orderNo.length() != 12){
			orderNo = init;
		}
		orderNo = orderNo.substring(10, orderNo.length());
		long orderNoLong = Long.parseLong(orderNo);
		orderNoLong++;
		orderNo = String.format("%05d", orderNoLong);
		String dateStr = Constant.YYMMDDFOMAT.format(new Date());
		switch (flag) {
			case Constant.FLAG_ORDER_A:
				orderNo = Constant.NO_ORDER_A + dateStr + orderNo;
				break;
			case Constant.FLAG_ORDER_B:
				orderNo = Constant.NO_ORDER_B + dateStr + orderNo;
				break;
			case Constant.FLAG_ORDER_C:
				orderNo = Constant.NO_ORDER_C + dateStr + orderNo;
				break;
			}
		return orderNo;
	}
	
	//文件上传重新命名
	public static String getFileName(String fileName){
		if(fileName == null || fileName.length() <= 0){
			return null;
		}
		fileName = fileName.trim();
		int index = fileName.lastIndexOf(".");
		if(index == -1){
			return null;
		}
		String suffix = fileName.substring(index,fileName.length());
		fileName = fileName.substring(0,index);
		fileName = fileName + System.currentTimeMillis() + suffix;
		return fileName;
	}
	
	//文件上传存储地址  商品说明图片
	public static String getProductFilePath(HttpServletRequest request,String fileName){
		StringBuilder path = getProductFilePath().append(fileName);
		String strPath = path.toString();
		File file = new File(strPath);
		if(!file.exists()){
			//读取项目内图片
			ServletContext servletContext = request.getSession().getServletContext();
		    String configFileString = servletContext.getRealPath("\\images\\404.jpg");
		    strPath = configFileString;
		}
		return strPath;
	}
	
	//文件上传存储地址 上级目录
	public static StringBuilder getProductFilePath(){
		StringBuilder path = new StringBuilder();
		path.append("F:\\web\\product\\");
		return path;
	}
	
	/*
	 * 分页
	 * totalPageCount 总页数
	 * totalCount 总条数
	 * count 每页显示多少条数
	 * pageIndex 当前页
	 * pageNum 显示页码 默认分页为9个页码
	 * fromAction 跳转action
	 */
	public static PageBean getPageBean(int totalCount,int count,int pageIndex,int pageNum,String fromAction){
		pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		pageNum = pageNum <= 0 ? 9 : pageNum;
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setCount(count);
		pageBean.setIndex((pageIndex - 1) * count);
		pageBean.setTotalCount(totalCount);
		pageBean.setFromAction(fromAction);
		int totalPageCount = pageBean.getTotalPageCount();
		int firstIndex = (pageIndex <= pageNum / 2 + 1 ? 1 : (pageIndex-pageNum / 2));
		int lastIndex = (firstIndex + pageNum-1 >= totalPageCount ? totalPageCount : firstIndex + pageNum - 1);
		int temp = lastIndex - firstIndex + 1;
		temp = temp == pageNum ? 0 : pageNum - temp;
		temp = temp < 0 ? 0 : temp;
		firstIndex = firstIndex - temp;
		firstIndex = firstIndex <= 0 ? 1 : firstIndex;
		lastIndex = lastIndex > totalPageCount ? totalPageCount : lastIndex;
		firstIndex = firstIndex > lastIndex ? lastIndex : firstIndex;
		pageBean.setFirstIndex(firstIndex);
		pageBean.setLastIndex(lastIndex);
		return pageBean;
	}
	
	//文件删除
	public static void delFile(String filePath,String fileName){
		if(fileName != null && fileName.length() > 0){
			File file = new File(filePath + fileName);
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	//判断当前时间处于当日的什么时段
	public static String getWelcome(){
		String welCome;
		int hour = DateUtil.getHour();
		if(hour > 0 && hour <= 4){
			welCome = Constant.WELCOMELIST[0];
		}else if(hour > 4 && hour <= 6){
			welCome = Constant.WELCOMELIST[1];
		}else if(hour > 6 && hour <= 11){
			welCome = Constant.WELCOMELIST[2];
		}else if(hour > 11 && hour <= 13){
			welCome = Constant.WELCOMELIST[3];
		}else if(hour > 13 && hour <= 18){
			welCome = Constant.WELCOMELIST[4];
		}else if(hour > 18 && hour <= 22){
			welCome = Constant.WELCOMELIST[5];
		}else{
			welCome = Constant.WELCOMELIST[6];
		}
		return welCome;
	}
	
	//BUG email发送
	public static boolean sendBUGMail(String content){
		return MailUtil.send(content);
	}
	
	//取出map中value相同的key 用于今日订单勾选记录 value为页码 key为order id
	public static List<Integer> getMapKey(Map<Integer, Integer> map,Integer value){
		List<Integer> mapKey = new ArrayList<Integer>();
		Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, Integer> next = iterator.next();
			Integer mapValue = next.getValue();
			if(mapValue == value){
				mapKey.add(next.getKey());
			}
		}
		return mapKey;
	}
}