package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import model.bean.MemberBean;
import model.bean.MyFavBean;
import model.service.MemberService;
import model.service.MyfavService;

@Controller
@RequestMapping("/pages/myFav.controller")
public class MyfavController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MyfavService myfavService;

	@GetMapping
	public String get(Model model, HttpSession session) {
		System.out.println("myfov get!!");

		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 驗證是否登入
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			List<MyFavBean> favs = myfavService.selectMyFavs(member.getMemberid());
			model.addAttribute("favs", favs);
			return "/pages/myFav";
		} else {
			return "/pages/login";
		}

	}

	@PostMapping
	public String post(Integer productid, Model model, HttpSession session) {
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 驗證是否登入
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			Integer memberid = member.getMemberid();

			// 判斷會員的『收藏』是否已經存在
			boolean isExist = myfavService.checkMyfav(memberid, productid);
			String like = null;
			if (isExist) {
				myfavService.delete(memberid, productid);
			} else {
				MyFavBean bean = new MyFavBean();
				bean.setMemberId(memberid);
				bean.setProductId(productid);
				MyFavBean myFavbean = myfavService.insertA(bean);
				like = "like";
			}
			model.addAttribute("like", like);
			return "/pages/product";
		}
		return "XXXXXX";
	}

	@DeleteMapping
	public ResponseEntity delete(HttpSession session, @RequestBody String body) {
		System.out.println("body = " + body);
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 驗證是否登入
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			// 轉換成 json 格式
			JSONObject json = new JSONObject(body);
			System.out.println("json = " + json);

			// 解析/轉換 json 資料
			Integer productid = null;
			try {
				productid = json.getInt("productid");
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 依據刪除的結果，回應客戶端訊息
			if (myfavService.delete(member.getMemberid(), productid)) {
				return ResponseEntity.status(HttpStatus.OK).body("刪除收藏成功");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("收藏出現異常的操作");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入帳號");
		}
	}

}
