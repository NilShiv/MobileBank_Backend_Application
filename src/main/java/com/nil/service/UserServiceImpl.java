package com.nil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nil.dto.LoginDTO;
import com.nil.dto.UserDTO;
import com.nil.entity.User;
import com.nil.exception.MobileBankException;
import com.nil.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public String createUser(UserDTO userDTO) throws MobileBankException {
		if(userRepository.findById(userDTO.getMobileNumber()).isPresent()) {
			throw new MobileBankException("USER.MOBILE_NUMBER_ALREADY_REGISTERED");
		}
		User user = new User();
		user.setAccountHolderName(userDTO.getAccountHolderName());
		user.setCommunicationAddress(userDTO.getCommunicationAddress());
		user.setDateOfBirth(userDTO.getDateOfBirth());
		user.setEmail(userDTO.getEmail());;
		user.setGender(userDTO.getGender());
		user.setMobileNumber(userDTO.getMobileNumber());
		user.setPAN(userDTO.getPAN());
		user.setPassword(userDTO.getPassword());
		user.setUserId(user.getUserId());
		userRepository.save(user);
		
		return "USER.ADDED_SUCCESS : "+ user.getUserId();
	}

	@Override
	public Boolean loginUser(LoginDTO loginDTO) throws MobileBankException {
		Optional<User> list = userRepository.findById(loginDTO.getMobileNumber());
		User user = list.orElseThrow(() -> new MobileBankException("USER.INVALID_MOBILE_NUMBER"));
		
		String b = user.getPassword();
		if(loginDTO.getPassword().equals(b))	{
			return Boolean.TRUE;
			
		}else {
			throw new MobileBankException("USER.AUTHENTICATION_FAILED");
		}
		
	}

	@Override
	public UserDTO getUserProfile(String userId) throws MobileBankException {
		Optional<User> list = userRepository.findByUserId(userId);
		User user = list.orElseThrow(() -> new MobileBankException("USER.USERiD_NOT_FOUND"));
		UserDTO userDTO = new UserDTO();
		userDTO.setAccountHolderName(user.getAccountHolderName());
		userDTO.setCommunicationAddress(user.getCommunicationAddress());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setEmail(user.getEmail());
		userDTO.setGender(user.getGender());
		userDTO.setMobileNumber(user.getMobileNumber());
		userDTO.setPAN(user.getPAN());
		userDTO.setPassword(user.getPassword());
		userDTO.setUserId(user.getUserId());
		
		return userDTO;
	}

	@Override
	public List<UserDTO> showAllUsers() throws MobileBankException {
		Iterable<User> users = userRepository.findAll();
		List<UserDTO> userDTOs= new ArrayList<>();
		for(User user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setAccountHolderName(user.getAccountHolderName());
			userDTO.setCommunicationAddress(user.getCommunicationAddress());
			userDTO.setDateOfBirth(user.getDateOfBirth());
			userDTO.setEmail(user.getEmail());
			userDTO.setGender(user.getGender());
			userDTO.setMobileNumber(user.getMobileNumber());
			userDTO.setPAN(user.getPAN());
			userDTO.setPassword(user.getPassword());
			userDTO.setUserId(user.getUserId());
			userDTOs.add(userDTO);
		}
		if(userDTOs.isEmpty()) {
			throw new MobileBankException("USER.NO_USERS_FOUND");
		}
		return userDTOs;
	}

}
