package com.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageBean implements Serializable {

	private static final long serialVersionUID = -5967555917862180641L;

	private int index;
	private int count;//当前页显示条数
	private int totalCount;//总条数
	private int totalPageCount;//总页数
	private int pageIndex;//当前页
	private String fromAction;//分页地址
	private int firstIndex;//开始页
    private int lastIndex;//结束页
    private long createTime;
    private List<Integer> pageNums = new ArrayList<Integer>();
    
	public List<Integer> getPageNums() {
		for (int i = firstIndex; i <= lastIndex; firstIndex++,i++) {
			pageNums.add(firstIndex);
		}
		return pageNums;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(count > 0) {
			totalPageCount = (totalCount / count);
			if(totalCount % count != 0) {
				totalPageCount ++;
			}
		}
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getFromAction() {
		return fromAction;
	}

	public void setFromAction(String fromAction) {
		this.fromAction = fromAction;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getFirstIndex() {
        return firstIndex;
    }
    
    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }
    
    public int getLastIndex() {
        return lastIndex;
    }
    
    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }
    
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalCount() {
		return totalCount;
	}
}
