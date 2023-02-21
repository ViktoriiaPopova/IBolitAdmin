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

import com.ibolit.ibolitadmin.model.Appointment;
import com.ibolit.ibolitadmin.model.AppointmentMapping;
import com.ibolit.ibolitadmin.model.AppointmentRepository;
import com.ibolit.ibolitadmin.model.SimpleUser;
import com.ibolit.ibolitadmin.model.SimpleUserRepository;
import com.ibolit.ibolitadmin.model.Speciality;
import com.ibolit.ibolitadmin.model.SpecialityRepository;


@RestController
public class AdminController {
	
	//Repositories here
	@Autowired
	private SimpleUserRepository userRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private SpecialityRepository specialityRepository;
	
	//Administrator page
	@RequestMapping("/AdminProfile")
	public ModelAndView AdminResponce(ModelAndView modelAndView) {
		//modelAndView.addObject("message", "testing");
		modelAndView.setViewName("AdminProfile");        
		return modelAndView;    
		}
	
	//Managing user CRUD operations starts here
	@RequestMapping("/UserManage")
	public ModelAndView UserResponce(ModelAndView modelAndView) {
		modelAndView.addObject("displayUsers", userRepository.findAll());
		modelAndView.setViewName("UserManage");
	 	return modelAndView;    
	}
	//Problem: Can not delete an user that has made already an appointment
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
			userRepository.save(user);
			modelAndView.setViewName("AdminProfile");        
			return modelAndView;
	}
	@RequestMapping(value="/UserUpdateForm/{idUser}", method = RequestMethod.GET)
	public ModelAndView updateUserResponce(ModelAndView modelAndView,@PathVariable (value ="idUser") Integer idUser) {
		SimpleUser user = userRepository.findById(idUser).get();
		modelAndView.addObject("SimpleUser", user);
		modelAndView.setViewName("UserUpdateForm");  
		return modelAndView;
		
	}
	
	//Managing appointment CRUD operations starts here
	@RequestMapping("/AppointmentManage")
	public ModelAndView AppointmentResponce(ModelAndView modelAndView) {
		modelAndView.addObject("displayAppointments", appointmentRepository.findAll());
		modelAndView.setViewName("AppointmentManage");
	 	return modelAndView;    
}
	//Delete an appointment
	@RequestMapping("/AppointmentDelete/{id_appointment}")
	public ModelAndView appointmentDelete(@PathVariable Integer id_appointment, ModelAndView modelAndView) {
		appointmentRepository.deleteById(id_appointment);
		modelAndView.addObject("displayAppointments", appointmentRepository.findAll());
		modelAndView.setViewName("AppointmentManage");
	 	return modelAndView;
}
	 
	//Update an appointment !mistake here!
	@RequestMapping(value="/AppointmentSaveForm", method = RequestMethod.POST)
	public ModelAndView saveAppointmentResponce(ModelAndView modelAndView, AppointmentMapping appointment) {
		Optional<Appointment> appointmentEntityOptional = appointmentRepository.findById(appointment.getId_appointment());
		Appointment appointmentEntity = null;
		if(appointmentEntityOptional.isEmpty()) {
			appointmentEntity = new Appointment();
		}else {
			appointmentEntity = appointmentEntityOptional.get();
		}
	 	appointmentEntity.setId_appointment(appointment.getId_appointment());
	 	appointmentEntity.setSpeciality(appointment.getSpeciality());
	 	appointmentEntity.setDates(appointment.getDates());
	 	appointmentEntity.setComment_user(appointment.getComment_user());
	 	//appointmentEntity.setSimpleUser(appointment.getSimpleUser());
		//modelAndView.addObject("Appointment", appointment);
	 	appointmentRepository.save(appointmentEntity);
		modelAndView.setViewName("AppointmentManage");  
		return modelAndView;
	}
	@RequestMapping(value="/AppointmentUpdateForm/{id_appointment}", method = RequestMethod.GET)
	public ModelAndView AppointmentUpdateForm(ModelAndView modelAndView,@PathVariable (value ="id_appointment") Integer id_appointment, Appointment appointment) {
		modelAndView.addObject("Appointment", appointment);
		modelAndView.setViewName("AppointmentUpdateForm");  
		return modelAndView;
	}
	//Create  new appointment
	@RequestMapping(value = "/createNewAppointment", method = RequestMethod.POST)
	public ModelAndView createNewAppointmentResponce(ModelAndView modelAndView, AppointmentMapping appointment) {
		 	SimpleUser user = userRepository.findById(appointment.getSimpleUser()).get();
		 	Appointment appointmentEntity = new Appointment();
		 	appointmentEntity.setComment_user(appointment.getComment_user());
		 	appointmentEntity.setDates(appointment.getDates());
		 	appointmentEntity.setSpeciality(appointment.getSpeciality());
		 	appointmentEntity.setSimpleUser(user);
			appointmentRepository.save(appointmentEntity);
			modelAndView.setViewName("AdminProfile");        
			return modelAndView;
		}
	@RequestMapping("/LogicImplementationAppointment")
	public ModelAndView LogicImplementationAppointment(ModelAndView modelAndView) {
		modelAndView.setViewName("LogicImplementationAppointment");        
		return modelAndView; 
	}
	
	@RequestMapping("/AppointmentCreateForm")
	public ModelAndView createNewResponce(ModelAndView modelAndView) {
		modelAndView.setViewName("AppointmentCreateForm");        
		return modelAndView;   
	}
	//Speciality Managing starts here
	@RequestMapping("/SpecialityManage")
	public ModelAndView SpecialityResponce(ModelAndView modelAndView) {
		modelAndView.addObject("displaySpeciality", specialityRepository.findAll());
		modelAndView.setViewName("SpecialityManage");
	 	return modelAndView;    
	}
	@RequestMapping("/SpecialityDelete/{id_speciality}")
	public ModelAndView specialityDelete(@PathVariable Integer id_speciality, ModelAndView modelAndView) {
		specialityRepository.deleteById(id_speciality);
		modelAndView.setViewName("AdminProfile");
	 	return modelAndView;
		
	}
	@RequestMapping("/specialityCreateForm")
	public ModelAndView specialityCreateForm(ModelAndView modelAndView) {
		modelAndView.setViewName("specialityCreateForm");        
		return modelAndView;   
	}

	
	@RequestMapping(value = "/saveNewSpeciality", method = RequestMethod.POST)
	public ModelAndView saveNewSpeciality(ModelAndView modelAndView, Speciality speciality) {
		//final Boolean spesialityExists = specialityRepository.existsBySpeciality(speciality.getId_speciality());
     specialityRepository.save(speciality);
     modelAndView.setViewName("SpecialityManage");        
			return modelAndView;
		}
	}

