package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.ProductMapper;
import com.pojo.PageBean;
import com.pojo.Product;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> recommendProductList(int recommend) {
		return productMapper.recommendProductList(recommend);
	}
	
	public int insertProduct(Product product) {
		return productMapper.insertProduct(product);
	}

	public void updateProduct(Product product) {
		productMapper.updateProduct(product);
	}
	
	public void deleteProduct(int id) {
		productMapper.deleteProduct(id);
	}

	public int getCount() {
		return productMapper.getCount();
	}

	public List<Product> productList(PageBean pageBean) {
		return productMapper.productList(pageBean);
	}

	public Product getProduct(int id) {
		return productMapper.getProduct(id);
	}

	public List<Product> getSelectProduct(Map<String, Object> param) {
		return productMapper.getSelectProduct(param);
	}

	public void deleteProductAsClassify(int id) {
		productMapper.deleteProductAsClassify(id);
	}

	public List<Product> getProductAsOrder(int id) {
		return productMapper.getProductAsOrder(id);
	}

	public synchronized Product getLastProduct() {
		return productMapper.getLastProduct();
	}

	public List<Product> productTodayList(long createTime) {
		return productMapper.productTodayList(createTime);
	}

	public List<Product> productTodayShelvesList(Map<String, Object> map) {
		return productMapper.productTodayShelvesList(map);
	}

	public List<Product> getProductAsName(String name) {
		return productMapper.getProductAsName(name);
	}

	public int recommendProduct(Product product) {
		return productMapper.recommendProduct(product);
	}

	public int recommendProductCount(int recommend) {
		return productMapper.recommendProductCount(recommend);
	}
}
