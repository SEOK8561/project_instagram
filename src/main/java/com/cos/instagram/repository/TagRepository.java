package com.cos.instagram.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.instagram.model.Tags;



public interface TagRepository extends JpaRepository<Tags, Integer>{

}
