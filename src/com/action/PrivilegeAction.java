package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.Privilege;
import com.service.PrivilegeService;
import com.service.RolePrivilegeService;
import com.util.StringUtil;

@Controller
public class PrivilegeAction {

	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	
	@RequestMapping(value = "/privilege/detailPrivilege",method = RequestMethod.GET)
	public ModelAndView detailPrivilege(@RequestParam(value = "id", required = false) String strId) {
		ModelAndView model = new ModelAndView("/privilege/privilege_detail");
		try {
			int id = StringUtil.toInt(strId);
			if(id > 0){
				Privilege privilege = privilegeService.getPrivilege(id);
				if(privilege != null){
					int privilegeId = privilege.getId();
					List<Privilege> privilegeList = privilegeService.getPrivilegeAsParentId(privilegeId);
					privilege.setPrivilegeList(privilegeList);
				}
				model.addObject("privilege", privilege);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/privilege/deletePrivilege",method = RequestMethod.POST)
	public void deletePrivilege(
			HttpServletResponse response,
			@RequestParam(value = "id", required = false) String strId
			) {
		try {
			response.setCharacterEncoding("text/json;charest=UTF-8");
			PrintWriter out = response.getWriter();
			StringBuilder json = new StringBuilder();
			json.append("{\"isTrue\":");
			int id = StringUtil.toInt(strId);
			if(id <= 0){
				json.append(false).append(",\"tips\":\"").append("参数错误 请重试").append("\"}");
				out.print(json);
				return;
			}
			Privilege privilege = privilegeService.getPrivilege(id);
			if(privilege == null){
				json.append(false).append(",\"tips\":\"").append("信息不存在或已经被删除").append("\"}");
				out.print(json);
				return;
			}
			List<Privilege> privilegeList = privilegeService.getPrivilegeAsParentId(id);
			if(privilegeList != null && privilegeList.size() > 0){
				json.append(false).append(",\"tips\":\"").append("请先删除子节点").append("\"}");
				out.print(json);
				return;
			}
			privilegeService.deletePrivilege(id);
			rolePrivilegeService.delRolePrivilegeAsPrivilegeId(id);
			json.append(true).append(",\"tips\":\"").append("操作成功").append("\"}");
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/privilege/editPrivilege",method = RequestMethod.POST)
	public String editPrivilege(
			@RequestParam(value = "pid", required = false) String strPid,
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "target", required = false) String target,
			@RequestParam(value = "display", required = false) String display,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "sort", required = false) String sort
			) {
		try {
			if(name == null || name.length() <= 0){
				return "redirect:/privilege/privilegeList.htm";
			}
			name = StringUtil.toSql(name.trim());
			int id = StringUtil.toInt(strId);
			Privilege privilege = new Privilege();
			privilege.setName(name);
			privilege.setSort(StringUtil.toInt(sort));
			privilege.setDisplay(StringUtil.toBoolean(display));
			privilege.setTarget(target);
			privilege.setUrl(url);
			if(type != null && type.equals("tree")){
				privilege.setIsTree(true);
				privilege.setIsButton(false);
			}else{
				privilege.setIsTree(false);
				privilege.setIsButton(true);
				privilege.setCode(StringUtil.toInt(code));
			}
			int pid = StringUtil.toInt(strPid);
			pid = pid < 0 ? 0 : pid;
			if(id <= 0){
				privilege.setParentId(pid);
				privilegeService.insertPrivilege(privilege);
			}else{
				privilege.setId(id);
				privilegeService.updatePrivilege(privilege);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/privilege/privilegeList.htm";
	}
	
	@RequestMapping(value = "/privilege/editPrivilege",method = RequestMethod.GET)
	public ModelAndView editPrivilege(
			@RequestParam(value = "pid", required = false) String strPid,
			@RequestParam(value = "id", required = false) String strId
			) {
		ModelAndView model = new ModelAndView("/privilege/privilege_edit");
		try {
			int pid = StringUtil.toInt(strPid);
			int id = StringUtil.toInt(strId);
			if(pid == -1){
				Privilege privilege = privilegeService.getPrivilege(id);
				model.addObject("privilege",privilege);
			}
			model.addObject("pid", pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/privilege/privilegeList",method = RequestMethod.GET)
	public ModelAndView privilegeList() {
		ModelAndView model = new ModelAndView("/privilege/privilege_list");
		try {
			List<Privilege> privilegeList = privilegeService.privilegeList();
			model.addObject("privilegeList",privilegeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}