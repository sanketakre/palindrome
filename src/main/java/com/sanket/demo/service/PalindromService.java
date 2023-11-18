package com.sanket.demo.service;

import java.util.List;

import com.sanket.demo.entity.PalindromeEntity;


public interface PalindromService {
	public PalindromeEntity save(PalindromeEntity palindromeEntity);
	public List<PalindromeEntity> findAll();
}
