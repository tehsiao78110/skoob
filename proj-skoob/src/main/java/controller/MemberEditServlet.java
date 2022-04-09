package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.bean.MemberBean;
import model.service.MemberService;


@WebServlet(urlPatterns={"/member/memberedit.controller"})
public class MemberEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Pattern telRegex = Pattern.compile("[0-9]{0,3}-[0-9]{8}$");
    private final Pattern phoneRegex = Pattern.compile("[0-9]{4}-[0-9]{6}$");
    private boolean checktel(String string) {
		return string!=null && telRegex.matcher(string).find();
	}
	private boolean checkphone(String string) {
		return string!=null && phoneRegex.matcher(string).find();
	}
	private MemberService memberService;
	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		memberService = (MemberService)context.getBean("memberService");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收資料
		
		HttpSession session = request.getSession();
		
		MemberBean member = (MemberBean) session.getAttribute("user");		
		
		String county = request.getParameter("county");
		String district = request.getParameter("district");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String phone = request.getParameter("phone");
		String save = request.getParameter("save");
		String cancel = request.getParameter("cancel");			
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		//驗證資料
		if(cancel !=null) {
			member = memberService.search(member.getAccount());
			session.setAttribute("user", member);
			save = null ; cancel=null;errors=null;
			request.getRequestDispatcher(
					"/member/memberinfo.jsp").forward(request, response);
		}
				
		if(member!=null && member.getAccount()!=null && save !=null) {			
			//確認市話
			if(tel!= null && !checktel(tel)&& tel.length()!=0 ) {				
				errors.put("tel","請確認市話格式正確,必須為XXX(區碼)-XXXXXXXX");
			}
			//確認手機
			if(phone!= null && !checkphone(phone)&& phone.length()!=0  ) {
				errors.put("phone","請確認手機格式正確,必須為XXXX-XXXXXX");
			}				
		}
		//錯誤回傳
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/member/memberinfo.jsp").forward(request, response);			
			return;
		}
		if(save !=null) {
			//System.out.println("twcity:"+address);
			member.setCounty(county);
			member.setDistrict(district);
			member.setAddress(address);
			member.setPhone(phone);
			member.setTel(tel);
				
			MemberBean result = memberService.edit(member);
			session.setAttribute("user", result);
			request.getRequestDispatcher(
					"/member/memberinfo.jsp").forward(request, response);
		}						
	}
}
