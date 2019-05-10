package com.cos.instagram.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.instagram.model.Images;
import com.cos.instagram.repository.ImageRepository;

@RestController
public class ImageController {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@PostMapping("/image/upload")
	public ResponseEntity<Resource> imageUpload(@RequestParam("file")MultipartFile file) throws IOException {
		System.out.println("getBytes : " + file.getBytes());
		System.out.println("getName : " + file.getName());
		System.out.println("getContentType : " + file.getContentType());
		System.out.println("getSize : " + file.getSize());
		System.out.println("getResource : " + file.getResource());
		System.out.println("getOriginalFilename. : " + file.getOriginalFilename());
		
		Images image = new Images();
		image.setFile(file.getBytes());
		imageRepository.save(image);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getOriginalFilename()+"\"")
				.contentType(MediaType.parseMediaType(file.getContentType()))
				.body(new ByteArrayResource(file.getBytes()));
	}
}
