package com.example.postgresdemo.repository;



import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.UserDao;
import com.example.postgresdemo.model.following;


@Repository
public interface followingRepository extends  JpaRepository<following, Long> {
	
//	UserDao findByUsername(String username);

}