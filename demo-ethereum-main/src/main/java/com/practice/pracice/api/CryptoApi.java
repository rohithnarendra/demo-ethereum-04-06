package com.practice.pracice.api;

import java.io.IOException;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import com.practice.pracice.dto.CommonData;
import com.practice.pracice.service.CryptoService;

@RestController
public class CryptoApi {
	
	@Autowired
	private CryptoService cryptoService;
	
	
	
	@PostMapping("/add/account")
	public CommonData createAccount(@RequestBody CommonData data) throws Exception {
		
		return cryptoService.createAccount(data);
	}
	@GetMapping("/create/address")
	public String createAddress() throws Exception {
			
			return  null;//cryptoService.createAddress();
		}
	
	@GetMapping("/check/balance")
public BigInteger checkBalance() throws Exception {
		
		return null;//cryptoService.checkBalance();
	}
	
	@PostMapping("/send/transaction")
	public String sendTransaction(@RequestBody CommonData data) throws Exception {
		System.out.println(data.toString());
		return cryptoService.TransactionSend(data);
	}
	
	@PostMapping("/fetch/account")
	public CommonData generateFile(@RequestBody CommonData data) throws Exception {
		return cryptoService.generateFile(data);
	}
	
}
