package com.ibolit.ibolitadmin.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleUserRepository extends JpaRepository<SimpleUser,Integer>{

	Boolean existsByEmail(String email);

	Boolean existsBySurname(String surname);

	Boolean existsByPassword(String password);

	SimpleUser findByEmail(String email);


}
