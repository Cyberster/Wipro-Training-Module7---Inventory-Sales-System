package com.wipro.sales.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.sales.bean.Stock;
import com.wipro.sales.dao.StockDao;

public class StockDaoTest {

	//@Test
	public void testInsertStock() {
		StockDao sdao = new StockDao();
		
		String productName = "Galaxy S5";
		String productID = sdao.generateProductID(productName);
		
		Stock stock = new Stock();
		stock.setProductID(productID);
		stock.setProductName(productName);
		stock.setQuantityOnHand(10);
		stock.setProductUnitPrice(40000);
		stock.setReorderLevel(3);
		
		assertEquals(1, sdao.insertStock(stock));
	}

	//@Test
	public void testGenerateProductID() {
		StockDao sdao = new StockDao();
		
		String productName = "Redmi Note 5";
		String productID = sdao.generateProductID(productName);
		
		assertEquals("Re1006", productID);
	}

	@Test
	public void testUpdateStock() {
		StockDao sdao = new StockDao();
		
		assertEquals(1, sdao.updateStock("1008Ga", 1));
	}

	//@Test
	public void testGetStock() {
		StockDao sdao = new StockDao();
		
		assertNotNull(sdao.getStock("Ga1009"));
	}

	//@Test
	public void testDeleteStock() {
		StockDao sdao = new StockDao();
		
		assertEquals(1, sdao.deleteStock("Te1016"));
	}

}
