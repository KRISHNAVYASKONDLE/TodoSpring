package com.in28.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SayHelloworld {

//	@ResponseBody to be not used when you are trying to use jsp
// /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
	
	
	@RequestMapping("hello")
	public String hello()
	{
		return "sayHello";
	}
	
}
