package com.in28.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	
	public boolean authenticate(String username,String password) {
		boolean isValidUsername=username.equals("krishna");
		boolean isValidPassword=username.equals("krishna");
		if(isValidUsername && isValidPassword)
		return true;
		else
		return false;
	}	
	
	
}
