package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import com.constant.Constant;
import com.pojo.Privilege;
import com.pojo.StaffInfo;

public class WebFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		StringBuilder builder = new StringBuilder();
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(Constant.STAFF);
		
		String form = request.getServletPath();
		if(form.equals("/index.html") && obj != null){
			builder.append("<script type=\"text/javascript\">");
			builder.append("window.top.location.href='index.htm';");
			builder.append("</script>");
			out.print(builder.toString());
			return;
		}
		
		String[] notFilter = new String[] {"index.html","main.htm","login.htm","logOut.htm","noPrivilege.htm"};
		boolean doFilter = true;
		for (String s : notFilter) {
			if (form.indexOf(s) != -1) {
				doFilter = false;
				break;
			}
		}
		if (doFilter) {
			String queryString = request.getQueryString();
			if(queryString != null && queryString.length() > 0){
				form = form + "?" + request.getQueryString();
			}
			String contextPath = request.getContextPath();
			String loginPage = contextPath + "/index.html";
			if (null == obj) {
				if(!form.equals("/index.htm")){
					loginPage = loginPage + "?" + URLEncoder.encode(form, "UTF-8");
				}
				builder.append("<script type=\"text/javascript\">");
				builder.append("window.top.location.href='");
				builder.append(loginPage);
				builder.append("';");
				builder.append("</script>");
				out.print(builder.toString());
			} else {
				StaffInfo staffInfo = (StaffInfo)obj;
				List<Privilege> privilegeList = staffInfo.getPrivilegeList();
				if(privilegeList != null){
					for (Privilege privilege : privilegeList) {
						String url = privilege.getUrl();
						if(url != null && url.length() > 0 && form.indexOf(url) != -1){
							if(!privilege.getIsTrue()){
								loginPage = contextPath + "/noPrivilege.htm?JSESSIONID=" + session.getId();
								builder.append("<script type=\"text/javascript\">");
								builder.append("window.top.location.href='");
								builder.append(loginPage);
								builder.append("';");
								builder.append("</script>");
								out.print(builder.toString());
								return;
							}
						}
					}
				}
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
}