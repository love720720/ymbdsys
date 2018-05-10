package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.constant.Constant;
import com.custom.Custom;
import com.pojo.PageBean;
import com.pojo.Privilege;
import com.pojo.Role;
import com.pojo.RolePrivilege;
import com.service.PrivilegeService;
import com.service.RolePrivilegeService;
import com.service.RoleService;
import com.util.StringUtil;

@Controller
public class RoleAction {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	
	@RequestMapping(value = "/role/editRolePrivilege",method = RequestMethod.GET)
	public String editRolePrivilege(
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "ids", required = false) String ids
			) {
		int id = StringUtil.toInt(strId);
		if(id <= 0){
			return "redirect:/role/manageRole.htm";
		}
		rolePrivilegeService.delRolePrivilegeAsRoleId(id);
		if(ids == null || ids.length() <= 0){
			return "redirect:/role/manageRole.htm?id="+id;
		}
		String[] split = ids.split(",");
		for (int i = 0; i < split.length; i++) {
			int privilegeId = StringUtil.toInt(split[i]);
			if(privilegeId > 0){
				Privilege privilege = privilegeService.getPrivilege(privilegeId);
				if(privilege != null){
					RolePrivilege rolePrivilege = new RolePrivilege();
					rolePrivilege.setRoleId(id);
					rolePrivilege.setPrivilegeId(privilegeId);
					rolePrivilegeService.insertRolePrivilege(rolePrivilege);
				}
			}
		}
		return "redirect:/role/manageRole.htm?id="+id;
	}
	
	@RequestMapping(value = "/role/manageRole",method = RequestMethod.GET)
	public ModelAndView manageRole(@RequestParam(value = "id", required = false) String strId) {
		ModelAndView model = new ModelAndView("/role/role_manage");
		int id = StringUtil.toInt(strId);
		if(id <= 0){
			return model;
		}
		Role role = roleService.getRole(id);
		if(role != null){
			List<Privilege> rolePrivilegeList = privilegeService.getRolePrivilege(id);
			List<Privilege> privilegeList = privilegeService.privilegeList();
			if(rolePrivilegeList != null && rolePrivilegeList.size() > 0 && privilegeList != null && privilegeList.size() > 0){
				for (Privilege rolePrivilege : rolePrivilegeList) {
					for (Privilege privilege : privilegeList) {
						if(rolePrivilege.getId() == privilege.getId()){
							privilege.setIsTrue(true);
						}
					}
				}
				if(rolePrivilegeList.size() == privilegeList.size()){
					model.addObject("allTrue", true);
				}else{
					model.addObject("allTrue", false);
				}
			}
			model.addObject("role", role);
			model.addObject("privilegeList", privilegeList);
		}
		return model;
	}
	
	@RequestMapping(value = "/role/deleteRole",method = RequestMethod.GET)
	public String deleteRole(@RequestParam(value = "id", required = false) String strId) {
		int id = StringUtil.toInt(strId);
		if(id <= 0){
			return "redirect:/role/roleList.htm";
		}
		Role role = roleService.getRole(id);
		if(role != null){
			int roleId = role.getId();
			roleService.deleteRole(roleId);
			rolePrivilegeService.delRolePrivilegeAsRoleId(roleId);
		}
		return "redirect:/role/roleList.htm";
	}
	
	@RequestMapping(value = "/role/editRole",method = RequestMethod.POST)
	public String editRole(
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "level", required = false) String strLevel,
			@RequestParam(value = "sort", required = false) String sort
			) {
		if(name == null || name.length() <= 0){
			return "redirect:/role/roleList.htm";
		}
		int level = StringUtil.toInt(strLevel);
		if(level < 0 || level > 4){
			return "redirect:/role/roleList.htm";
		}
		int id = StringUtil.toInt(strId);
		Role role = new Role();
		role.setName(name);
		role.setLevel(level);
		role.setSort(StringUtil.toInt(sort));
		if(id <= 0){
			roleService.insertRole(role);
		}else{
			role.setId(id);
			roleService.updateRole(role);
		}
		return "redirect:/role/roleList.htm";
	}
	
	@RequestMapping(value = "/role/editRole")
	public ModelAndView editRole(@RequestParam(value = "id", required = false) String strId) {
		ModelAndView model = new ModelAndView("/role/role_edit");
		int id = StringUtil.toInt(strId);
		if(id > 0){
			Role role = roleService.getRole(id);
			model.addObject("role",role);
		}
		model.addObject("roleLevels", Constant.ROLELEVELS);
		return model;
	}
	
	@RequestMapping(value = "/role/roleList",method = RequestMethod.GET)
	public ModelAndView roleList(@RequestParam(value = "pageIndex", required = false) String strPageIndex) {
		int pageIndex = 1;
		if(strPageIndex != null){
			pageIndex = StringUtil.toInt(strPageIndex);
		}
		pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		int totalCount = roleService.getCount();
		PageBean pageBean = Custom.getPageBean(totalCount,Constant.COUNT_10,pageIndex,Constant.PAGENUM_9,"roleList");
		List<Role> list = roleService.roleList(pageBean);
		ModelAndView model = new ModelAndView("/role/role_list");
		model.addObject("pageBean",pageBean);
		model.addObject("list",list);
		return model;
	}
}