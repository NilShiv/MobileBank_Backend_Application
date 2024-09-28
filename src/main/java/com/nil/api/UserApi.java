package com.nil.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nil.dto.LoginDTO;
import com.nil.dto.UserDTO;
import com.nil.exception.MobileBankException;
import com.nil.service.UserService;

@RestController
@RequestMapping(value = "/user")
@Validated
public class UserApi {

	@Autowired
	UserService userService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value = "/users")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) throws MobileBankException{
		String success = userService.createUser(userDTO);
		return new ResponseEntity<String>(success, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/users/login")
	public ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO loginDTO) throws MobileBankException{
		userService.loginUser(loginDTO);
		environment.getProperty("USER.LOGGED_SUCCESS");
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@GetMapping(value = "/users/{userId}")
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId) throws MobileBankException{
		UserDTO userDTO = userService.getUserProfile(userId);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/users/all")
	public ResponseEntity<List<UserDTO>> showAllUsers() throws MobileBankException{
		List<UserDTO> userDTOs = userService.showAllUsers();
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}
}
