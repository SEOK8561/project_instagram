package com.cos.instagram.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;	//유저 아이디
	private String name;		//실명
	private String website;
	private String bio;
	private String email;
	private String phone;
	private String gender;
	@Lob
	private byte[] profile;
	
	//@OneToMany(mappedBy="") 
	//private Follow from_user_id;
	
	//@OneToMany(mappedBy="") 
	//private Follow to_user_id;
	
		
	private Timestamp create_Date;
	private Timestamp update_Date;
}
