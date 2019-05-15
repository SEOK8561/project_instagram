package com.cos.instagram.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

/* 
 * @JsonIgnore를 사용하면 해당 객체를 참조하지 않는다.
*/

@Data
@Entity
public class Tags {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;	//태그 이름
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name="imageId")
	//@JsonIgnore
	@JsonBackReference
	private Images image;			//폴링키를 갖는쪽에서 이미지를 넣는다.
	
	@OneToMany(mappedBy = "image")
	private List<Tags> tags = new ArrayList<>();
	
	@CreationTimestamp
	private LocalDate createDate;
	@CreationTimestamp
	private LocalDate updateDate;
	
	
	
	//이렇게 하면 시리어라이저블(직렬화)이 되지 않는다.
	/*public Images getImage() {
		return null;
	}*/
}
