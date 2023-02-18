package com.ibolit.ibolitadmin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	//Can not delete an user that has made already an appointment
	@RequestMapping("/UserDelete/{idUser}")
	public ModelAndView userDelete(@PathVariable Integer idUser, ModelAndView modelAndView) {
		userRepository.deleteById(idUser);
		modelAndView.addObject("displayUsers", userRepository.findAll());
		modelAndView.setViewName("UserManage");
	 	return modelAndView;
		
	}
	
	@RequestMapping("/userCreateForm")
	public ModelAndView createResponce(ModelAndView modelAndView) {
		modelAndView.setViewName("userCreateForm");        
		return modelAndView;   
	}
	
	@RequestMapping(value = "/createNewUser", method = RequestMethod.POST)
	public ModelAndView createNewUserResponce(ModelAndView modelAndView, SimpleUser user) {
		final Boolean userEmailExists = userRepository.existsByEmail(user.getEmail());
		final Boolean userPasswordExists = userRepository.existsByPassword(user.getPassword());
		if (userEmailExists || userPasswordExists) {
			modelAndView.setViewName("AdminProfile");
			return modelAndView;
		} else {
			userRepository.save(user);
			modelAndView.setViewName("AdminProfile");        
			return modelAndView;
		}
	}
	@RequestMapping(value="/UserUpdateForm/{idUser}", method = RequestMethod.GET)
	public ModelAndView updateUserResponce(ModelAndView modelAndView,@PathVariable (value ="idUser") Integer idUser) {
		SimpleUser user = userRepository.findById(idUser).get();
		modelAndView.addObject("SimpleUser", user);
		modelAndView.setViewName("UserUpdateForm");  
		return modelAndView;
		
	}
}