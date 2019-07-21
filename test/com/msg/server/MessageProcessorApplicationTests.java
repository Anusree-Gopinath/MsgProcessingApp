package com.msg.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.msg.server.service.MessageService;
import com.msg.server.service.MessageServiceImpl;
import com.msg.server.service.SalesRegister;
import com.msg.server.service.SalesReport;
import com.msg.server.service.SalesReportImpl;

public class MessageProcessorApplicationTests {
	
	MessageService messageService = new MessageServiceImpl();
	SalesReport salesReport = new SalesReportImpl();

	@Test
	public void testProcessMessages() throws Exception {
		SalesRegister.getInstance().clearSales();
		
		List<String> messages = new ArrayList<String>();
		messages.add("MSG,Apple,10");
		messages.add("MSG,Banana,15");
		messages.add("DMSG,Banana,15,10");
		messages.add("AMSG,Apple,10,ADD");
		
		assertEquals(false, salesReport.printReport());
		assertEquals(true, messageService.processMessages(messages.get(0)));
		assertEquals(true, messageService.processMessages(messages.get(1)));
		assertEquals(true, messageService.processMessages(messages.get(2)));
		assertEquals(true, messageService.processMessages(messages.get(3)));
		assertEquals(true, salesReport.printReport());
	}

	@Test
	public void testProcessMessages_70sales() {
		SalesRegister.getInstance().clearSales();
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader("test/testdata/Msgs70.csv"))){
			String line;
			assertEquals(false, salesReport.printReport());
			int counter = 0;
			
			while((line = bufferedReader.readLine()) != null)
			{
				boolean retVal = messageService.processMessages(line);
				
				if (!retVal)
				{
					assertEquals(50, counter);
					break;
				}
				
				counter++;
				
			}
			
			assertEquals(true, salesReport.printReport());
			
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}
}
