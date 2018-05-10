package com.pojo;

import java.io.File;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.constant.Constant;
import com.custom.Custom;


public class Product implements Serializable {

	private static final long serialVersionUID = -1025550327297078000L;

	private int id;
	private int classifyId;// 所属分类
	private String classifyName;
	private String no;// 商品编号
	private String name;
	private long createTime;// 创建日期
	private String createTimeInfo;
	private long updateTime;// 修改时间
	private String updateTimeInfo;
	private long shelvesTime;// 上架日期
	private String shelvesTimeInfo;
	private int stat;// 商品状态
	private String statInfo;
	private float priceOld;// 原价
	private float priceNow;// 现价
	private int activity;// 所属活动商品
	private int inventory;// 库存
	private String inventoryInfo;
	private String standard;// 规格
	private String matters;// 注意事项
	private float shelfLife;// 保质期
	private int imageFlag;// 是否有图片
	private String imageName;// 商品图片 原文件名称
	private String imageFileName;// 商品图片 更改后文件名称
	private String record;// 修改商品时最后操作人以及信息
	private int recommend;// 是否推荐商品
	
	private float priceNows;
	private int byNum;
	
	public String getInventoryInfo() {
		if(inventory > Constant.INVENTORY_EMPTY){
			inventoryInfo = "库存[" + inventory + "]件";
		}else if(inventory == Constant.INVENTORY_FULL){
			inventoryInfo = "库存充足";
		}else if(inventory == Constant.INVENTORY_EMPTY){
			inventoryInfo = "库存已空";
		}
		return inventoryInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(int classifyId) {
		this.classifyId = classifyId;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		if (no != null) {
			int len = no.length();
			if (len > 25) {
				no = no.substring(0, 25);
			}
			no = no.toUpperCase();
		}
		this.no = no;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getShelvesTime() {
		return shelvesTime;
	}

	public void setShelvesTime(long shelvesTime) {
		this.shelvesTime = shelvesTime;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public float getPriceOld() {
		return priceOld;
	}

	public void setPriceOld(float priceOld) {
		this.priceOld = priceOld;
	}

	public float getPriceNow() {
		return priceNow;
	}

	public void setPriceNow(float priceNow) {
		this.priceNow = priceNow;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getStatInfo() {
		if (stat < 0 || stat > 2) {
			return null;
		}
		statInfo = Constant.STATS[stat];
		return statInfo;
	}

	public String getCreateTimeInfo() {
		return createTimeInfo;
	}

	public void setCreateTimeInfo(String createTimeInfo) {
		this.createTimeInfo = createTimeInfo;
	}

	public String getShelvesTimeInfo() {
		return shelvesTimeInfo;
	}

	public void setShelvesTimeStr(String shelvesTimeInfo) {
		this.shelvesTimeInfo = shelvesTimeInfo;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getMatters() {
		return matters;
	}

	public void setMatters(String matters) {
		this.matters = matters;
	}

	public float getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(float shelfLife) {
		this.shelfLife = shelfLife;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getImageFlag() {
		return imageFlag;
	}

	public void setImageFlag(int imageFlag) {
		this.imageFlag = imageFlag;
	}

	public float getPriceNows() {
		return priceNows;
	}

	public void setPriceNows(float priceNows) {
		this.priceNows = priceNows;
	}

	public int getByNum() {
		return byNum;
	}

	public void setByNum(int byNum) {
		this.byNum = byNum;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeInfo() {
		return updateTimeInfo;
	}

	public void setUpdateTimeInfo(String updateTimeInfo) {
		this.updateTimeInfo = updateTimeInfo;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public static Product editorImage(HttpServletRequest request,Product product,Product tProduct,String[] FILES){
		int id = product.getId();
		if(tProduct != null && id > 0){
			int imageFlag = tProduct.getImageFlag();
			if(imageFlag == Constant.IMAGE1){
				String imageFileName = tProduct.getImageFileName();
				if(imageFileName != null && imageFileName.length() > 0){
					File file = new File(Custom.getProductFilePath(request,imageFileName));
					if(file.exists()){
						file.delete();
					}
				}
			}
		}
		if(FILES != null && FILES[0] != null && FILES[1] != null){
			product.setImageFlag(Constant.IMAGE1);
			product.setImageName(FILES[0]);
			product.setImageFileName(FILES[1]);
		}
		return product;
	}
}
