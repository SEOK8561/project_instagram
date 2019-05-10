package com.cos.instagram.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.instagram.model.Images;

public interface ImageRepository extends JpaRepository<Images, Integer>{

	
}
