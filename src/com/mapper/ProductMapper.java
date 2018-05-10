package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.PageBean;
import com.pojo.Product;

public interface ProductMapper {

	public List<Product> recommendProductList(int recommend);
	
	public List<Product> productList(PageBean pageBean);
	
	public int getCount();
	
	public int insertProduct(Product product);

	public void updateProduct(Product product);

	public void deleteProduct(int id);
	
	public Product getProduct(int id);

	public List<Product> getSelectProduct(Map<String, Object> param);

	public void deleteProductAsClassify(int id);

	public List<Product> getProductAsOrder(int id);

	public Product getLastProduct();

	public List<Product> productTodayList(long createTime);

	public List<Product> productTodayShelvesList(Map<String, Object> map);

	public List<Product> getProductAsName(String name);

	public int recommendProduct(Product product);

	public int recommendProductCount(int recommend);
}
