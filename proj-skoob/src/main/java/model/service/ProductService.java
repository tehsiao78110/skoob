package model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.AuthorBean;
import model.bean.ProductBean;
import model.dao.AuthorDAO;
import model.dao.ProductDAO;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	public ProductBean select(Integer proid) {
		ProductBean bean = productDAO.select(proid);
		if (bean != null) {
			return bean;
		}
		return null;
	}

}
