package com.sanket.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PalindromeEntity {
	
	@Id
	@GeneratedValue
	Integer id;
	String userName;
	String strToTest;
	Boolean indicator;

}
