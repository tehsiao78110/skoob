package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.bean.MemberBean;
import model.service.MemberService;

 
//@WebServlet(urlPatterns={"/pages/register.controller"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    private final Pattern emailRegex = Pattern.compile("[a-z0-9A-Z_-]+([.][a-z0-9A-Z_-]+)*@[a-z0-9A-Z]+([.][a-z0-9A-Z_-]+)*$");
    private final Pattern accountRegex = Pattern.compile("[a-z0-9A-Z_-]{6,12}$");
    private final Pattern passwordRegex = Pattern.compile("[a-z0-9A-Z_-]{6,12}$");
    private final Pattern telRegex = Pattern.compile("[0-9]{0,3}-[0-9]{8}$");
    private final Pattern phoneRegex = Pattern.compile("[0-9]{4}-[0-9]{6}$");
    
    private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    private boolean checkaccount(String string) {
		return string!=null && accountRegex.matcher(string).find();
	}
	private boolean checkemail(String string) {
		return string!=null && emailRegex.matcher(string).find();
	}
	private boolean checkpassword(String string) {
		return string!=null && passwordRegex.matcher(string).find();
	}
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//????????????
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		String tel = request.getParameter("tel");
		String phone = request.getParameter("phone");
		String register = request.getParameter("register");
		
		
		
		//??????Bean
		Date date = null;
		if(birth!= null && birth.length()!=0 ) {
		try {
			date=sFormat.parse(birth);
//			String test = sFormat.format(date);
//			System.out.println("test:" +test);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		}
		MemberBean bean = new MemberBean();
		bean.setAccount(account);
		bean.setAddress(address);
		bean.setBirth(date);
		bean.setEmail(email);
		bean.setName(name);
		bean.setPassword(password);
		bean.setPhone(phone);
		bean.setTel(tel);
		//????????????
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if(register!=null) {
			//????????????
			if(account==null || account.length()==0) {
				errors.put("account","???????????????");			
			}else if(memberService.checkAccountExist(account)) {
				errors.put("account","?????????????????????");
			}else if(!checkaccount(account)) {
				errors.put("account","???????????????????????????,????????????6-12,??????_-");
			}
												
			//????????????
			if(password==null || password.length()==0) {
				errors.put("password","???????????????");
			}else if(!checkpassword(password)) {
				errors.put("password","???????????????????????????,????????????6-12,??????_-");
			}
			//????????????
			if(password2==null || password2.length()==0 || !password2.equals(password)) {
				System.out.println("pw2:"+ password2);
				errors.put("password2","???????????????");
			}
			//????????????
			if(name==null || name.length()==0) {
				errors.put("name","???????????????");
			}else if(name.length()>20) {
				errors.put("name","?????????????????????,???????????????");
			}
			
			//??????email
			if(email==null || email.length()==0) {
				errors.put("email","?????????????????????");
			}else if(memberService.isRepeatEmail(bean)) {
				errors.put("email","???????????????????????????");
			}else if(!checkemail(email)) {
				errors.put("email","?????????email????????????");
			}
			//????????????
			if(birth==null || birth.length()==0) {
				errors.put("birth","???????????????");
			}
			//????????????
			if(tel!= null && !checktel(tel)&& tel.length()!=0 ) {
				
				errors.put("tel","???????????????????????????,?????????XXX(??????)-XXXXXXXX");
			}
			//????????????
			if(phone!= null && !checkphone(phone)&& phone.length()!=0  ) {
				errors.put("phone","???????????????????????????,?????????XXXX-XXXXXX");
			}			
		}
		//????????????
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/member/register.jsp").forward(request, response);
			return;
		}
		
		//??????View(??????Service????????????)
		if(register.equals("??????")) {
			MemberBean result = memberService.insert(bean);
			
			if(result==null) {
				errors.put("action", "????????????");
				request.getRequestDispatcher(
					"/member/register.jsp").forward(request, response);
			} else {
				errors.put("action", "????????????????????????????????????");
				request.setAttribute("sent", result);				
				request.getRequestDispatcher(
						"/member/login.jsp").forward(request, response);
			}
			
			
//			request.getRequestDispatcher(
//					"/index.jsp").forward(request, response);
			
		}else {
			errors.put("action", "Unknown Action");
			request.getRequestDispatcher(
					"/member/register.jsp").forward(request, response);
		}
		
				
			
		
	}

	
	
}
