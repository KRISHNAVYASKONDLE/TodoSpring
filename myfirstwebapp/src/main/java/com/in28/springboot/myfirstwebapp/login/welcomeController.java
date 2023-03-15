package com.in28.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class welcomeController {

	private Logger logger= LoggerFactory.getLogger(getClass());
	

	@RequestMapping(value="/", method= RequestMethod.GET)
	public String genlogin(ModelMap model)
	{
		model.put("name", usernamefromspring());
		return "redirect:list-todos";
	}
	
	 
	private String usernamefromspring()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
}


