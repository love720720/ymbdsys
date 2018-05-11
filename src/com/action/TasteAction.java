package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.Taste;
import com.service.TasteService;
import com.util.StringUtil;

@Controller
public class TasteAction {
	
	@Autowired
	private TasteService tasteService;
	
	@RequestMapping(value = "/taste/tasteList",method = RequestMethod.GET)
	public ModelAndView tasteList() {
		ModelAndView model = new ModelAndView("/taste/taste_list");
		try {
			List<Taste> list = tasteService.tasteList();
			model.addObject("list",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/taste/editTaste",method = RequestMethod.GET)
	public ModelAndView editTaste() {
		ModelAndView model = new ModelAndView("/taste/taste_edit");
		return model;
	}
	
	@RequestMapping(value = "/taste/editTaste",method = RequestMethod.POST)
	public String editTaste(
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "strSort", required = false) String strSort) {
		try {
			int id = StringUtil.toInt(strId);
			if(name == null || name.length() <= 0){
				return "redirect:/taste/tasteList.htm";
			}
			Taste taste = new Taste();
			if(id <= 0){
				taste.setName(StringUtil.toSql(name.trim()));
				taste.setSort(StringUtil.toInt(strSort));
				tasteService.insertTaste(taste);
			}else{
				taste.setId(id);
				taste.setName(StringUtil.toSql(name.trim()));
				taste.setSort(StringUtil.toInt(strSort));
				tasteService.updateTaste(taste);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/taste/tasteList.htm";
	}
	
	@RequestMapping(value = "/taste/updateTaste",method = RequestMethod.GET)
	public ModelAndView updateTaste(@RequestParam(value = "id", required = false) String strId) {
		ModelAndView model = new ModelAndView("/taste/taste_edit");
		try {
			Taste taste = tasteService.getTaste(StringUtil.toInt(strId));
			model.addObject("taste",taste);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/taste/deleteTaste",method = RequestMethod.GET)
	public String deleteTaste(@RequestParam(value = "id", required = false) String strId) {
		try {
			int id = StringUtil.toInt(strId);
			if(id <= 0){
				return "redirect:/taste/tasteList.htm";
			}
			tasteService.deleteTaste(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/taste/tasteList.htm";
	}
}