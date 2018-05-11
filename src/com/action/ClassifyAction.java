package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.Classify;
import com.service.ClassifyService;
import com.service.ProductService;
import com.util.StringUtil;

@Controller
public class ClassifyAction {

	@Autowired
	private ClassifyService classifyService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/classify/editClassify",method = RequestMethod.GET)
	public ModelAndView editClassify(@RequestParam(value = "id", required = false) String strId) {
		ModelAndView model = new ModelAndView("/classify/classify_edit");
		try {
			int id = StringUtil.toInt(strId);
			if(id > 0){
				Classify classify = classifyService.getClassify(id);
				model.addObject("classify",classify);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/classify/classifyList")
	public ModelAndView classifyList() {
		ModelAndView model = new ModelAndView("/classify/classify_list");
		try {
			List<Classify> list = classifyService.classifyList();
			model.addObject("list",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/classify/editClassify",method = RequestMethod.POST)
	public String editClassify(
			@RequestParam(value = "id", required = false) String strId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "sort", required = false) String strSort) {
		if(name == null || name.length() <= 0){
			return "redirect:/classify/classifyList.htm";
		}
		try {
			int id = StringUtil.toInt(strId);
			name = StringUtil.toSql(name);
			int sort = StringUtil.toInt(strSort);

			Classify classify = new Classify();
			classify.setName(name);
			classify.setSort(sort);
			if(id <= 0){
				classifyService.insertClassify(classify);
			}else{
				classify.setId(id);
				classifyService.updateClassify(classify);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/classify/classifyList.htm";
	}
	
	@RequestMapping(value = "/classify/deleteClassify",method = RequestMethod.GET)
	public String deleteClassify(@RequestParam(value = "id", required = false) String strId) {
		int id = StringUtil.toInt(strId);
		if(id <= 0){
			return "redirect:/classify/classifyList.htm";
		}
		try {
			Classify classify = classifyService.getClassify(id);
			if(classify != null){
				int clsaaifyId = classify.getId();
				classifyService.deleteClassify(clsaaifyId);
				productService.deleteProductAsClassify(clsaaifyId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/classify/classifyList.htm";
	}
}