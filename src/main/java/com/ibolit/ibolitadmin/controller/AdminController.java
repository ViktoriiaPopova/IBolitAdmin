package com.ibolit.ibolitadmin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ibolit.ibolitadmin.model.SimpleUser;
import com.ibolit.ibolitadmin.model.SimpleUserRepository;

@RestController
public class AdminController {
	
	//Repositories here
	@Autowired
	private SimpleUserRepository userRepository;
	
	@RequestMapping("/AdminProfile")
	public ModelAndView AdminResponce(ModelAndView modelAndView) {
		modelAndView.addObject("message", "blabla");
		modelAndView.setViewName("AdminProfile");        
		return modelAndView;    
		}

	@RequestMapping("/UserManage")
	public ModelAndView UserResponce(ModelAndView modelAndView) {
		modelAndView.addObject("displayUsers", userRepository.findAll());
		modelAndView.setViewName("UserManage");
	 	return modelAndView;    
	}
	@RequestMapping("/UserDelete/{idUser}")
	public ModelAndView userDelete(@PathVariable Integer idUser, ModelAndView modelAndView) {
	
		userRepository.deleteById(idUser);
		modelAndView.addObject("displayUsers", userRepository.findAll());
		modelAndView.setViewName("UserManage");
	 	return modelAndView;
		
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView saveData(ModelAndView modelAndView,SimpleUser user) {	
		//Not allow potential user to register with an email or surname that is already exist in db
		Boolean userEmail = userRepository.existsByEmail(user.getEmail());
		Boolean userSurname = userRepository.existsBySurname(user.getSurname());
		if(userEmail || userSurname) {
			System.out.println("user with this email alredy exists");
		}else {
			userRepository.save(user);		
			//Redirect to user profile
			modelAndView.setViewName("UserManage");
			 return modelAndView;
		}
		modelAndView.setViewName("UserManage");
		 return modelAndView;
	}
	
	

}