package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.UserDto;
import com.epam.service.LoginService;
import com.epam.service.RegisterService;

@Controller
public class AdminController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private RegisterService registerService;
	private String adminLogin = "adminlogin";
	private String login = "login";
	private String adminHome = "adminhome";
	private String adminRegister = "adminregister";

	@RequestMapping("/login")
	public String home(String usertype) {
		return login;
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/index")
	public String indexPage() {
		return "index";
	}

	@RequestMapping("/adminlogin")
	public ModelAndView adminLoginPage(UserDto user) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(adminLogin);
		return mv;
	}

	@RequestMapping("/adminhome")
	public String adminHome() {
		return adminHome;
	}

	@GetMapping("/verifyadmin")
	public ModelAndView verifyAdmin(UserDto user) {
		ModelAndView mv = new ModelAndView();
		if (loginService.getAuthentication(user)) {
			mv.setViewName(adminHome);
			mv.addObject(login, "Admin Logged in succesfully......");
		} else {
			mv.setViewName(adminLogin);
			mv.addObject("invaliduser", "Invalid User...");
		}
		return mv;
	}

	@RequestMapping("/addadmin")
	public ModelAndView addUser(String username, String password, String confirmpassword) {
		ModelAndView mv = new ModelAndView();
		if (loginService.getAuthentication(new UserDto(username, password, "admin"))) {
			mv.setViewName(adminRegister);
			mv.addObject("alreadyregistered", "admin already registered...please try adding another admin..");
		} else {
			if (password.equals(confirmpassword)) {
				registerService.addUser(new UserDto(username, password, "user"));
				mv.setViewName(adminHome);
				mv.addObject("adminregisterd", "admin registered succesfully...");
			} else {
				mv.setViewName(adminRegister);
				mv.addObject("adminregistered", "password and confirm password did not match...");
			}
		}
		return mv;
	}

	@RequestMapping("/adminregister")
	public String adminRegister() {
		return adminRegister;
	}

	@RequestMapping("/logout")
	public String logout() {
		return "index";
	}
}
