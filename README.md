# MsgProcessingApp
This is a standalone application to process the sales messages.
MessageProcessorApplication reads the sale transactions from Messages.csv file.

Sale transactions are of 3 types:
MSG - Message Type 1
DMSG - Message Type 2 (Detailed)
AMSG - Message Type 3 (Adjustment)

The transactions are processed and report is printed on to the console after every 10 messages. After 50 messages, application stops accepting more transactions and prints the final report with the adjustments.

The Junit test cases are present in test folder - MessageProcessorApplicationTests.java
