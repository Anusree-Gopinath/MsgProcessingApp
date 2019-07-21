package com.msg.server.service;

import java.util.Set;

import com.msg.server.model.MessageType;
import com.msg.server.model.OperationType;
import com.msg.server.model.Product;

public class MessageServiceImpl implements MessageService {
	
	private Long transactionCount = 0L;
	
	SalesRegister salesRegister = SalesRegister.getInstance();

	@Override
	public boolean processMessages(String message){
		try {
			SalesReport salesReport = new SalesReportImpl();
			
			String[] details = message.split(",");
			Long prevVal = transactionCount;
			
			if (details[0].equals(MessageType.MSG.name())) //Message Type 1
			{	
					salesRegister.getSales().forEach((k,v)-> {
						if(k.getProductType().equals(details[1]))
						{
							salesRegister.setTransaction(k, 1L); 
							transactionCount++;
						}
					});		
					
					if (prevVal == transactionCount)
					{
						Product product = new Product();
						product.setProductType(details[1]);
						product.setUnitPrice(Double.valueOf(details[2]));	
						salesRegister.setTransaction(product, 1L); 
						transactionCount++;
					}
			}
			else if (details[0].equals(MessageType.DMSG.name())) //Message Type 2
			{				
					salesRegister.getSales().forEach((k,v)->{
						if(k.getProductType().equals(details[1]))
						{
							salesRegister.setTransaction(k, Long.valueOf(details[3])); 
							transactionCount++;
						}
					});
					
					if (prevVal == transactionCount)
					{
						Product product = new Product();
						product.setProductType(details[1]);
						product.setUnitPrice(Double.valueOf(details[2]));	
						salesRegister.setTransaction(product, Long.valueOf(details[3])); 
						transactionCount++;
					}
			}
			else if (details[0].equals(MessageType.AMSG.name()))//Message Type 3
			{				
				Set<Product> keySet = salesRegister.getSales().keySet();
				keySet.stream().forEach(p->
				{
						if(p.getProductType().equals(details[1]))
						{
							Double unitPrice = 0.0d;
							
							switch(OperationType.valueOf(details[3].toUpperCase()))
							{
								case ADD:
									unitPrice = p.getUnitPrice()+Double.valueOf(details[2]);
									break;
								case MULTIPLY:
									unitPrice = p.getUnitPrice() * Double.valueOf(details[2]);
									break;
								case SUBTRACT:
									unitPrice = p.getUnitPrice() - Double.valueOf(details[2]);
									break;
								default:
									break;
							}
							
							p.setUnitPrice(unitPrice);
						}
				});
				transactionCount++;
			}
			
			if (transactionCount%10 == 0)
			{
				System.out.println("Report after every 10th message..");
				salesReport.printReport();
			}
			
			if(transactionCount > 50)
			{
				System.out.println("Stopped accepting new messages..");
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
