package model.dao;

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
public class ProductDAOTests {
	@Autowired
	private ProductDAO productDAO;

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		ProductBean product = productDAO.select(27);
		System.out.println("更新前： " + product);
		// 更新資料
		product.setInventory(product.getInventory() - 3);
		product.setBuytime(product.getBuytime() + 5);
		ProductBean update = productDAO.update(product);
		System.out.println("-------------------------------------------------");
		System.out.println("更新後： " + update);
	}
	
}
