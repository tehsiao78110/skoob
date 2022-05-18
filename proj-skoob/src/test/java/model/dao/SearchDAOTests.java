package model.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import model.bean.ProductBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.config.xml" })
public class SearchDAOTests {
	@Autowired
	private SearchDAO searchDAO;

	@Test
	@Transactional
	@Rollback(true)
	public void testSelect() {
		List<ProductBean> products = searchDAO.getQqueryResult("海");
		System.out.println("總筆數：" + products.size());
		System.out.println("------------------------");
		for (ProductBean product : products) {
			System.out.println(product);
		}
	}

}
