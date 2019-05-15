package com.cos.instagram.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor		//모든 필드가 있는 생성자를 생성함
@NoArgsConstructor
@Builder
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;	//유저 아이디
	private String password;
	private String name;		//실명
	private String website;
	private String bio;
	private String email;
	private String phone;
	private String gender;
	
	//@OneToMany(mappedBy="") 
	//private Follow from_user_id;
	
	//@OneToMany(mappedBy="") 
	//private Follow to_user_id;
		
	@CreationTimestamp
	private LocalDate createDate;
	@CreationTimestamp
	private LocalDate updateDate;
}
