package com.alishirmohammadi.librarymanagementsystem.login;


import com.alishirmohammadi.librarymanagementsystem.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
@Autowired
EmailService emailService;
	@Autowired
	private UserRepository userRepo;
	
	//@GetMapping("")
//	public String viewHomePage() {
	//	return "index";
//	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		emailService.send(user.getEmail(),"Account Information","username:"+user.getUserName()+"---"+"password:"+user.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);

		return "index";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "list-user";
	}


}
