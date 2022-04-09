package model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.AuthorBean;
import model.dao.AuthorDAO;

@Service
@Transactional
public class AuthorService {
	@Autowired
	private AuthorDAO authorDAO;
	
	public AuthorBean selectauthor(Integer authid) {
		System.out.println("authid = "+authid);
		System.out.println("before----selectname");
		AuthorBean authorbean = authorDAO.selectname(authid);
		System.out.println("after----selectname");
		if(authorbean!=null) {
			
			//抓取作者相關資訊
			String authorname = authorbean.getAuthorname();
			String authorintro = authorbean.getAuthorintro();
			System.out.println("authorname : " + authorname);
			System.out.println("authorintro :" + authorintro);
			return authorbean;
			
		}
		return null;
	}
}
