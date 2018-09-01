package com.wipro.sales.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.sales.bean.Stock;

public class AdministratorTest {
	Administrator admin = new Administrator();
	//@Test
	public void testInsertStock() {	
		Stock stock = new Stock();
//		stock.setProductID(productID);
		stock.setProductName("Test Product5");
		stock.setQuantityOnHand(10);
		stock.setProductUnitPrice(40000);
		stock.setReorderLevel(3);
		
		assertNotEquals("Data not Valid for insertion", admin.insertStock(stock));
	}

	@Test
	public void testDeleteStock() {
		assertEquals("deleted", admin.deleteStock("Te1016"));
	}

	@Test
	public void testInsertSales() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetSalesReport() {
		//fail("Not yet implemented"); 
	}

}
