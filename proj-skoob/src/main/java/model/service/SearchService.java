package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.ProductBean;
import model.dao.SearchDAO;

@Service
@Transactional
public class SearchService {
	@Autowired
	private SearchDAO searchDAO;

	public List<ProductBean> search(String keyword) {
//		List<ProductBean> result = searchDAO.search(keyword);
		keyword = "%" + keyword + "%";
		List<ProductBean> result = searchDAO.getQqueryResult(keyword);
		return result;
	}
}
