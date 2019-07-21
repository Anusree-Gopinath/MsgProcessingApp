package com.msg.server;

import java.io.BufferedReader;
import java.io.FileReader;

import com.msg.server.service.MessageService;
import com.msg.server.service.MessageServiceImpl;
import com.msg.server.service.SalesReport;
import com.msg.server.service.SalesReportImpl;

public class MessageProcessorApplication {
	
	public static void main(String[] args) {	
		String line;		
		MessageService messageService = new MessageServiceImpl();
		SalesReport salesReport = new SalesReportImpl();
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader("Messages.csv"))){
		
			while((line = bufferedReader.readLine()) != null)
			{
				boolean retVal = messageService.processMessages(line);
				
				if (!retVal)
				{
					break;
				}
				
			}
			
			System.out.println("Final Report");
			salesReport.printReport();
			
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}	
	}
}
