package com.sanket.demo.util;

import java.util.stream.IntStream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Utility {
	public static boolean isPalindrome(String strForTest) {
		boolean isPalindromFlag = true;
		for(int i=0; i<=strForTest.length()/2; i++) {
			if(strForTest.charAt(i) != strForTest.charAt(strForTest.length()-1-i)) {
				isPalindromFlag = false;
			}
		}
		
		System.out.println(IntStream.range(0, strForTest.length()/2)
				.noneMatch(i -> strForTest.charAt(i) != strForTest.charAt(strForTest.length()-1-i)));
		return isPalindromFlag;
	}
	public static boolean isNullOrBlank(String value) {
		if(value != null || !value.isBlank())
			return false;
		return true;
	}
	
	public ResponseEntity<Object> validate(String strToTest, String userName){
		if(Utility.isNullOrBlank(strToTest) || Utility.isNullOrBlank(userName)) {
			return ResponseEntity.badRequest().body("User Name/ String to check palindrome cann't be blank!");
		}
		if(strToTest.matches(".*\\s*")) {
			return ResponseEntity.badRequest().body("String to check palindrome should not contain spaces!");
		}
		if(strToTest.matches(".*\\d+.")) {
			return ResponseEntity.badRequest().body("String to check palindrome should not contain numbers!");
		}
		return null;
	}
}
