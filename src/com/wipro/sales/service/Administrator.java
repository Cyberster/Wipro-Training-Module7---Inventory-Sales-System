package com.wipro.sales.service;

import java.util.ArrayList;
import java.util.Date;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.bean.Stock;
import com.wipro.sales.dao.SalesDao;
import com.wipro.sales.dao.StockDao;

public class Administrator {
	private static StockDao stockDao = new StockDao();
	private static SalesDao salesDao = new SalesDao();
	
	/**
	 * This method is used to insert the given stock obj into the TBL_STOCK table 
	 * using StockDao class insertStock method if the below conditions are successful.
	 * 1. Stock obj should not be null
	 * 2. ProductName should be of minimum 2 letters in length
	 * 3. If above 2 are valid generate Product Id using StockDao class generateProductId 
	 * 	method and store the same in the ProductID member of the given Stock Object
	 * 
	 * If any of the above conditions fail return “Data not Valid for insertion”
	 * Else Return the generated ProductId 
	 * @param stock
	 * @return
	 */
	public synchronized String insertStock(Stock stock) {		
		if (stock != null && stock.getProductName().length() >= 2) {
			String productID = stockDao.generateProductID(stock.getProductName());
			stock.setProductID(productID);
			if (stockDao.insertStock(stock) == 1)
				return productID;
			else
				return "Data not Valid for insertion";
		} else {
			return "Data not Valid for insertion";
		}
	}
	
	/**
	 * Delete the record of the given Product id using StockDao class deleteStock method, 
	 * if delete is successful return “deleted”
	 * else return “record cannot be deleted”
	 * @param ProductID
	 * @return
	 */
	public String deleteStock(String ProductID) {		
		if (stockDao.deleteStock(ProductID) == 1)
			return "deleted";
		else 
			return "record cannot be deleted";
	}
	
	/**
	 * This method is used to insert the given sales obj into the TBL_SALEStable using 
	 * SalesDao class insertSales method if the below conditions are successful.
	 * 1. Sales obj should not be null else return “Object not valid for insertion”
	 * 2. ProductID should be present in the TBL_STOCK table else return “Unknown 
	 * 	Product for sales”
	 * 3. Products current QuatityOnHand value should be more than the QuantitySold value
	 * 	else return “Not enough stock on hand for sales”
	 * 4. SalesDate should be current date or earlier date and not future date, 
	 * else return “Invalid date”
	 * 5. If above 4 are valid generate SalesId using SalesDao class generateSalesId method 
	 * and store the same in the SalesID member of the given Sales Object Call the 
	 * insertSalesmethod of SalesDao and insert the record. 
	 * If insertion is successful call the updateStock method of the StockDao and 
	 * update the sold quantity to the stock.
	 * On successful completion of both the transaction return “Sales Completed”else “Error”.
	 * @param sales
	 * @return
	 */
	public String insertSales(Sales sales) {		
		if (sales == null) 
			return "Object not valid for insertion";
		
		if (stockDao.getStock(sales.getProductID()) == null)
			return "Unknown Product for sales";
		
		if (stockDao.getStock(sales.getProductID()).getQuantityOnHand() < sales.getQuantitySold())
			return "Not enough stock on hand for sales";
		
		if (sales.getSalesDate().before(new Date()))
			return "Invalid date";
		
		String salesID = salesDao.generateSalesID(sales.getSalesDate());
		sales.setSalesID(salesID);
		
		if (salesDao.insertSales(sales) == 1) {
			if (stockDao.updateStock(sales.getProductID(), sales.getQuantitySold()) == 1)
				return "sales record inserted successfully";
			else 
				return "Error";
		} else {
			return "Error";
		}
	}
	
	/**
	 * This method calls the getSalesReport of the SalesDao and returns the ArrayList
	 * @return
	 */
	public ArrayList<SalesReport> getSalesReport() {	
		return salesDao.getSalesReport();
	}
	
}