package com.msg.server.service;

import java.util.HashMap;

import com.msg.server.model.Product;

public class SalesRegister {
	
	private static SalesRegister salesInstance = null;
	
	private HashMap<Product, Long> sales = null;
	
	private SalesRegister()
	{
		sales = new HashMap<Product, Long>();
	}
	
	public static SalesRegister getInstance()
	{
		synchronized(SalesRegister.class)
		{
			if (salesInstance == null)
			{
				salesInstance = new SalesRegister();
			}
		}
	
		return salesInstance;
	}

	public HashMap<Product, Long> getSales() {
		return sales;
	}

	public void clearSales() {
		salesInstance = null;
	}
	
	public void setTransaction(Product product, Long count)
	{
		Long currentCount = sales.get(product);
		
		if (currentCount == null)
		{
			sales.put(product, count);
		}
		else
		{
			sales.replace(product, currentCount+count);
		}
	}

}
