package com.sanket.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanket.demo.entity.PalindromeEntity;
import com.sanket.demo.service.PalindromService;
import com.sanket.demo.util.Utility;

import lombok.extern.slf4j.Slf4j;


/**
 * @author Sanket Akre
 * REST API to accept a username and text value and return an indicator whether that value is a palindrome.
 *
 */

@Slf4j
@RequestMapping("/myapp")
@RestController
public class PalindromeRestController {
	
	Logger log = LoggerFactory.getLogger(PalindromeRestController.class);	
	
	private PalindromService palindromService;	
	private CacheManager cacheManager;
	
	@Autowired
	public PalindromeRestController(PalindromService palindromService,CacheManager cacheManager) {
		super();
		this.palindromService = palindromService;
		this.cacheManager = cacheManager;
	}

	/**
	 * 
	 * REST API to accept a username and text value and return an indicator whether that value is a palindrome.
	 * @param userName This is input parameter
	 * @param strToTest This is input parameter to check whether value is palindrome
	 * @return boolean Return an indicator whether that value is a palindrome
	 * 
	 */
	@GetMapping("/userName/{userName}/strToTest/{strToTest}")
	@Cacheable(value="palindromeEntity")
	public ResponseEntity<Object> checkPalindrome(@PathVariable("userName") String userName,
			@PathVariable("strToTest") String strToTest) {
		
		log.info("Request parameters strToTest {} userName {}",strToTest, userName);

		// Validate the request parameter
		if(Utility.isNullOrBlank(strToTest) || Utility.isNullOrBlank(userName)) {
			return ResponseEntity.badRequest().body("User Name/ String to check palindrome cann't be blank!");
		}
		if(strToTest.matches(".*\\s.*")) {
			return ResponseEntity.badRequest().body("String to check palindrome should not contain spaces!");
		}
		if(strToTest.matches(".*\\d+.")) {
			return ResponseEntity.badRequest().body("String to check palindrome should not contain numbers!");
		}
		
		// To persist the UserName, strToTest
		PalindromeEntity palindromeEntity = new PalindromeEntity();
		palindromeEntity.setUserName(userName);
		palindromeEntity.setStrToTest(strToTest);
		
		try {
			palindromService.save(palindromeEntity);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok(palindromeEntity.getIndicator());
	}
	
	
	@GetMapping("/health")
	public String healthCheck() {
		return "Up";
	}
}
