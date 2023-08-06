package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
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
	private String index = "index";
	private ModelAndView modelAndView;

	@RequestMapping("/login")
	public String home(String role) {
		return login;
	}

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(index);
		return mv;
	}

	@RequestMapping("/adminlogin")
	public ModelAndView adminLoginPage(UserDto user) {
		modelAndView = new ModelAndView();
		modelAndView.setViewName(adminLogin);
		return modelAndView;
	}

	@RequestMapping("/adminhome")
	public String adminHome() {
		return adminHome;
	}

	@GetMapping("/verifyadmin")
	public ModelAndView verifyAdmin(UserDto user) {
		modelAndView = new ModelAndView();
		if (loginService.getAuthentication(user)) {
			modelAndView.setViewName(adminHome);
			modelAndView.addObject(login, "Admin Logged in succesfully......");
		} else {
			modelAndView.setViewName(adminLogin);
			modelAndView.addObject("invaliduser", "Invalid User...");
		}
		return modelAndView;
	}

	@RequestMapping("/addadmin")
	public ModelAndView addUser(String username, String password, String confirmpassword)
			throws UserException {
		modelAndView = new ModelAndView();
		if (loginService.getAuthentication(new UserDto(username, password, "admin"))) {
			modelAndView.setViewName(adminRegister);
			modelAndView.addObject("alreadyregistered", "admin already registered...please try adding another admin..");
		} else {
			if (password.equals(confirmpassword)) {
				registerService.addUser(new UserDto(username, password, "user"));
				modelAndView.setViewName(adminHome);
				modelAndView.addObject("adminregisterd", "admin registered succesfully...");
			} else {
				modelAndView.setViewName(adminRegister);
				modelAndView.addObject("adminregistered", "password and confirm password did not match...");
			}
		}
		return modelAndView;
	}

	@RequestMapping("/adminregister")
	public String adminRegister() {
		return adminRegister;
	}

	@RequestMapping("/logout")
	public String logout() {
		return index;
	}
}
