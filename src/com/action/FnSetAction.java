package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.pojo.SiteInformation;
import com.pojo.StaffInfo;
import com.pojo.UpdateLog;
import com.service.SiteInformationService;
import com.service.StaffInfoService;
import com.service.UpdateLogService;
import com.util.StringUtil;

@Controller
public class FnSetAction {

	@Autowired
	private UpdateLogService updateLogService;
	@Autowired
	private StaffInfoService staffInfoService;
	@Autowired
	private SiteInformationService siteInformationService;
	
	@RequestMapping(value = "/fnSet/siteInformationEdit",method = RequestMethod.POST)
	public String siteInformationEdit(
			@RequestParam(value = "urgencyNotice", required = false) String urgencyNotice,
			@RequestParam(value = "notice", required = false) String notice,
			@RequestParam(value = "dispatchExplain", required = false) String dispatchExplain,
			@RequestParam(value = "timeExplain", required = false) String timeExplain,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "qq", required = false) String qq,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "postcode", required = false) String postcode,
			@RequestParam(value = "bottomExplain", required = false) String bottomExplain
			){
		try {
			SiteInformation siteInformation = new SiteInformation();
			siteInformation.setAddress(StringUtil.toSql(address));
			siteInformation.setBottomExplain(StringUtil.toSql(bottomExplain));
			siteInformation.setDispatchExplain(StringUtil.toSql(dispatchExplain));
			siteInformation.setNotice(StringUtil.toSql(notice));
			siteInformation.setPhone(StringUtil.toSql(phone));
			siteInformation.setPostcode(StringUtil.toSql(postcode));
			siteInformation.setQq(StringUtil.toSql(qq));
			siteInformation.setUrgencyNotice(StringUtil.toSql(urgencyNotice));
			siteInformation.setTimeExplain(StringUtil.toSql(timeExplain));

			siteInformationService.truncateSiteInformation();
			siteInformationService.insertSiteInformation(siteInformation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:./siteInformation.htm";
	}
	
	@RequestMapping(value = "/fnSet/siteInformationEdit")
	public ModelAndView siteInformationEdit_GET(){
		ModelAndView model = new ModelAndView("/fnSet/site_information_edit");
		try {
			SiteInformation siteInformation = siteInformationService.getSiteInformation();
			model.addObject("siteInformation", siteInformation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/fnSet/siteInformation")
	public ModelAndView siteInformation(){
		ModelAndView model = new ModelAndView("/fnSet/site_information");
		try {
			SiteInformation siteInformation = siteInformationService.getSiteInformation();
			model.addObject("siteInformation", siteInformation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	//手动加载log
	@RequestMapping(value = "/fnSet/loadLog",method = RequestMethod.POST)
	public void loadLog(
			HttpServletResponse response,
			@RequestParam(value = "pageIndex", required = false) String strPageIndex
			) throws IOException {
		try {
			PrintWriter out = response.getWriter();
			StringBuilder json = new StringBuilder();
			json.append("{\"logs\":[");
			int pageIndex = StringUtil.toInt(strPageIndex);
			pageIndex = pageIndex < 1 ? 1 : pageIndex;
			int totalCount = updateLogService.getCount();
			int count = 5;//每次只多加载五条更新日志
			//终止查询
			if(pageIndex * count >= totalCount){
				json.append("]}");
				out.print(json);
				out.flush();
				out.close();
				return;
			}
			int index = pageIndex * count;
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("index", index);
			map.put("count", count);
			List<UpdateLog> logList = updateLogService.loadLog(map);
			if(logList == null || logList.size() <= 0){
				json.append("]}");
				out.print(json);
				out.flush();
				out.close();
				return;
			}
			StringBuilder info = new StringBuilder();
			for (UpdateLog log : logList) {
				info.append("{\"statInfo\":\"").append(log.getStatName()).append("\",");
				info.append("\"createTimeInfo\":\"").append(log.getCreateTimeInfo()).append("\",");
				info.append("\"staffInfoName\":\"").append(log.getStaffInfoName()).append("\",");
				info.append("\"content\":\"").append(log.getContent()).append("\",");
				info.append("\"statName\":\"").append(log.getStatName()).append("\",");
				info.append("\"isNew\":").append(log.getIsNew()).append("},");
			}
			json.append(info.subSequence(0, info.length() - 1));
			json.append("]}");
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/fnSet/fnSet",method = RequestMethod.GET)
	public ModelAndView fnSet() {
		ModelAndView model = new ModelAndView("/fnSet/fn_set");
		return model;
	}
	
	@RequestMapping(value = "/fnSet/upLogList",method = RequestMethod.GET)
	public ModelAndView upLogList() {
		ModelAndView model = new ModelAndView("/fnSet/update_log_list");
		try {
			List<UpdateLog> updateLogList = updateLogService.updateLogList();
			model.addObject("updateLogList",updateLogList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/fnSet/editUpLog",method = RequestMethod.GET)
	public ModelAndView editUpLog_GET() {
		ModelAndView model = new ModelAndView("/fnSet/update_log_edit");
		try {
			model.addObject("updateLogStat",Constant.UPDATELOGSTAT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/fnSet/editUpLog",method = RequestMethod.POST)
	public String editUpLog(
			HttpServletRequest request,
			@RequestParam(value = "stat", required = false) String strStat,
			@RequestParam(value = "content", required = false) String content
			) {
		try {
			int stat = 0;
			if(strStat != null){
				stat = StringUtil.toInt(strStat);
			}
			stat = stat < 0 ? 0 :stat;
			HttpSession session = request.getSession();
			StaffInfo staffInfo = (StaffInfo)session.getAttribute(Constant.STAFF);
			if(content == null || content.length() <= 0){
				content = Constant.UPDATELOGSTAT[stat];
			}
			UpdateLog updateLog = new UpdateLog();
			updateLog.setCreateTime(System.currentTimeMillis());
			updateLog.setContent(StringUtil.toSql(content));
			updateLog.setStaffInfoId(staffInfo.getId());
			updateLog.setStaffInfoName(staffInfo.getName());
			updateLog.setStat(stat);
			updateLogService.insertUpdateLog(updateLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/fnSet/fnSet.htm";
	}
}