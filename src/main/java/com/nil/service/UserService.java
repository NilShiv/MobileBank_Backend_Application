package com.nil.service;

import java.util.List;

import com.nil.dto.LoginDTO;
import com.nil.dto.UserDTO;
import com.nil.exception.MobileBankException;

public interface UserService {

	public String createUser(UserDTO userDTO) throws MobileBankException;
	public Boolean loginUser(LoginDTO loginDTO) throws MobileBankException;
	public UserDTO getUserProfile(String userId) throws MobileBankException;
	public List<UserDTO> showAllUsers() throws MobileBankException;
	
}
