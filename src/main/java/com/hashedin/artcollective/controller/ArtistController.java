package com.hashedin.artcollective.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArtistController {
	
	@RequestMapping("/")
	public ModelAndView index() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("time", new Date());
		model.put("message", "Hello Artists!");
		
		return new ModelAndView("index", model);
	}
}
