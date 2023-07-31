package com.sip.TutoriaCRUDTest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Profile("test")
public class TestController {

	@Value("${welcome.message}")
	private String meg;
	
	private List<String> lists=Arrays.asList("a,b,c,d,e,f,g");
	
	@RequestMapping("/test")
	public String Hello(Model model) {
		
		model.addAttribute("message",meg);
		
		model.addAttribute("lists",lists);
		
		return "welcome";
	}
	
}
