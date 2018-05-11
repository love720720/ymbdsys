package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.constant.Constant;
import com.custom.Custom;
import com.pojo.Classify;
import com.pojo.PageBean;
import com.pojo.Product;
import com.pojo.StaffInfo;
import com.service.ClassifyService;
import com.service.ProductService;
import com.util.DateUtil;
import com.util.StringUtil;


@Controller
public class ProductAction {

	@Autowired
	private ProductService productService;
	@Autowired
	private ClassifyService classifyService;
	
	@RequestMapping(value = "/product/recommend",method = RequestMethod.POST)
	public void recommend(
			HttpServletResponse response,
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "isTrue", required = false) String strIstrue
			) throws IOException{
		try {
			PrintWriter out = response.getWriter();
			StringBuilder json = new StringBuilder();
			json.append("{\"isTrue\":");
			String tips = "参数错误";

			int id = StringUtil.toInt(strId);
			boolean isTrue = StringUtil.toBoolean(strIstrue);
			if(id <= 0){
				json.append(false).append(",\"tips\":\"").append(tips).append("\"}");
				out.print(json);
				return;
			}
			tips = "取消推荐成功";
			int recommend = Constant.RECOMMEND_FALSE;//取消推荐
			if(isTrue){
				tips = "推荐成功";
				recommend = Constant.RECOMMEND_TRUE;
				int count = productService.recommendProductCount(recommend);
				if(count >= Constant.RECOMMEND_COUNT){
					tips = "系统设置只能推荐" + Constant.RECOMMEND_COUNT + "个商品";
					json.append(false).append(",\"tips\":\"").append(tips).append("\"}");
					out.print(json);
					return;
				}
			}

			Product product = new Product();
			product.setId(id);
			product.setRecommend(recommend);

			int i = productService.recommendProduct(product);
			if(i > 0){
				json.append(true).append(",\"tips\":\"").append(tips).append("\"}");
				out.print(json);
				return;
			}

			tips = "系统操作失败 请返回重试";
			json.append(false).append(",\"tips\":\"").append(tips).append("\"}");
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/product/recommendList")
	public ModelAndView recommendList(){
		ModelAndView model = new ModelAndView("/product/recommend_list");
		try {
			List<Product> recommendProductList = productService.recommendProductList(Constant.RECOMMEND_TRUE);
			model.addObject("recommendProductList", recommendProductList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/product/searchProduct",method = RequestMethod.POST)
	public ModelAndView searchProduct(@RequestParam(value = "id", required = false) String strId){
		ModelAndView model = new ModelAndView("/product/product_search");
		try {
			int id = StringUtil.toInt(strId);
			if(id > 0){
				Product product = productService.getProduct(id);
				model.addObject("product", product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/product/searchProduct",method = RequestMethod.GET)
	public ModelAndView searchProduct_GET(){
		ModelAndView model = new ModelAndView("/product/product_search");
		return model;
	}
	
	@RequestMapping(value = "/product/getProductAsName",method = RequestMethod.POST)
	public void getProductAsName(
			HttpServletResponse response,
			@RequestParam(value = "name", required = false) String name
			) {
		try {
			response.setContentType("text/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			StringBuilder json = new StringBuilder();
			json.append("{\"products\":[");
			name = StringUtil.toSql(name);
			if(name == null || name.length() <= 0){
				json.append("]}");
				out.print(json);
				out.flush();
				out.close();
				return;
			}
			List<Product> productList = productService.getProductAsName(name);
			if(productList == null || productList.size() <= 0){
				json.append("]}");
				out.print(json);
				out.flush();
				out.close();
				return;
			}
			StringBuilder info = new StringBuilder();
			for (Product product : productList) {
				info.append("{\"id\":").append(product.getId()).append(",");
				info.append("\"no\":\"").append(product.getNo()).append("\",");
				info.append("\"classifyName\":\"").append(product.getClassifyName()).append("\",");
				info.append("\"name\":\"").append(product.getName()).append("\",");
				info.append("\"createTimeInfo\":\"").append(product.getCreateTimeInfo()).append("\",");
				info.append("\"shelvesTimeInfo\":\"").append(product.getShelvesTimeInfo()).append("\",");
				info.append("\"statInfo\":\"").append(product.getStatInfo()).append("\",");
				info.append("\"priceOld\":").append(product.getPriceOld()).append(",");
				info.append("\"priceNow\":").append(product.getPriceNow()).append(",");
				info.append("\"inventoryInfo\":\"").append(product.getInventoryInfo()).append("\"},");
			}
			json.append(info.subSequence(0, info.length() - 1));
			json.append("]}");
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/product/getProduct",method = RequestMethod.POST)
	public void getProduct(
			HttpServletResponse response,
			@RequestParam(value = "id", required = false) String strId
			) throws IOException {
		try {
			response.setContentType("text/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			StringBuilder json = new StringBuilder();
			json.append("{\"isTrue\":");
			int id = StringUtil.toInt(strId);
			if(id <= 0){
				json.append(false).append("}");
				out.print(json);
				out.flush();
				out.close();
				return;
			}
			Product product = productService.getProduct(id);
			if(product == null){
				json.append(false).append("}");
				out.print(json);
				out.flush();
				out.close();
				return;
			}
			json.append(true).append(",\"id\":").append(product.getId());
			json.append(",\"name\":\"").append(product.getName());
			json.append("\",\"price\":").append(product.getPriceNow()).append("}");
			out.print(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/product/productToday")
	public ModelAndView productToday() {
		ModelAndView model = new ModelAndView("/product/product_today");
		try {
			List<Product> list = productService.productTodayList(DateUtil.getTodayLong());
			model.addObject("list",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/product/productList")
	public ModelAndView productList(@RequestParam(value = "pageIndex", required = false) String strPageIndex) {
		ModelAndView model = new ModelAndView("/product/product_list");
		try {
			int pageIndex = 1;
			if(strPageIndex != null){
				pageIndex = StringUtil.toInt(strPageIndex);
			}
			pageIndex = pageIndex <= 0 ? 1 : pageIndex;
			int totalCount = productService.getCount();
			PageBean pageBean = Custom.getPageBean(totalCount,Constant.COUNT_10,pageIndex,Constant.PAGENUM_9,"productList");
			List<Product> list = productService.productList(pageBean);
			model.addObject("pageBean",pageBean);
			model.addObject("list",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/product/editProduct",method = RequestMethod.GET)
	public ModelAndView editProduct(
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "search", required = false) String search
			){
		ModelAndView model = new ModelAndView("/product/product_edit");
		try {
			List<Classify> classifyList = classifyService.classifyList();
			model.addObject("stats",Constant.STATS);
			model.addObject("classifyList",classifyList);

			int id = StringUtil.toInt(strId);
			if(id <= 0){
				return model;
			}
			Product product = productService.getProduct(id);
			if(product == null){
				return model;
			}
			if(search != null){
				model.addObject("search",DateUtil.getTodayLong());
			}
			model.addObject("product",product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/product/detailProduct",method = RequestMethod.GET)
	public ModelAndView detailProduct(@RequestParam(value = "id", required = false) String strId){
		ModelAndView model = new ModelAndView("/product/product_detail");
		try {
			int id = StringUtil.toInt(strId);
			if(id <= 0){
				return model;
			}
			Product product = productService.getProduct(id);
			model.addObject("product",product);
			model.addObject("IMAGE1",Constant.IMAGE1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/product/showImage",method = RequestMethod.GET)
	public void showImage(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "fileName", required = false) String fileName
			) throws IOException{
		try {
			fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
			FileInputStream is = new FileInputStream(Custom.getProductFilePath(request,fileName));
			int i = is.available();
			byte data[] = new byte[i];
			is.read(data);
			is.close();
			response.reset();
			response.setContentType("image/*");
			OutputStream toClient = response.getOutputStream();
			toClient.write(data);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/product/editProduct",method = RequestMethod.POST)
	public ModelAndView editProduct(
			HttpServletRequest request,
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "classifyId", required = false) String strClassifyId,
			@RequestParam(value = "stat", required = false) String strStat,
			@RequestParam(value = "shelvesTime", required = false) String strShelvesTime,
			@RequestParam(value = "priceOld", required = false) String strPriceOld,
			@RequestParam(value = "priceNow", required = false) String strPriceNow,
			@RequestParam(value = "inventory", required = false) String strInventory,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "recommend", required = false) String strRecommend
			) throws ParseException, IllegalStateException, IOException {
		ModelAndView model = new ModelAndView("/product/product_alert");
		try {
			model.addObject("isTrue", false);
			model.addObject("tips", "参数错误 请返回重试");
			int classifyId = StringUtil.toInt(strClassifyId);
			if(classifyId <= 0){
				return model;
			}
			if(name == null || name.length() <= 0){
				return model;
			}
			long currentTimeMillis = System.currentTimeMillis();
			long shelvesTime = 0L;
			if(strShelvesTime != null && strShelvesTime.length() == 10){
				shelvesTime = Constant.YYYYMMDD_FOMAT.parse(strShelvesTime).getTime();
			}else{
				shelvesTime = Constant.YYYYMMDD_FOMAT.parse(DateUtil.getToday()).getTime();
			}
			float priceOld = StringUtil.toFloat(strPriceOld);
			float priceNow = StringUtil.toFloat(strPriceNow);
			if(priceNow <= 0F){
				return model;
			}
			if(priceNow > priceOld || priceOld <= 0F){
				priceOld = priceNow;
			}
			int inventory = StringUtil.toInt(strInventory);
			if(inventory < Constant.INVENTORY_EMPTY){
				inventory = Constant.INVENTORY_FULL;
			}
			String[] FILES = new String [2];
			if (file != null && !file.isEmpty()) {
				boolean b = false;
				String originalFilename = file.getOriginalFilename();
				String fileName = Custom.getFileName(originalFilename);
				for (String suffix : Constant.SUFFIX) {
					if(fileName.endsWith(suffix)){
						b = true;
						break;
					}
				}
				if(b){
					StringBuilder path = Custom.getProductFilePath();
					File targetFile = new File(path.toString(),fileName);
					if(!targetFile.exists()){
						targetFile.mkdirs();
					}
					file.transferTo(targetFile);
					FILES[0] = originalFilename;
					FILES[1] = fileName;
				}
			}

			int recommend = Constant.RECOMMEND_FALSE;
			if(StringUtil.toInt(strRecommend) != recommend){
				int count = productService.recommendProductCount(Constant.RECOMMEND_TRUE);
				if(count < Constant.RECOMMEND_COUNT){
					recommend = Constant.RECOMMEND_TRUE;
				}
			}

			Product product = new Product();
			product.setName(StringUtil.toSql(name.trim()));
			product.setClassifyId(classifyId);
			product.setCreateTime(currentTimeMillis);
			product.setUpdateTime(currentTimeMillis);
			product.setInventory(inventory);
			product.setPriceNow(priceNow);
			product.setPriceOld(priceOld);
			product.setShelvesTime(shelvesTime);
			product.setStat(StringUtil.toInt(strStat));
			product.setImageFlag(Constant.IMAGE2);
			product.setRecommend(recommend);
			int id = StringUtil.toInt(strId);
			if(id > 0){
				HttpSession session = request.getSession();
				StaffInfo staffInfo = (StaffInfo)session.getAttribute(Constant.STAFF);
				String record = "最后修改:" + staffInfo.getName();
				product.setId(id);
				product.setRecord(record);
				Product tProduct = productService.getProduct(id);
				product = Product.editorImage(request,product, tProduct, FILES);
				productService.updateProduct(product);
			}else{
				product = Product.editorImage(request,product, null, FILES);
				Product tProduct = productService.getLastProduct();
				String productNo = null;
				if(tProduct != null){
					productNo = tProduct.getNo();
				}
				product.setNo(Custom.getProductNo(productNo));
				productService.insertProduct(product);
			}
			if(search != null && search.length() == 13){
				model.addObject("search",true);
			}else{
				model.addObject("search",false);
			}
			model.addObject("isTrue", true);
			model.addObject("tips", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/product/deleteProduct",method = RequestMethod.GET)
	public String deleteProduct(
			@RequestParam(value="id", required = false) String strId,
			@RequestParam(value="form", required = false) String form
			) {
		try {
			int id = StringUtil.toInt(strId);
			if(id <= 0){
				return "redirect:/product/productList.htm";
			}
			Product product = productService.getProduct(id);
			int imageFlag = product.getImageFlag();
			if(imageFlag == Constant.IMAGE1){
				String imageFileName = product.getImageFileName();
				Custom.delFile(Custom.getProductFilePath().toString(), imageFileName);
			}
			productService.deleteProduct(id);
			if(form != null && form.equals("today")){
				return "redirect:/product/productToday.htm";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/product/productList.htm";
	}
	
	@RequestMapping(value = "/product/selectProduct",method = RequestMethod.POST)
	public void selectProduct(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="name", required = false) String name,
			@RequestParam(value="productIds", required = false) String productIds
			) throws IOException {
		try {
			response.setContentType("text/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			Map<String, Object> param = new HashMap<String, Object>();
			if(productIds != null && productIds.length() > 0){
				productIds = productIds.trim().replace(" ", "");
				if(productIds.length() <= 0){
					productIds = "0";
				}
				productIds = productIds.substring(0,productIds.length() - 1);
			}else{
				productIds = "0";
			}
			StringBuilder json = new StringBuilder();
			json.append("{\"products\":[");
			if (name == null || name.length() <= 0) {
				json.append("]}");
				out.write(json.toString());
				out.flush();
				out.close();
				return;
			}
			param.put("name", StringUtil.toSqlLike(name));
			param.put("productIds", StringUtil.toSql(productIds));
			param.put("stat", Constant.STAT_SALE);
			List<Product> list = productService.getSelectProduct(param);
			if (list == null || list.size() == 0) {
				json.append("]}");
				out.write(json.toString());
				out.flush();
				out.close();
				return;
			}
			StringBuilder info = new StringBuilder();
			for (Product product : list) {
				info.append("{\"id\":\"").append(product.getId()).append("\",");
				info.append("\"no\":\"").append(product.getNo()).append("\",");
				info.append("\"name\":\"").append(product.getName()).append("\",");
				info.append("\"price\":\"").append(product.getPriceNow()).append("\"},");
			}
			json.append(info.subSequence(0, info.length() - 1));
			json.append("]}");
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}