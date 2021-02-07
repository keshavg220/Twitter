package com.example.postgresdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.UserDao;


@Repository
public interface UserRepository extends  JpaRepository<UserDao, Long> {
	UserDao findByUsername(String username);

}