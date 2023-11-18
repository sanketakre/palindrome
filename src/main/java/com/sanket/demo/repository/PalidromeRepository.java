package com.sanket.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanket.demo.entity.PalindromeEntity;

@Repository
public interface PalidromeRepository extends JpaRepository<PalindromeEntity, Integer> {

}
