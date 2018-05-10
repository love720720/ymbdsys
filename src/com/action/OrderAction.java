package com.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.constant.Constant;
import com.custom.Custom;
import com.pojo.Order;
import com.pojo.OrderHistory;
import com.pojo.OrderProduct;
import com.pojo.OrderStatistics;
import com.pojo.PageBean;
import com.pojo.Product;
import com.pojo.StaffInfo;
import com.pojo.Taste;
import com.pojo.User;
import com.service.ClassifyService;
import com.service.OrderHistoryService;
import com.service.OrderProductService;
import com.service.OrderService;
import com.service.ProductService;
import com.service.TasteService;
import com.service.UserService;
import com.util.DateUtil;
import com.util.ExcelUtil;
import com.util.StringUtil;

@SuppressWarnings("unchecked")
@Controller
public class OrderAction {

	@Autowired
	private TasteService tasteService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ClassifyService classifyService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	//用于手动添加订单时候用户名的搜索
	@RequestMapping(value = "/order/selectUserInfo",method = RequestMethod.POST)
	public void selectUserInfo(
			HttpServletResponse response,
			@RequestParam(value = "name", required = false) String name
			) throws IOException {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		StringBuilder json = new StringBuilder();
		json.append("{\"users\":[");
		if (name == null || name.length() <= 0) {
			json.append("]}");
			writer.write(json.toString());
			writer.flush();
			writer.close();
			return;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		name = StringUtil.toSqlLike(name);
		param.put("name", name);
		List<Order> list = orderService.getSelectOrderUserName(param);
		if (list == null || list.size() <= 0) {
			json.append("{\"id\":").append(0).append(",");
			json.append("\"userId\":").append(0).append(",");
			json.append("\"name\":\"").append(name).append("\",");
			json.append("\"phoneNo\":\"\",");
			json.append("\"phone\":\"\",");
			json.append("\"address\":\"\"}");
			json.append("]}");
			writer.write(json.toString());
			writer.flush();
			writer.close();
			return;
		}
		StringBuilder info = new StringBuilder();
		for (Order order : list) {
			info.append("{\"id\":").append(order.getId()).append(",");
			info.append("\"userId\":").append(order.getUserId()).append(",");
			info.append("\"name\":\"").append(order.getUserName()).append("\",");
			info.append("\"phone\":\"").append(order.getPhone()).append("\",");
			info.append("\"address\":\"").append(order.getAddress()).append("\"},");
		}
		json.append(info.subSequence(0, info.length() - 1));
		json.append("]}");
		writer.write(json.toString());
		writer.flush();
		writer.close();
		return;
	}
	
	//搜索客户订单
	@RequestMapping(value = "/order/selectOrderUserName",method = RequestMethod.POST)
	public void selectOrderUserName(
			HttpServletResponse response,
			@RequestParam(value = "name", required = false) String name
			) throws IOException {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		StringBuilder json = new StringBuilder();
		json.append("{\"userNames\":[");
		if (name == null || name.length() <= 0) {
			json.append("]}");
			writer.write(json.toString());
			writer.flush();
			writer.close();
			return;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		name = StringUtil.toSqlLike(name);
		param.put("name", name);
		List<Order> list = orderService.getSelectOrderUserName(param);
		if (list == null || list.size() == 0) {
			json.append("]}");
			writer.write(json.toString());
			writer.flush();
			writer.close();
			return;
		}
		Set<String> set = new HashSet<String>();
		for (Order order : list) {
			set.add(order.getUserName());
		}
		if(set.size() <= 0){
			json.append("]}");
			writer.write(json.toString());
			writer.flush();
			writer.close();
			return;
		}
		StringBuilder info = new StringBuilder();
		Iterator<String> iterator = set.iterator();
		int id = 0;
		while(iterator.hasNext()){
			name = iterator.next();
			info.append("{\"id\":").append(id).append(",");
			info.append("\"name\":\"").append(name).append("\"},");
			id++;
		}
		json.append(info.subSequence(0, info.length() - 1));
		json.append("]}");
		writer.write(json.toString());
		writer.flush();
		writer.close();
		return;
		
	}
	
	//搜索客户订单
	@RequestMapping(value = "/order/orderUserSearch",method = RequestMethod.POST)
	public ModelAndView orderUserSearch(
			@RequestParam(value = "name", required = false) String name
			){
		ModelAndView model = new ModelAndView("/order/order_user_search");
		if(name == null || name.length() <= 0){
			model.addObject("message","名称为空");
			return model;
		}
		List<Order> list = orderService.getOrderAsUser(StringUtil.toSql(name));
		if(list == null || list.size() <= 0){
			model.addObject("message","暂无信息");
			return model;
		}
		for (Order order : list) {
			List<Product> productList = productService.getProductAsOrder(order.getId());
			if(productList == null){
				productList = new ArrayList<Product>();
			}
			order.setProductList(productList);
		}
		model.addObject("list",list);
		return model;
	}
	
	//搜索客户订单
	@RequestMapping(value = "/order/orderUserSearch",method = RequestMethod.GET)
	public ModelAndView orderUserSearch_GET(){
		ModelAndView model = new ModelAndView("/order/order_user_search");
		return model;
	}
	
	//导出订单
	@RequestMapping(value = "/order/dwOrderExcel")
	public void dwOrderExcel(
			HttpServletResponse response,
			@RequestParam(value = "searchStartTime", required = false) String searchStartTime,
			@RequestParam(value = "searchEndTime", required = false) String searchEndTime
			) throws ParseException{
		if(searchStartTime == null || searchEndTime == null){
			return;
		}
		String name = "订单数据" + searchStartTime + "_" + searchEndTime;
		String fileName = name + ".xls";//excel导出文件名称
		searchStartTime += " 00:00:00";
		searchEndTime += " 23:59:59";
		long start = Constant.YYYYMMDDHHMMSS_FOMAT.parse(searchStartTime).getTime();
		long end = Constant.YYYYMMDDHHMMSS_FOMAT.parse(searchEndTime).getTime() + 1000;
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("searchStartTime", start);
		map.put("searchEndTime", end);
		List<Order> orderList = orderService.searchOrderList(map);
		if(orderList == null || orderList.size() <= 0){
			return;
		}
		for (Order order : orderList) {
			List<Product> productList = productService.getProductAsOrder(order.getId());
			if(productList == null){
				productList = new ArrayList<Product>();
			}
			order.setProductList(productList);
		}
		try {
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
			response.setContentType("application/msexcel");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(name);
			HSSFCellStyle style = wb.createCellStyle();
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFRow row = sheet.createRow(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			HSSFCell ce1 = row.createCell(0);
			ce1.setCellValue(name);
			ce1.setCellStyle(style);
			String[] tableHeader = {"下单时间","订单编号","客户名称","客户地址", "客户号码","订单价格","购买商品"};
			int[] clss = new int[tableHeader.length];
			for (int i = 0; i < clss.length; i++) {
				clss[i] = i;
			}
			ExcelUtil.createTableHeader(tableHeader, sheet, style, clss, 1);
			int rowNum = 2;
			for (Order order : orderList) {
				String productInfo = null;
				StringBuilder stringBuilder = new StringBuilder();
				for (Product product: order.getProductList()) {
					stringBuilder.append(product.getName()).append("-").append(product.getByNum()).append(" | ");
				}
				if(stringBuilder.length() > 0){
					productInfo = stringBuilder.substring(0, stringBuilder.length() - 3);
				}
				ArrayList<String> ls = new ArrayList<String>();
				String createTime = order.getCreateTimeInfo();
				createTime = createTime.substring(0,19);
				ls.add(createTime);
				ls.add(order.getNo());
				ls.add(order.getUserName());
				ls.add(order.getAddress());
				ls.add(order.getPhone());
				ls.add(String.valueOf(order.getPriceNows()));
				ls.add(productInfo);
				ExcelUtil.createTableRow(ls, sheet, rowNum, style, clss);
				rowNum++;
			}
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//搜索订单
	@RequestMapping(value = "/order/searchOrder",method = RequestMethod.POST)
	public ModelAndView searchOrder(
			@RequestParam(value = "searchStartTime", required = false) String searchStartTime,
			@RequestParam(value = "searchEndTime", required = false) String searchEndTime
			) throws ParseException{
		ModelAndView model = new ModelAndView("/order/order_search_excel");
		if(searchStartTime == null || searchEndTime == null){
			model.addObject("message","时间为空");
			return model;
		}
		model.addObject("searchStartTime",searchStartTime);
		model.addObject("searchEndTime",searchEndTime);
		Map<String, Long> map = new HashMap<String, Long>();
		long start = Constant.YYYYMMDDHHMMSS_FOMAT.parse(searchStartTime + " 00:00:00").getTime();
		long end = Constant.YYYYMMDDHHMMSS_FOMAT.parse(searchEndTime + " 23:59:59").getTime() + 1000;
		
		map.put("searchStartTime", start);
		map.put("searchEndTime", end);
		List<Order> list = orderService.searchOrderList(map);
		if(list == null || list.size() <= 0){
			model.addObject("message","暂无信息");
			return model;
		}
		model.addObject("list",list);
		return model;
	}
	
	//搜索订单
	@RequestMapping(value = "/order/orderSearchExcel",method = RequestMethod.GET)
	public ModelAndView orderSearchExcel_GET(){
		String searchStartTime = DateUtil.getToday();
		String searchEndTime = searchStartTime;
		ModelAndView model = new ModelAndView("/order/order_search_excel");
		model.addObject("searchStartTime", searchStartTime);
		model.addObject("searchEndTime", searchEndTime);
		return model;
	}
	
	//统计
	@RequestMapping(value = "/order/orderProductCountPrice",method = RequestMethod.GET)
	public ModelAndView orderProductCountAndPrice(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String strPageIndex,//统计的当前页码
			@RequestParam(value = "ids", required = false) String ids,//当前页统计的id 暂未加入session
			@RequestParam(value = "notIds", required = false) String notIds//当前页未统计id
			) {
		int pageIndex = 1;
		if(strPageIndex != null){
			pageIndex = StringUtil.toInt(strPageIndex);
		}
		pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		ModelAndView model = new ModelAndView("/order/order_product_count_price");
		HttpSession session = request.getSession();
		Map<Integer, Integer> orderIdAndPageIndex = new HashMap<Integer, Integer>();//存放所有选中的order id 和 当前页码
		Object sessionOrderIdAndPageIndex = session.getAttribute(Constant.SESSIONORDERIDANDPAGEINDEX);
		if(sessionOrderIdAndPageIndex != null){
			orderIdAndPageIndex = (HashMap<Integer, Integer>)sessionOrderIdAndPageIndex;
		}
		if(notIds != null){
			String[] split = notIds.split(",");
			for (int i = 0; i < split.length; i++) {
				int id = StringUtil.toInt(split[i]);
				if(id > 0){
					orderIdAndPageIndex.remove(id);//移除已存在的id
				}
			}
		}
		Set<Integer> keySet = new HashSet<Integer>();
		keySet.addAll(orderIdAndPageIndex.keySet());
		Set<Integer> noRepeatProductCount = new HashSet<Integer>();
		float priceNows = 0.0F;
		int productCount = 0;
		int byNums = 0;
		List<Order> orderList = new ArrayList<Order>();
		if(ids != null){
			String[] split = ids.split(",");
			for (int i = 0; i < split.length; i++) {
				int id = StringUtil.toInt(split[i]);
				if(id > 0){
					keySet.add(id);//添加当前页的id
				}
			}
		}
		//统计完成 销毁session 并且重置页面id
		session.removeAttribute(Constant.SESSIONORDERIDANDPAGEINDEX);
		ids = "";
		for (Integer orderId : keySet) {
			Order order = orderService.getOrder(orderId);
			if(order != null){
				List<Product> productList = productService.getProductAsOrder(order.getId());
				if(productList == null){
					productList = new ArrayList<Product>();
				}
				int size = productList.size();
				productCount += size;
				priceNows += order.getPriceNows();
				int byNum = 0;
				for (Product product : productList) {
					byNum += product.getByNum();
					noRepeatProductCount.add(product.getId());
				}
				byNums += byNum;
				order.setByNums(byNum);
				order.setProductCount(size);
				order.setProductList(productList);
				orderList.add(order);
				ids += orderId +",";
			}
		}
		if(ids.length() > 0){
			ids = ids.substring(0,ids.length() - 1);
		}else{
			ids = "0";
		}
		List<OrderStatistics> orderStatisticsList = orderService.getProductStatistics(ids);
		model.addObject("noRepeatProductCount",noRepeatProductCount.size());
		model.addObject("orderStatisticsList",orderStatisticsList);
		model.addObject("productCount",productCount);
		model.addObject("priceNows",priceNows);
		model.addObject("orderList",orderList);
		model.addObject("byNums",byNums);
		return model;
	}
	
	//手动添加订单
	@RequestMapping(value = "/order/manually",method = RequestMethod.GET)
	public ModelAndView orderManually(@RequestParam(value = "id", required = false) String strId) {
		String productIds = "";
		ModelAndView model = new ModelAndView("/order/order_manually");
		List<Product> productList = new ArrayList<Product>();
		int id = StringUtil.toInt(strId);
		if(id > 0){
			Order order = orderService.getOrder(id);
			if(order != null){
				int userId = order.getUserId();
				if(userId > 0){
					User user = userService.getUser(order.getUserId());
					model.addObject("user",user);
				}
				
				productList = productService.getProductAsOrder(order.getId());
				for (Product product : productList) {
					productIds += product.getId() + ",";
				}
				model.addObject("order",order);
				model.addObject("productIds",productIds);
				model.addObject("productList",productList);
			}
		}
		if(productIds.length() > 0){
			productIds = productIds.substring(0,productIds.length() - 1);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stat", Constant.STAT_SALE);
		map.put("shelvesTime", DateUtil.getTodayLong());
		

		List<Product> todayProductList = productService.productTodayShelvesList(map);
		for (int i = todayProductList.size() -1; i >= 0; i--) {
			Product todayProduct = todayProductList.get(i);
			for (Product product : productList) {
				if(todayProduct.getId() == product.getId()){
					todayProductList.remove(todayProduct);
				}
			}
		}
		List<Taste> tasteList = tasteService.tasteList();
		model.addObject("tasteList",tasteList);
		model.addObject("sendTimes",Constant.SENDTIMES);
		model.addObject("todayProductList",todayProductList);
		return model;
	}
	
	//手动添加订单
	@RequestMapping(value = "/order/orderManually",method = RequestMethod.POST)
	public void manuallyOrder(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "userId", required = false) String strUserId,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "sendTime", required = false) String sendTime,
			@RequestParam(value = "taste", required = false) String taste,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "idByNum", required = false) String idByNum//前台页面 传入购买的商品id与当前购买商品的数量
			) throws IOException {
		PrintWriter out = response.getWriter();
		if(address == null || address.length() <= 0){
			out.print(false);
			return;
		}
		if(idByNum == null || idByNum.length() <= 0){
			out.print(false);
			return;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();//存放 选择的商品 以及 商品个数 map用于去除重复商品
		String[] split = idByNum.split(",");
		for (String str : split) {
			String[] id_byNum = str.split("_");
			int productId = StringUtil.toInt(id_byNum[0]);
			int byNum = StringUtil.toInt(id_byNum[1]);
			if(productId <= 0 || byNum <= 0){
				continue;
			}
			Integer num = map.get(productId);
			if(num != null && num > 0){
				byNum += num;
			}
			map.put(productId, byNum);
			
		}
		Map<Integer,Float> priceNowMap = new HashMap<Integer,Float>();
		Map<Integer,Float> priceOldMap = new HashMap<Integer,Float>();
		Map<Integer,Integer> productByNumMap = new HashMap<Integer,Integer>();
		
		float priceOlds = 0.0F;
		float priceNows = 0.0F;
		Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, Integer> next = iterator.next();
			Integer productId = next.getKey();
			Integer byNum = next.getValue();
			Product product = productService.getProduct(productId);
			if(product == null){
				continue;
			}
			
			float priceOld = product.getPriceOld();
			float priceNow = product.getPriceNow();
			for (int i = 0; i < byNum; i++) {
				priceOlds += priceOld;
				priceNows += priceNow;
			}
			priceOldMap.put(productId, priceOld);
			priceNowMap.put(productId, priceNow);
			productByNumMap.put(productId, byNum);
		}
		
		long millis = System.currentTimeMillis();
		float discount = priceNows / priceOlds * 100;
		BigDecimal bigDecimal = new BigDecimal(discount);
		discount = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();//折扣 保留2位小数
		Order order = new Order();
		order.setUserName(userName);
		order.setPhone(phone);
		order.setAddress(address);
		order.setSendTime(sendTime);
		order.setPriceOlds(priceOlds);
		order.setPriceNows(priceNows);
		order.setDiscount(discount);
		order.setTaste(taste);
		order.setRemark(remark);
		order.setFlag(Constant.FLAG_ORDER_C);
		order.setStat(Constant.STAT_ORDER_1);
		order.setUpdateTime(millis);
		int orderId = 0;//用于更新中间表信息
		int id = StringUtil.toInt(strId);
		
		User user = null;
		int userId = StringUtil.toInt(strUserId);
		if(userId > 0){
			user = userService.getUser(userId);
			if(user != null){
				order.setUserId(userId);
			}
		}
		if(id > 0){
			HttpSession session = request.getSession();
			StaffInfo staffInfo = (StaffInfo)session.getAttribute(Constant.STAFF);
			String record = "最后修改:" + staffInfo.getName();
			order.setId(id);
			order.setRecord(record);
			int i = orderService.updateOrder(order);
			if(i <= 0){
				out.print(false);
				return;
			}
			orderId = id;
			
			if(user != null){
				OrderHistory orderHistory = new OrderHistory();
				orderHistory.setOrderId(orderId);
				orderHistory.setUserId(userId);
				orderHistory.setPriceNows(priceNows);
				orderHistory.setRemark(remark);
				orderHistory.setUserName(userName);
				orderHistoryService.updateOrderHistory(orderHistory);
			}
			
		}else{
			order.setCreateTime(millis);
			Order orderTemp = orderService.getLastOrder(DateUtil.getTodayLong());
			String orderNo = null;
			if(orderTemp != null)
				orderNo = orderTemp.getNo();

			String no = Custom.getOrderNo(orderNo,Constant.FLAG_ORDER_C);
			orderTemp = orderService.getOrderToNo(no);
			if(orderTemp != null && orderTemp.getNo().equals(no)){
				out.print(false);
				return;
			}
			order.setNo(no);
			int i = orderService.insertOrder(order);
			if(i <= 0){
				out.print(false);
				return;
			}
			orderId = order.getId();
			
			if(user != null){
				OrderHistory orderHistory = new OrderHistory();
				orderHistory.setOrderId(orderId);
				orderHistory.setUserId(userId);
				orderHistory.setCreateTime(millis);
				orderHistory.setNo(no);
				orderHistory.setPriceNows(priceNows);
				orderHistory.setRemark(remark);
				orderHistory.setUserName(userName);
				orderHistoryService.insertOrderHistory(orderHistory);
			}
		}
		orderProductService.deleteOrderProductAsOrder(orderId);//清空之前购买数据
		iterator = productByNumMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, Integer> next = iterator.next();
			Integer productId = next.getKey();
			Integer byNum = next.getValue();
			
			float priceNow = priceNowMap.get(productId);
			float priceOld = priceOldMap.get(productId);
			
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(orderId);
			orderProduct.setProductId(productId);
			orderProduct.setByNum(byNum);
			orderProduct.setPriceNow(priceNow);
			orderProduct.setPriceNows(priceNow * byNum);
			orderProduct.setPriceOld(priceOld);
			orderProduct.setPriceOlds(priceOld * byNum);
			int orderProductId = orderProductService.insertOrderProduct(orderProduct);
			if(orderProductId <= 0){
				//当order id出错删除可能存在的信息
				orderService.deleteOrder(orderId);
				orderHistoryService.deleteOrderHistory(orderId);
				orderProductService.deleteOrderProductAsOrder(orderId);
				out.print(false);
				return;
			}
		}
		out.print(true);
		return;
	}
	
	//当前所有订单下商品销售统计
	@RequestMapping(value = "/order/dwExcel")
	public void dwExcel(
			HttpServletResponse response,
			@RequestParam(value = "searchStartTime", required = false) String searchStartTime,
			@RequestParam(value = "searchEndTime", required = false) String searchEndTime
			) throws ParseException{
		if(searchStartTime == null || searchEndTime == null){
			return;
		}
		String name = "订单数据统计" + searchStartTime + "_" + searchEndTime;
		String fileName = name + ".xls";//excel导出文件名称
		searchStartTime += " 00:00:00";
		searchEndTime += " 23:59:59";
		long start = Constant.YYYYMMDDHHMMSS_FOMAT.parse(searchStartTime).getTime();
		long end = Constant.YYYYMMDDHHMMSS_FOMAT.parse(searchEndTime).getTime() + 1000;
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("startTime", start);
		map.put("endTime", end);
		List<OrderStatistics> orderStatisticsList = orderService.getOrderStatisticsDetail(map);
		int productCounts = 0;//订单商品总数
		float priceNows = 0.0F;//订单商品总金额
		for (OrderStatistics orderStatistics : orderStatisticsList) {
			productCounts += orderStatistics.getProductCount();
			priceNows += orderStatistics.getPriceNows();
		}
		try {
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
			response.setContentType("application/msexcel");
			OutputStream out = response.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(name);
			HSSFCellStyle style = wb.createCellStyle();
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFRow row = sheet.createRow(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			HSSFCell ce1 = row.createCell(0);
			ce1.setCellValue(name);
			ce1.setCellStyle(style);
			String[] tableHeader = { "商品编号","商品名称","订单是商品个数", "订单是商品金额"};
			int[] clss = new int[tableHeader.length];
			for (int i = 0; i < clss.length; i++) {
				clss[i] = i;
			}
			ExcelUtil.createTableHeader(tableHeader, sheet, style, clss, 1);
			int rowNum = 2;
			if(orderStatisticsList.size() > 0){
				ArrayList<String> total = new ArrayList<String>();
				total.add("订单商品总数");
				total.add(String.valueOf(productCounts));
				total.add("订单商品总金额");
				total.add(String.valueOf(priceNows));
				ExcelUtil.createTableRow(total, sheet, rowNum, style, clss);
				rowNum++;
				for (OrderStatistics orderStatistics : orderStatisticsList) {
					ArrayList<String> ls = new ArrayList<String>();
					ls.add(orderStatistics.getProductNo());
					ls.add(orderStatistics.getProductName());
					ls.add(String.valueOf(orderStatistics.getProductCount()));
					ls.add(String.valueOf(orderStatistics.getPriceNows()));
					ExcelUtil.createTableRow(ls, sheet, rowNum, style, clss);
					rowNum++;
				}
			}
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	//当前所有订单下商品销售统计
	@RequestMapping(value = "/order/orderStatisticsDetail")
	public ModelAndView orderStatisticsDetail(
			@RequestParam(value = "searchStartTime", required = false) String searchStartTime,
			@RequestParam(value = "searchEndTime", required = false) String searchEndTime,
			@RequestParam(value = "param", required = false) String strParam
			) throws ParseException {
		ModelAndView model = new ModelAndView("/order/order_statistics_detail");
		int param = StringUtil.toInt(strParam);
		Map<String, Long> map = new HashMap<String, Long>();
		long start = 0L;
		long end = 0L;
		switch (param) {
			case 0://今日
				start = DateUtil.getTodayLong();
				end = DateUtil.getTomorrowLong();
				break;
			case 1://昨日
				start = DateUtil.getYesterdayLong();
				end = DateUtil.getTodayLong();
				break;
			case 2://本周
				start = DateUtil.getWeekdayFirstLong();
				end = DateUtil.getTomorrowLong();
				break;
			case 3://本月
				start = DateUtil.getMonthFirstLong();
				end = DateUtil.getTomorrowLong();
				break;
			case 4://按日期
				start = Constant.YYYYMMDDHHMMSS_FOMAT.parse(searchStartTime + " 00:00:00").getTime();
				end = Constant.YYYYMMDDHHMMSS_FOMAT.parse(searchEndTime + " 23:59:59").getTime() + 1000;//增加1秒 为了到第二天的凌晨 查询到第二天的0点结束
				break;
			default://今日
				start = DateUtil.getTodayLong();
				end = DateUtil.getTomorrowLong();
				break;
		}
		map.put("startTime", start);
		map.put("endTime", end);
		List<OrderStatistics> orderStatisticsList = orderService.getOrderStatisticsDetail(map);
		int productCounts = 0;//订单商品总数
		float priceNows = 0.0F;//订单商品总金额
		for (OrderStatistics orderStatistics : orderStatisticsList) {
			productCounts += orderStatistics.getProductCount();
			priceNows += orderStatistics.getPriceNows();
		}
		searchStartTime = Constant.YYYYMMDD_FOMAT.format(new Date(start));
		searchEndTime = Constant.YYYYMMDD_FOMAT.format(new Date(end - 1000));
		//查询 导出时间
		model.addObject("searchStartTime",searchStartTime);
		model.addObject("searchEndTime",searchEndTime);
		model.addObject("priceNows",priceNows);
		model.addObject("productCounts",productCounts);
		model.addObject("orderStatisticsList",orderStatisticsList);
		return model;
	}
	
	//session缓存清空
	@RequestMapping(value = "/order/removeOrderIdCaChe")
	public void removeOrderIdCaChe(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.SESSIONORDERIDANDPAGEINDEX);
		out.print(true);
	}
	
	//今日订单
	@RequestMapping(value = "/order/orderToday")
	public ModelAndView orderToday(
			HttpServletRequest request,
			@RequestParam(value = "beforePageIndex", required = false) String strBeforePageIndex,//上一页
			@RequestParam(value = "pageIndex", required = false) String strPageIndex,//当前页
			@RequestParam(value = "ids", required = false) String ids,
			@RequestParam(value = "notIds", required = false) String notIds
			) {
		int beforePageIndex = 1;
		int pageIndex = 1;
		if(strBeforePageIndex != null){
			beforePageIndex = StringUtil.toInt(strBeforePageIndex);
		}
		beforePageIndex = beforePageIndex <= 0 ? 1 : beforePageIndex;
		if(strPageIndex != null){
			pageIndex = StringUtil.toInt(strPageIndex);
		}
		pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		
		ModelAndView model = new ModelAndView("/order/order_today");
		HttpSession session = request.getSession();
		Map<Integer, Integer> orderIdAndPageIndex = new HashMap<Integer, Integer>();//存放所有选中的order id 和 当前页码
		Object sessionOrderIdAndPageIndex = session.getAttribute(Constant.SESSIONORDERIDANDPAGEINDEX);
		if(sessionOrderIdAndPageIndex != null){
			orderIdAndPageIndex = (HashMap<Integer, Integer>)sessionOrderIdAndPageIndex;
		}
		if(notIds != null){
			String[] split = notIds.split(",");
			for (int i = 0; i < split.length; i++) {
				int orderId = StringUtil.toInt(split[i]);
				if(orderId > 0){
					orderIdAndPageIndex.remove(orderId);
				}
			}
		}
		if(ids != null && ids.length() > 0){
			String[] split = ids.split(",");
			for (int i = 0; i < split.length; i++) {
				int orderId = StringUtil.toInt(split[i]);
				if(orderId > 0){
					//保存上一页已经勾选的记录
					orderIdAndPageIndex.put(orderId, beforePageIndex);
				}
			}
		}
		if(orderIdAndPageIndex != null){
			List<Integer> orderIdList = Custom.getMapKey(orderIdAndPageIndex, pageIndex);//存放页面选中的order id
			model.addObject("orderIdList", orderIdList);
		}
		session.setAttribute(Constant.SESSIONORDERIDANDPAGEINDEX, orderIdAndPageIndex);
		long createTime = DateUtil.getTodayLong();
		int totalCount = orderService.getOrderTodayCount(createTime);
		PageBean pageBean = Custom.getPageBean(totalCount, Constant.COUNT_5,pageIndex,Constant.PAGENUM_9,"orderToday");
		pageBean.setCreateTime(createTime);
		List<Order> list = orderService.orderTodayList(pageBean);
		for (Order order : list) {
			List<Product> productList = productService.getProductAsOrder(order.getId());
			if(productList == null){
				productList = new ArrayList<Product>();
			}
			order.setProductList(productList);
		}
		model.addObject("beforePageIndex",beforePageIndex);
		model.addObject("pageBean",pageBean);
		model.addObject("list",list);
		return model;
	}
	
	//所有订单列表
	@RequestMapping(value = "/order/orderList")
	public ModelAndView orderList(@RequestParam(value = "pageIndex", required = false) String strPageIndex) {
		int pageIndex = 1;
		if(strPageIndex != null){
			pageIndex = StringUtil.toInt(strPageIndex);
		}
		pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		int totalCount = orderService.getCount();
		PageBean pageBean = Custom.getPageBean(totalCount, Constant.COUNT_10,pageIndex,Constant.PAGENUM_9,"orderList");
		List<Order> list = orderService.orderList(pageBean);
		ModelAndView model = new ModelAndView("/order/order_list");
		model.addObject("pageBean",pageBean);
		model.addObject("list",list);
		return model;
	}
	
	//明细
	@RequestMapping(value = "/order/detailOrder",method = RequestMethod.GET)
	public ModelAndView detailOrder(@RequestParam(value = "id", required = false) String strId) {
		ModelAndView model = new ModelAndView("/order/order_detail");
		if(strId != null){
			int id = StringUtil.toInt(strId);
			if(id > 0){
				Order order = orderService.getOrder(id);
				if(order != null){
					List<Product> productList = productService.getProductAsOrder(order.getId());
					model.addObject("order",order);
					model.addObject("productList",productList);
				}
			}
		}
		return model;
	}
	
	//打印次数
	@RequestMapping(value = "/order/editPrintNum",method = RequestMethod.POST)
	public void editPrintNum(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "flag", required = false) String strFlag,//批量 或者 单个
			@RequestParam(value = "ids", required = false) String ids
			) throws IOException {
		int flag = 1;
		if(strFlag != null){
			flag = StringUtil.toInt(strFlag);
		}
		flag = flag <= 0 ? 0 : flag;
		HttpSession session = request.getSession();
		Set<Integer> keySet = new HashSet<Integer>();
		PrintWriter out = response.getWriter();
		switch (flag) {
			//批量更新 直接更新session存在的id
			case 0:
				Map<Integer, Integer> orderIdAndPageIndex = new HashMap<Integer, Integer>();//存放所有选中的order id 和 当前页码
				Object sessionOrderIdAndPageIndex = session.getAttribute(Constant.SESSIONORDERIDANDPAGEINDEX);
				if(sessionOrderIdAndPageIndex == null){
					out.print(true);
					return;
				}
				orderIdAndPageIndex = (HashMap<Integer, Integer>)sessionOrderIdAndPageIndex;
				keySet.addAll(orderIdAndPageIndex.keySet());
				break;
				//单个更新 直接更新传入的ids
			case 1:
				int id = StringUtil.toInt(ids);
				if(id <= 0){
					out.print(false);
					return;
				}
				keySet.add(id);
				break;
			default:
				out.print(false);
				return;
		}
		for (Integer orderId : keySet) {
			orderService.updateOrderPrintNum(orderId);
		}
		session.removeAttribute(Constant.SESSIONORDERIDANDPAGEINDEX);
		out.print(true);
		return;
	}
	
	//删除订单
	@RequestMapping(value = "/order/deleteOrder",method = RequestMethod.GET)
	public String deleteOrder(
			@RequestParam(value = "form", required = false) String form,
			@RequestParam(value = "id", required = false) String strId
			) {
		if(form == null || form.length() <= 0){
			form = "list";
		}
		form = form.equals("list") ? "redirect:/order/orderList.htm" : "redirect:/order/orderToday.htm";
		int id = StringUtil.toInt(strId);
		if(id <= 0){
			return form;
		}
		orderService.deleteOrder(id);
		orderProductService.deleteOrderProductAsOrder(id);
		return form;
	}
	
	//打印
	@RequestMapping(value = "/order/printAll",method = RequestMethod.POST)
	public void printAll(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "ids", required = false) String ids,
			@RequestParam(value = "notIds", required = false) String notIds,
			@RequestParam(value = "flag", required = false) String strFlag,
			@RequestParam(value = "pageIndex", required = false) String strPageIndex
			) throws IOException {
		int flag = 1;
		if(strFlag != null){
			flag = StringUtil.toInt(strFlag);
		}
		int pageIndex = 1;
		if(strPageIndex != null){
			pageIndex = StringUtil.toInt(strPageIndex);
		}
		pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		response.setCharacterEncoding("UTF-8");
		Set<Integer> keySet = new HashSet<Integer>();
		List<Order> orderList = new ArrayList<Order>();
		StringBuilder orders = new StringBuilder();
		orders.append("{\"orders\":[");
		PrintWriter out = response.getWriter();
		switch (flag) {
			case 0:
				HttpSession session = request.getSession();
				Map<Integer, Integer> orderIdAndPageIndex = new HashMap<Integer, Integer>();//存放所有选中的order id 和 当前页码
				Object sessionOrderIdAndPageIndex = session.getAttribute(Constant.SESSIONORDERIDANDPAGEINDEX);
				if(sessionOrderIdAndPageIndex != null){
					orderIdAndPageIndex = (HashMap<Integer, Integer>)sessionOrderIdAndPageIndex;
				}
				if(notIds != null){
					String[] split = notIds.split(",");
					for (int i = 0; i < split.length; i++) {
						int id = StringUtil.toInt(split[i]);
						if(id > 0){
							orderIdAndPageIndex.remove(id);
						}
					}
				}
				keySet.addAll(orderIdAndPageIndex.keySet());
				if(ids != null){
					String[] split = ids.split(",");
					for (int i = 0; i < split.length; i++) {
						int id = StringUtil.toInt(split[i]);
						if(id > 0){
							keySet.add(id);
							orderIdAndPageIndex.put(id, pageIndex);
						}
					}
					session.setAttribute(Constant.SESSIONORDERIDANDPAGEINDEX, orderIdAndPageIndex);
				}
				break;
			case 1:
				int id = StringUtil.toInt(ids);
				if(id <= 0){
					orders.append("]}");
					out.print(orders);
					return;
				}
				keySet.add(id);
				break;
			default:
				orders.append("]}");
				out.print(orders);
				return;
		}
		for (Integer orderId : keySet) {
			Order order = orderService.getOrder(orderId);
			if(order != null){
				List<Product> productList = productService.getProductAsOrder(order.getId());
				order.setProductList(productList);
				orderList.add(order);
			}
		}
		if(orderList.size() <= 0){
			orders.append("]}");
			out.print(orders);
			return;
		}
		for (Order order : orderList) {
			List<Product> productList = order.getProductList();
			String address = order.getAddress();
			if(address != null && address.length() > 0){
				address = address.replace("\n", "");
			}
			String remark = order.getRemark();
			if(remark != null && remark.length() > 0){
				remark = remark.replace("\n", "");
			}
			orders.append("{\"userName\":\"").append(order.getUserName()).append("\",");
			orders.append("\"phone\":\"").append(order.getPhone()).append("\",");
			orders.append("\"address\":\"").append(address).append("\",");
			orders.append("\"priceNows\":").append(order.getPriceNows()).append(",");
			orders.append("\"remark\":\"").append(remark).append("\",");
			StringBuilder products = new StringBuilder();
			products.append("\"products\":[");
			for (Product product : productList) {
				products.append("{\"name\":\"").append(product.getName()).append("\",");
				products.append("\"priceNow\":").append(product.getPriceNow()).append(",");
				products.append("\"byNum\":").append(product.getByNum());
				products.append("},");
			}
			if(productList != null && productList.size() > 0){
				CharSequence subSequence = products.subSequence(0, products.length() - 1);
				orders.append(subSequence);
			}
			orders.append("]},");
		}
		CharSequence subSequence = orders.subSequence(0, orders.length() - 1);
		orders = new StringBuilder();
		orders.append(subSequence).append("]}");
		out.print(orders);
		return;
	}
}