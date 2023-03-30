package com.rickmorty.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
	
	@GetMapping()
	public String landing() {
		return "index";
	}
	
	@GetMapping("/page01")
	public String page01(){
		return "page01";
	}
}
