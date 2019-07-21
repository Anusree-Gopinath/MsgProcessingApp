package com.msg.server.service;

public class SalesReportImpl implements SalesReport {
	
	private SalesRegister salesRegister = SalesRegister.getInstance();
	
	@Override
	public boolean printReport()
	{
		boolean retVal = false;
		try {
			if (!salesRegister.getSales().isEmpty())
			{
				System.out.println("***********************************************************************************************");
				salesRegister.getSales().forEach((key, value)->{
					System.out.println("Product: "+key.getProductType()+" Unit Price: "+ key.getUnitPrice()+" Total Sales: "+ value+ " Gross Amount: "+(key.getUnitPrice()*value));
				});
				System.out.println("***********************************************************************************************");
				retVal = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
}
