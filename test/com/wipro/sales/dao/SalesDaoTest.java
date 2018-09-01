package com.wipro.sales.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.dao.SalesDao;

public class SalesDaoTest {

	@Test
	public void testInsertSales() {
		SalesDao sdao = new SalesDao();
		String salesId =  sdao.generateSalesID(new Date());
		
		Sales sales = new Sales();
		sales.setSalesID(salesId);
		sales.setSalesDate(new Date());
		sales.setProductID("RE1001");
		sales.setQuantitySold(10);
		sales.setSalesPricePerUnit(15000);
		
		int expected = 1;
		int actual = sdao.insertSales(sales);
		
		assertEquals(expected, actual);
	}

//	@Test
//	public void testGenerateSalesID() {
//		SalesDao sdao = new SalesDao();
//		
//		String seq =  sdao.generateSalesID(new Date());
//		System.out.println(seq);
//		
//		assertEquals("181006", seq);
//	}

	@Test
	public void testGetSalesReport() {
		SalesDao sdao = new SalesDao();
		ArrayList<SalesReport> list = sdao.getSalesReport();
		
		assertNotNull(list);
	}

}
