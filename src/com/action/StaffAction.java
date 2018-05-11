package com.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.pojo.PageBean;
import com.pojo.Role;
import com.pojo.Staff;
import com.pojo.StaffInfo;
import com.pojo.StaffInfoRole;
import com.service.RoleService;
import com.service.StaffInfoRoleService;
import com.service.StaffInfoService;
import com.util.PasswordMD5;
import com.util.PinYinUtil;
import com.util.StringUtil;

@Controller
public class StaffAction {

	@Autowired
	private StaffInfoService staffInfoService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private StaffInfoRoleService staffInfoRoleService;
	
	@RequestMapping(value = "/staff/pingYin",method = RequestMethod.POST)
	public void pingYin(
			HttpServletResponse response,
			@RequestParam(value = "name", required = false) String name
			) throws IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			StringBuilder json = new StringBuilder();
			json.append("{\"isTrue\":");
			if(name == null || name.length() <= 0){
				json.append(false).append(",\"param\":\"名称为空 请返回重试\"}");
				out.print(json);
				return;
			}
			String userName = PinYinUtil.converterToSpell(name);
			Staff staff = staffInfoService.getStaffAsName(userName);
			if(staff != null){
				json.append(false).append(",\"param\":\"用户名已经存在 请重新填写\"}");
				out.print(json);
				return;
			}
			json.append(true).append(",\"param\":\"").append(userName).append("\"}");
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/staff/editStaff",method = RequestMethod.POST)
	public void editStaff(
			HttpServletResponse response,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "gender", required = false) String strGender,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "email", required = false) String email
			) throws IOException {
		try {
			PrintWriter out = response.getWriter();
			if(name == null || userName == null || name.length() < 2 || name.length() > 16){
				out.print(false);
				return;
			}
			String password = Constant.PASSWORD;
			if(email != null && email.length() > 0){
				password = Custom.getPassword();
			}
			String passwordMd5 = PasswordMD5.encryptionStaffPassword(password);
			StaffInfo staffInfo = new StaffInfo();
			staffInfo.setGender(StringUtil.toInt(strGender));
			staffInfo.setName(name);
			staffInfo.setPhone(phone);
			staffInfo.setLastLoginTime(Constant.LONGINIT);
			int i = staffInfoService.insertStaffInfo(staffInfo);
			if(i <= 0){
				out.print(false);
				return;
			}
			int id = staffInfo.getId();
			Staff staff = new Staff();
			staff.setPassword(passwordMd5);
			staff.setRegisterTime(System.currentTimeMillis());
			staff.setStaffInfoId(id);
			staff.setUserName(userName);
			staff.setStat(Constant.STAFF_ACTIVATE);
			i = staffInfoService.insertStaff(staff);
			if(i <= 0){
				staffInfoService.deleteStaffInfo(id);
				out.print(false);
				return;
			}
			Custom.sendStaffPassword(email, password);
			out.print(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/staff/upPassword",method = RequestMethod.POST)
	public void upPassword(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "oldPassword", required = false) String oldPassword,
			@RequestParam(value = "newPassword", required = false) String newPassword
			) throws IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			StringBuilder json = new StringBuilder();
			json.append("{\"isTrue\":");
			if(oldPassword == null || newPassword == null){
				json.append(false).append(",\"param\":\"").append("密码为空 请重试").append("\"}");
				out.print(json);
				return;
			}
			HttpSession session = request.getSession();
			StaffInfo staffInfo = (StaffInfo)session.getAttribute(Constant.STAFF);
			if(staffInfo == null){
				json.append(false).append(",\"param\":\"").append("未获取用户信息 请刷新重试").append("\"}");
				out.print(json);
				return;
			}
			oldPassword = PasswordMD5.encryptionStaffPassword(oldPassword);
			if(!staffInfo.getPassword().endsWith(oldPassword)){
				json.append(false).append(",\"param\":\"").append("原始密码不正确 请重试").append("\"}");
				out.print(json);
				return;
			}
			newPassword = PasswordMD5.encryptionStaffPassword(StringUtil.toSql(newPassword));
			staffInfo.setPassword(newPassword);
			int i = staffInfoService.updatePassword(staffInfo);
			if(i <= 0){
				json.append(false).append(",\"param\":\"").append("密码修改失败 请重试").append("\"}");
				out.print(json);
				return;
			}
			session.removeAttribute(Constant.STAFF);
			json.append(true).append(",\"param\":\"").append("密码修改成功 请重新登录").append("\"}");
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/staff/editStaffRole",method = RequestMethod.POST)
	public String editStaffRole(
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "roleId", required = false) String strRoleId
			) {
		try {
			int id = StringUtil.toInt(strId);
			int roleId = StringUtil.toInt(strRoleId);
			if(id > 0 && roleId > 0){
				StaffInfo staffInfo = staffInfoService.getStaffInfoAsId(id);
				Role role = roleService.getRole(roleId);
				if(staffInfo != null && role != null){
					StaffInfoRole staffInfoRole = new StaffInfoRole();
					staffInfoRole.setRoleId(roleId);
					staffInfoRole.setStaffInfoId(id);
					staffInfoRoleService.deleteStaffInfoRoleAsStaffId(id);
					staffInfoRoleService.insertStaffInfoRole(staffInfoRole);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/staff/staffList.htm";
	}
	
	@RequestMapping(value = "/staff/manageStaff",method = RequestMethod.GET)
	public ModelAndView manageStaff(@RequestParam(value = "id", required = false) String strId) {
		ModelAndView model = new ModelAndView("/staff/staff_manage");
		try {
			int id = StringUtil.toInt(strId);
			if(id > 0){
				StaffInfo staffInfo = staffInfoService.getStaffInfoAsId(id);
				List<Role> roleList = roleService.getRoleList();
				model.addObject("staffInfo", staffInfo);
				model.addObject("roleList", roleList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/staff/staffCenter",method = RequestMethod.GET)
	public ModelAndView staffCenter(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("/staff/staff_center");
		try {
			HttpSession session = request.getSession();
			StaffInfo staffInfo = (StaffInfo)session.getAttribute(Constant.STAFF);
			model.addObject("staffInfo",staffInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/staff/password",method = RequestMethod.GET)
	public ModelAndView password() {
		ModelAndView model = new ModelAndView("/staff/staff_password");
		return model;
	}
	
	@RequestMapping(value = "/staff/staffCentre",method = RequestMethod.GET)
	public ModelAndView staffCentre() {
		ModelAndView model = new ModelAndView("/staff/staff_edit");
		model.addObject("genders",Constant.GENDERS);
		return model;
	}
	
	@RequestMapping(value = "/staff/editStaff",method = RequestMethod.GET)
	public ModelAndView editStaff_GET() {
		ModelAndView model = new ModelAndView("/staff/staff_edit");
		model.addObject("genders",Constant.GENDERS);
		return model;
	}
	
	@RequestMapping(value = "/staff/staffList")
	public ModelAndView classifyList(@RequestParam(value = "pageIndex", required = false) String strPageIndex) {
		ModelAndView model = new ModelAndView("/staff/staff_list");
		try {
			int pageIndex = 1;
			if(strPageIndex != null){
				pageIndex = StringUtil.toInt(strPageIndex);
			}
			pageIndex = pageIndex <= 0 ? 1 : pageIndex;
			int totalCount = staffInfoService.getCount();
			PageBean pageBean = Custom.getPageBean(totalCount,Constant.COUNT_10,pageIndex,Constant.PAGENUM_9,"staffList");
			List<StaffInfo> list = staffInfoService.staffInfoList(pageBean);
			model.addObject("pageBean",pageBean);
			model.addObject("list",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/staff/activateDestroy")
	public void activateDestroy(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = false) String strId
			) throws IOException {
		try {
			PrintWriter out = response.getWriter();
			int id = StringUtil.toInt(strId);
			if(id <= 0){
				out.print(false);
				return;
			}
			Staff staff = staffInfoService.getStaff(id);
			if(staff == null){
				out.print(false);
				return;
			}
			Object object = request.getSession().getAttribute(Constant.STAFF);
			if(object == null){
				out.print(false);
				return;
			}
			StaffInfo sessionStaffInfo  = (StaffInfo )object;
			if(sessionStaffInfo .getId() == staff.getStaffInfoId()){
				out.print(false);
				return;
			}
			int stat = staff.getStat();
			stat = stat == Constant.STAFF_ACTIVATE ? Constant.STAFF_DESTROY : Constant.STAFF_ACTIVATE;
			staff.setStat(stat);
			int i = staffInfoService.updateStaff(staff);
			if(i <= 0){
				out.print(false);
				return;
			}
			out.print(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}