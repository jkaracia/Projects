package com.techelevator.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.User;

@Controller
public class AccountController {
	
	@Autowired
	private AuthProvider auth;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String login(ModelMap modelHolder) {
		return "login";
	}
	@RequestMapping(path="/", method=RequestMethod.POST)
	public String login (@RequestParam String username, @RequestParam String password, RedirectAttributes flash) {
		
		if (auth.signIn(username, password)) {
			return "redirect:/parksHome";
		} else {
			flash.addFlashAttribute("message", "Login Invalid");
			return "redirect:/";
		}
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logOff() {
		auth.logOff();
		return "logout";
	}
	
	@RequestMapping(path="/register", method=RequestMethod.GET)
	public String register(ModelMap modelHolder) {
		if (!modelHolder.containsAttribute("user")) {
			modelHolder.put("user", new User());
		}
		return "register";
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes flash) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			flash.addFlashAttribute("message", "Please fix the following errors:");
			return "redirect:/register";
		}
		auth.register(user.getUsername(), user.getPassword());
		return "redirect:/";
	}

}
