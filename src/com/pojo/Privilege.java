package com.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * 权限信息
 */
public class Privilege implements Serializable {

	private static final long serialVersionUID = -4397086644668274143L;

	private int id;
	private String name;// 权限名称
	private int parentId;// 父权限id
	private String url;// 权限链接
	private boolean isTree;// 是否为左侧权限树
	private boolean isButton;// 是否为按钮权限
	private String target;// 权限树打开方式
	private int code;// 按钮权限代码 唯一不可重复
	private boolean display;// 是否隐藏
	private int sort;

	private int roleId;
	private String roleName;// 角色名称
	private int level;// 角色等级 用于管理人员权限 通等级不可操作 不可操作高于自己等级
	private String levelInfo;
	private int roleSort;
	private boolean isTrue = false;//是否存在该权限
	
	//子权限 可以是页面权限 也可以是按钮权限
	List<Privilege> privilegeList = new ArrayList<Privilege>();

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean getDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public int getLevel() {
		return level;
	}

	public boolean getIsTree() {
		return isTree;
	}

	public boolean getIsButton() {
		return isButton;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLevelInfo() {
		return levelInfo;
	}

	public void setLevelInfo(String levelInfo) {
		this.levelInfo = levelInfo;
	}

	public int getRoleSort() {
		return roleSort;
	}

	public void setRoleSort(int roleSort) {
		this.roleSort = roleSort;
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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIsTree(boolean isTree) {
		this.isTree = isTree;
	}

	public void setIsButton(boolean isButton) {
		this.isButton = isButton;
		
	}
}