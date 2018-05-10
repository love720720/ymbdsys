package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.custom.Custom;
import com.util.StringUtil;

@Controller
public class FeedbackAction {

	@RequestMapping(value = "/feedback/suggestFeedback",method = RequestMethod.GET)
	public ModelAndView bug() {
		ModelAndView model = new ModelAndView("/feedback/suggest_feedback");
		return model;
	}
	
	@RequestMapping(value = "/feedback/sendFeedbackMail",method = RequestMethod.POST)
	public void sendMail(
			HttpServletResponse response,
			@RequestParam(value = "content", required = false) String content
			) throws IOException {
		PrintWriter out = response.getWriter();
		if(content == null || content.length() <= 0){
			out.print(false);
			return;
		}
		boolean b = Custom.sendBUGMail(StringUtil.toSql(content));
		out.print(b);
		return;
	}
}
