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

 
@WebServlet(urlPatterns={"/pages/register.controller"})
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
		
		//接收資料
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
		
		
		
		//寫入Bean
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
		//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if(register!=null) {
			//檢查帳號
			if(account==null || account.length()==0) {
				errors.put("account","請輸入帳號");			
			}else if(memberService.isRepeatAccount(account)) {
				errors.put("account","此帳號已被註冊");
			}else if(!checkaccount(account)) {
				errors.put("account","帳號格式須為英數字,長度介於6-12,可有_-");
			}
												
			//檢查密碼
			if(password==null || password.length()==0) {
				errors.put("password","請輸入密碼");
			}else if(!checkpassword(password)) {
				errors.put("password","密碼格式須為英數字,長度介於6-12,可有_-");
			}
			//確認密碼
			if(password2==null || password2.length()==0 || !password2.equals(password)) {
				System.out.println("pw2:"+ password2);
				errors.put("password2","請確認密碼");
			}
			//確認姓名
			if(name==null || name.length()==0) {
				errors.put("name","請輸入姓名");
			}else if(name.length()>20) {
				errors.put("name","您的姓名有問題,請聯絡客服");
			}
			
			//確認email
			if(email==null || email.length()==0) {
				errors.put("email","請輸入電子信箱");
			}else if(memberService.isRepeatEmail(bean)) {
				errors.put("email","此電子信箱已被註冊");
			}else if(!checkemail(email)) {
				errors.put("email","請確認email格式正確");
			}
			//確認生日
			if(birth==null || birth.length()==0) {
				errors.put("birth","請輸入生日");
			}
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
					"/member/register.jsp").forward(request, response);
			return;
		}
		
		//導向View(使用Service驗證資料)
		if(register.equals("註冊")) {
			MemberBean result = memberService.insert(bean);
			
			if(result==null) {
				errors.put("action", "註冊失敗");
				request.getRequestDispatcher(
					"/member/register.jsp").forward(request, response);
			} else {
				errors.put("action", "註冊成功，請登入使用服務");
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
