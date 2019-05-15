package com.cos.instagram.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.instagram.model.Images;
import com.cos.instagram.model.Tags;
import com.cos.instagram.model.Users;
import com.cos.instagram.repository.ImageRepository;
import com.cos.instagram.repository.TagRepository;
import com.cos.instagram.repository.UserRepository;
import com.cos.instagram.util.UtilCos;

@RestController
public class TestImageController {
	
	private static final String context = "TestImageController";
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/image/upload")
	public Images imageUpload(@RequestParam("file") MultipartFile file, String caption, String location, String tags) throws IOException{
		Path filePath = Paths.get(UtilCos.getResourcePath()+file.getOriginalFilename());
		System.out.println(context+filePath);
		Files.write(filePath, file.getBytes());
		
		Users user = UtilCos.getUser();
		String password = user.getPassword();
		String encPassword = passwordEncoder.encode(password);
		System.out.println("password : "+encPassword);
		user.setPassword(encPassword);
		userRepository.save(user);		//user객체가 db에 flush 되지 않으면 image를 save할 수 없음.
		List<String> tagList = UtilCos.tagParser(tags);
		
		Images image = Images.builder()
				.caption(caption)
				.location(location)
				.user(user)
				.mimeType(file.getContentType())
				.fileName(file.getOriginalFilename())
				.filePath(filePath.toString())
				.build();
		
		imageRepository.save(image);
		
		for(String t : tagList) {
			Tags tag = new Tags();
			tag.setName(t);
			tag.setImage(image);
			tag.setUser(user);
			tagRepository.save(tag);
			image.getTags().add(tag);
		}
		
		return image;
	}
	
	@PostMapping("test/image/upload")
	public ResponseEntity<Resource> imageUpload(@RequestParam("file")MultipartFile file) throws IOException {

		Images image = new Images();
		//image.setFile(file.getBytes());
		imageRepository.save(image);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getOriginalFilename()+"\"")
				.contentType(MediaType.parseMediaType(file.getContentType()))
				.body(new ByteArrayResource(file.getBytes()));
	}
}
