package com.psg.ihsserver.paymentGateway;

import com.tp.pg.util.TransactionRequestBean;

public class ProcessPayment {

	// MOCK
	String key = "";
	String iv = "";

	public void createNewTransaction() {
		TransactionRequestBean objTransactionRequestBean = new TransactionRequestBean();

		objTransactionRequestBean.setStrRequestType("TWC");
		objTransactionRequestBean.setStrMerchantCode("T2663");
		objTransactionRequestBean.setMerchantTxnRefNumber("TXN0001");
		objTransactionRequestBean.setStrAmount("120.00");
		objTransactionRequestBean.setStrCurrencyCode("INR");
		objTransactionRequestBean.setStrITC("email:myname@domain.com");
		objTransactionRequestBean
				.setStrReturnURL("https://tpslvksrv6046/MerchantIntegrationClient/Responsepayload.jsp ");
		objTransactionRequestBean.setStrShoppingCartDetails("Test_120.0_0.0");
		objTransactionRequestBean.setTxnDate("24-12-2013");
		objTransactionRequestBean.setStrBankCode("9980");// Bank Code
		objTransactionRequestBean
				.setWebServiceLocator("https://www.tpsl-  india.in/PaymentGateway/services/TransactionDetailsNew");// This
																													// will
																													// be
																													// different
																													// for
																													// UAT
																													// and
																													// LIVE
		objTransactionRequestBean.setStrTPSLTxnID("");
		objTransactionRequestBean.setStrMobileNumber("8451053257");
		objTransactionRequestBean.setKey(key.getBytes());
		objTransactionRequestBean.setIv(iv.getBytes());
		objTransactionRequestBean.setStrCustomerName("xyz");
		objTransactionRequestBean.setStrEmail("myname@domain.com");
		objTransactionRequestBean.setStrTimeOut("1000");// this should be in
														// seconds

		objTransactionRequestBean.setCardNo("11111323213121111");// Card No
		objTransactionRequestBean.setCardName("johney boss");// Card Holder Name
		objTransactionRequestBean.setCardExpMM("11");// Expiry Month in MM
														// format
		objTransactionRequestBean.setCardExpYY("2017");// Epiry Year in YYYY
														// format
		objTransactionRequestBean.setCardCVV("111");// CVV if available

		String response = objTransactionRequestBean.getTransactionToken();
		
		if(response.startsWith("ERROR")){
			//TODO Handle transaction failure
		}
		else
		{
			
			response.substring(response.indexOf("="),response.length());
		}
	}

	
	
}
