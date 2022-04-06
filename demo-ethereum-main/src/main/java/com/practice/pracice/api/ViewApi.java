package com.practice.pracice.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewApi {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

}
