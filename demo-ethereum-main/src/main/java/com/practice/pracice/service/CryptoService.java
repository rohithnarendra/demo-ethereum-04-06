package com.practice.pracice.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.practice.pracice.dto.CommonData;

@Service
public class CryptoService {
	
	
	@Value("${folderPath}")
	private String folderPath;
	
	public CommonData createAccount(CommonData data) throws Exception {
		System.out.println("folder path"+folderPath);
		
		
	    String directoryName = folderPath;
	    File directory = new File(directoryName);
	    if (! directory.exists()){
	    	System.out.println();
	        directory.mkdir();
	        }

		
		
		File file = new File(directoryName);
		System.out.println(data.getPassword());
		
        String fileName = WalletUtils.generateFullNewWalletFile(data.getPassword(), file);
        System.out.println("File Name------>>> " + fileName);
        String address = this.createAddress(fileName,data.getPassword());
        BigInteger bal = this.checkBalance(fileName,data.getPassword());
        CommonData commonData = new CommonData();
        commonData.setAddress(address);
        commonData.setBalance(bal);
        
        return commonData;
	}


	
	public BigInteger checkBalance(String path,String password) throws Exception{
        File file = new File
        		(folderPath+path);
  
        Credentials credentials = WalletUtils.loadCredentials(password, file);
        BigInteger privateKey = credentials.getEcKeyPair().getPrivateKey();
        
        String sprivateIndex = privateKey.toString(16);
        
       System.out.println(credentials.getEcKeyPair().getPrivateKey()
    		   +"key pair");
       
       System.out.println( sprivateIndex  +"  private Key");
        System.out.println("Get Wallet address===  "+credentials.getAddress());
            String address = credentials.getAddress();
            HttpService httpService = new HttpService("https://ropsten.infura.io/v3/78c9109631d94ee8be3941711cb1ea1b");
            Web3j web3 = Web3j.build(httpService);
        EthGetBalance balance = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger s = balance.getBalance();
        System.out.println("---------- "+balance.getBalance());
        return s;
    }
	
	public String createAddress(String path,String password) throws Exception {
	
	        File file = new File
	        		(folderPath+path);
	       
	        Credentials credentials = WalletUtils.loadCredentials(password, file);
	        String s = credentials.getAddress();
	        System.out.println("Get Wallet address===  " + credentials.getAddress());
	        return s;
	    
	}
	
	public String TransactionSend(CommonData data)throws Exception{
		System.out.println(data.getPassword());
		//String password = "Rohith@970037";
		 File directoryPath = new File(folderPath);
		 String fileName="";
		 File files[] = directoryPath.listFiles();
		 for(File file : files) {
			 if(file.getName().contains(data.getSrcAddress().substring(2))){
			fileName=file.getName();
			System.out.println("CryptoService.TransactionSend()"+fileName);
			 }
		 }
		 
		 System.out.println("file"+fileName);
		
        File file = new File
        		(folderPath+fileName);
        System.out.println(file.exists() +"eists");
        Credentials credentials = WalletUtils.loadCredentials(data.getPassword(),file);
        System.out.println("Get Wallet address===  "+credentials.getAddress());
            HttpService httpService = new HttpService("https://ropsten.infura.io/v3/78c9109631d94ee8be3941711cb1ea1b");
            Web3j web3 = Web3j.build(httpService);
            

            RemoteCall<TransactionReceipt> rTransactionReceipt = Transfer.sendFunds(web3, credentials, data.getAddress(), BigDecimal.valueOf(data.getValue()), Convert.Unit.ETHER);
            TransactionReceipt transactionReceipt=rTransactionReceipt.send();
            
            System.out.println("79"+transactionReceipt.getTransactionHash());
            String s = transactionReceipt.getTransactionHash();
            System.out.println(transactionReceipt.getTransactionHash());
            return s;
    }
	
	public CommonData generateFile(CommonData data) throws Exception {
		 BigInteger privateKeyInBT = new BigInteger(data.getPrivateKey(), 16);
		    ECKeyPair aPair = ECKeyPair.create(privateKeyInBT);
		    BigInteger publicKeyInBT = aPair.getPublicKey();
		    String sPublickeyInHex = publicKeyInBT.toString(16);
		    File directory = new File(folderPath);
		    if (! directory.exists()){
		    	System.out.println();
		        directory.mkdir();
		        }
			File file = new File(folderPath);
		    
		
			String fileName=	WalletUtils.generateWalletFile(data.getPassword(), aPair, file, true);
			 String address = this.createAddress(fileName,data.getPassword());
		        BigInteger bal = this.checkBalance(fileName,data.getPassword());
		        CommonData commonData = new CommonData();
		        commonData.setAddress(address);
		        commonData.setBalance(bal);
		        
		        return commonData;
		
		
	}
	
	

}
