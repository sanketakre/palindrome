package com.sanket.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanket.demo.controller.PalindromeRestController;
import com.sanket.demo.entity.PalindromeEntity;
import com.sanket.demo.repository.PalidromeRepository;
import com.sanket.demo.util.Utility;

@Service
public class PalindromServiceImpl implements PalindromService {
	
	Logger log = LoggerFactory.getLogger(PalindromeRestController.class);
	
	private PalidromeRepository palidromeRepository;
	
	@Autowired
	public PalindromServiceImpl(PalidromeRepository palidromeRepository) {
		this.palidromeRepository = palidromeRepository;
	}


	@Override
	public PalindromeEntity save(PalindromeEntity palindromeEntity) {
		log.info("In PalindromServiceImpl {}",palindromeEntity);
		palindromeEntity.setIndicator(Utility.isPalindrome(palindromeEntity.getStrToTest()));
		return palidromeRepository.save(palindromeEntity);
	}


	@Override
	public List<PalindromeEntity> findAll() {
		return palidromeRepository.findAll();
	}

}
