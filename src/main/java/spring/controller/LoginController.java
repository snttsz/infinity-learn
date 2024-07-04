package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController 
{
	// TODO -> fazer controller do login
	@GetMapping("/login")
	public String login() 
	{
		return "login";
	}
}