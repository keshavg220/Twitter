package com.example.postgresdemo.repository;



import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Answer;
import com.example.postgresdemo.model.UserDao;
import com.example.postgresdemo.model.following;


@Repository
public interface followingRepository extends  JpaRepository<following, Long> {

	List<following> findByPrimaryUsername(String primaryUsername);
	
}