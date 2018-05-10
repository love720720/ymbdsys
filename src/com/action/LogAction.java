package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.constant.Constant;
import com.custom.Custom;
import com.pojo.Log;
import com.pojo.PageBean;
import com.service.LogService;
import com.util.StringUtil;

@Controller
public class LogAction {

	@Autowired
	private LogService logService;
	 
	@RequestMapping(value = "/log/clearAccessLog")
	public String clearAccessLog() {
		logService.clearAccessLog();
		return "redirect:/log/accessLog.htm";
	}
	
	@RequestMapping(value = "/log/accessLog")
	public ModelAndView accessLog(
			@RequestParam(value = "pageIndex", required = false) String strPageIndex//当前页
			) {
		int pageIndex = StringUtil.toInt(strPageIndex);
		if(pageIndex < 0)
			pageIndex = 0;

		int totalCount = logService.getAccessCount();
		PageBean pageBean = Custom.getPageBean(totalCount, Constant.COUNT_20,pageIndex,Constant.PAGENUM_9,"accessLog");
		List<Log> list = logService.getAccessLog(pageBean);
		ModelAndView model = new ModelAndView("/log/access_log");
		model.addObject("list", list);
		model.addObject("pageBean", pageBean);
		return model;
	}
}