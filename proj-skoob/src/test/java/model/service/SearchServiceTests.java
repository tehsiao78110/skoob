package model.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import model.bean.ProductBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/beans.config.xml"})
@Transactional
public class SearchServiceTests {
	@Autowired
	private SearchService searchService;
	
	
	@Test
	public void name() {
		List<ProductBean> prodicts = searchService.search("海");
		System.out.println("總紀錄：" + prodicts.size());
		for (ProductBean product : prodicts) {
			System.out.println(product);
		}
	}
	
}
