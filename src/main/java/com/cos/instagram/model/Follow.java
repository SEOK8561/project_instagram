package com.cos.instagram.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="fromUser")	//DB에 들어갈 FK이름
	private Users fromUser;
	
	@ManyToOne
	@JoinColumn(name="toUser")		//FK 변수명(마음대로)
	private Users toUser;
	
	@CreationTimestamp
	private LocalDate createDate;
	@CreationTimestamp
	private LocalDate updateDate;
}
