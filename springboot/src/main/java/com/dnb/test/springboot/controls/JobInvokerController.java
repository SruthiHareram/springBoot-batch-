package com.dnb.test.springboot.controls;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class JobInvokerController {
	
	@RequestMapping("/")
	public ModelAndView firstPage() {
		return new ModelAndView("index");
	}
   
}