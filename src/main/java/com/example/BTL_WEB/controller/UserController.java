package com.example.BTL_WEB.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.BTL_WEB.book.User;
import com.example.BTL_WEB.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login/check")
	public String check(Model model, HttpSession session, User user) {
		User userLogin = userService.getAccount(user.getUsername(), user.getPassword());
		if(userLogin != null) {
			session.setAttribute("userLogin", userLogin);
			return "redirect:/home";
		}
		model.addAttribute("error", "true");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/home";
	}
	
	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup/save")
	public String createUser(@RequestParam("repeatpassword") String repass ,Model model, User user, RedirectAttributes redirectAttributes) {
		user.setRole("GUEST");
	    if (!repass.equals(user.getPassword())) {
	        model.addAttribute("repeatnotequal", "true");
	        return "signup";
	    }
	    
	    if(userService.isExisted(user) == true) {
			model.addAttribute("existed", "true");
			model.addAttribute("repeatnotequal", "false");
			return "signup";
		}
	    
	    userService.save(user);
	    return "redirect:/home";
	}

	
}
