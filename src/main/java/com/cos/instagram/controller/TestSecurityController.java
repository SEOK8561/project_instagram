package com.cos.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestSecurityController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/user/login")
	public String userLogin() {
		return "user/login";
	}
	@GetMapping("/image/explore")
	public String exploreImage() {
		return "image/explore";
	}
}
