package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.constant.Constant;
import com.custom.Custom;
import com.pojo.Button;
import com.pojo.Privilege;
import com.pojo.Staff;
import com.pojo.StaffInfo;
import com.pojo.Tree;
import com.service.PrivilegeService;
import com.service.StaffInfoService;
import com.util.PasswordMD5;
import com.util.StringUtil;

@Controller
public class LoginAction {

	@Autowired
	private StaffInfoService staffInfoService;
	@Autowired
	private PrivilegeService privilegeService;
	
	/*
	 * 登陆验证
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public void login(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "loginUserName", required = false) String loginUserName,
			@RequestParam(value = "loginUserPassword", required = false) String loginUserPassword
			) throws IOException {
		PrintWriter out = response.getWriter();
		String userName = StringUtil.toSql(loginUserName);
		String password = StringUtil.toSql(loginUserPassword);
		if(userName == null || password == null || userName.length() <= 0 || password.length() <= 0){
			out.print(false);
			return;
		}
		password = PasswordMD5.encryptionStaffPassword(password);
		Staff staff = new Staff();
		staff.setUserName(userName);
		staff.setPassword(password);
		staff.setStat(Constant.STAFF_ACTIVATE);
		StaffInfo staffInfo = staffInfoService.getStaffInfo(staff);
		if(staffInfo == null){
			out.print(false);
			return;
		}
		String loginTimeStr = staffInfo.getLastLoginTimeStr();
		staffInfo.setLastLoginTime(System.currentTimeMillis());
		staffInfoService.updateLastLoginTime(staffInfo);
		Date date = new Date(staffInfo.getLastLoginTime());
		staffInfo.setLoginTimeStr(loginTimeStr);
		staffInfo.setLastLoginTimeStr(Constant.YYYYMMDDHHMMSS_FOMAT.format(date));
		HttpSession session = request.getSession();
		
		List<Privilege> privilegeList  = privilegeService.getStaffPrivilege(staffInfo.getId());
		List<Tree> treeList = new ArrayList<Tree>();
		List<Button> buttonList = new ArrayList<Button>();
		if(privilegeList != null && privilegeList.size() > 0){
			for (Privilege privilege : privilegeList) {
				boolean isTrue = privilege.getIsTrue();
				boolean display = privilege.getDisplay();
				if(isTrue && display && privilege.getIsTree()){
					Tree tree = new Tree();
					tree.setId(privilege.getId());
					tree.setName(privilege.getName());
					tree.setParentId(privilege.getParentId());
					tree.setSort(privilege.getSort());
					tree.setTarget(privilege.getTarget());
					tree.setUrl(privilege.getUrl());
					treeList.add(tree);
				}
				if(isTrue && display && privilege.getIsButton()){
					Button button = new Button();
					button.setCode(privilege.getCode());
					buttonList.add(button);
				}
			}
		}
		staffInfo.setPrivilegeList(privilegeList);
		session.setAttribute(Constant.STAFF, staffInfo);
		session.setAttribute(Constant.TREELIST, treeList);
		session.setAttribute(Constant.BUTTONLIST, buttonList);
		out.print(true);
		return;
	}
	
	/*
	 * error
	 */
	@RequestMapping(value = "/error")
	public ModelAndView error() {
		ModelAndView model = new ModelAndView("error");
		return model;
	}
	
	/*
	 * 登陆跳转
	 */
	@RequestMapping(value = "/loginForm")
	public String loginForm(@RequestParam(value = "form", required = false) String form) throws UnsupportedEncodingException {
		if(form != null && form.length() > 0){
			return "redirect:" + form;
		}
		return "redirect:./index.htm";
	}

	/*
	 * 首页面配置
	 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("/index");
		model.addObject("welCome",Custom.getWelcome());
		return model;
	}

	/*
	 * 登出取消session
	 */
	@RequestMapping(value = "/logOut",method = RequestMethod.GET)
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.STAFF);
		session.removeAttribute(Constant.TREELIST);
		session.removeAttribute(Constant.BUTTONLIST);
		return "redirect:./index.html";
	}
	
	/*
	 * 无权限提示
	 */
	@RequestMapping(value = "/noPrivilege",method = RequestMethod.GET)
	public ModelAndView noPrivilege(
			HttpServletRequest request,
			@RequestParam(value = "JSESSIONID", required = false) String sid
			) {
		ModelAndView model = new ModelAndView("/no_privilege");
		HttpSession session = request.getSession();
		String id = session.getId();
		if(sid != null && sid.equals(id)){
			model.addObject("isTrue", true);
		}
		return model;
	}
}
