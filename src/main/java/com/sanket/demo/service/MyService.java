package com.sanket.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.sanket.demo.entity.PalindromeEntity;

@Service
public class MyService implements ApplicationListener<ApplicationReadyEvent> {
	Logger log = LoggerFactory.getLogger(MyService.class);
	
	  @Autowired
	  PalindromService palindromService;

	  @Cacheable(value="palindromeEntity")
	  public List<PalindromeEntity> getEntry() {
		List<PalindromeEntity> listPalin = palindromService.findAll();
		log.info("Palidrom cache {}",listPalin);
	    return  listPalin;
	  }

	  @Override
	  public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
	    getEntry();
	  }
	}

