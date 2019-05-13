package com.cos.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.instagram.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
